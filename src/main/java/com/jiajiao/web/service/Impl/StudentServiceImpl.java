package com.jiajiao.web.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiajiao.web.dao.*;
import com.jiajiao.web.enums.UserReqConst;
import com.jiajiao.web.form.CommentForm;
import com.jiajiao.web.form.GetStudentResumeOrderForm;
import com.jiajiao.web.form.StudentResumeForm;
import com.jiajiao.web.pojo.*;
import com.jiajiao.web.service.IStudentService;
import com.jiajiao.web.utils.ParentNeedUtil;
import com.jiajiao.web.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

    @Autowired
    StudentResumeMapper resumeMapper;
    @Autowired
    StudentAcademyMapper academyMapper;
    @Autowired
    SubjectMapper subjectMapper;
    @Autowired
    TimeMapper timeMapper;
    @Autowired
    StudentSentMapper studentSentMapper;
    @Autowired
    ParentSentMapper parentSentMapper;
    @Autowired
    ParentNeedMapper parentNeedMapper;
    @Autowired
    MessagesServiceImpl messagesService;

    @Override
    public ResponseVo getStudentAcademy() {
        List<StudentAcademy> academyList = academyMapper.selectAll();
        return ResponseVo.success("学院返回成功", academyList);
    }

    @Override
    public ResponseVo getStudentResumeByUId(Integer uId) {

        List<StudentResume> resumeList = resumeMapper.selectByUId(uId);
        List<StudentResumeVo> studentResumeVoList = buildStudentResumeVoList(resumeList);
        return ResponseVo.success("个人的简历获取成功", studentResumeVoList);

    }

    @Override
    public ResponseVo addStudentResume(StudentResumeVo studentResumeVo, Integer uId) {
        List<StudentResume> studentResumeList = resumeMapper.selectByUId(uId);
        if (studentResumeList.size() >= 3) {
            return ResponseVo.error("请勿添加过多简历");
        }
        //插入Resume表
        StudentResume studentResume = studentResumeVo.getStudentResume();
        studentResume.setuId(uId);
        resumeMapper.insertSelective(studentResume);

        Integer outId = studentResume.getId();
        //插入subject 表
        insertNewSubject(outId, studentResumeVo);
        //插入Time表
        insertNewTime(outId, studentResumeVo);

        return ResponseVo.success("插入成功");
    }

    @Override
    public ResponseVo updateStudentResume(StudentResumeVo studentResumeVo) {

        //更新StudentResume
        StudentResume studentResume = studentResumeVo.getStudentResume();
        resumeMapper.updateByPrimaryKeySelective(studentResume);

        Integer outId = studentResume.getId();
        //更新对应的Time 表
        timeMapper.deleteByOutKey(outId, UserReqConst.TIME_STUDENT_TYPE);
        insertNewTime(outId, studentResumeVo);
        //更更新对应的Subject 表
        subjectMapper.deleteByOutKey(outId, UserReqConst.SUBJECT_STUDENT_TYPE);
        insertNewSubject(outId, studentResumeVo);

        return ResponseVo.success("简历更新成功");
    }

    public boolean checkIdAndUId(Integer id, Integer uId) {
        //校验uId 与 id 是否一致
        StudentResume checkResume = resumeMapper.selectByPrimaryKey(id);
        if (checkResume == null || !checkResume.getuId().equals(uId)) {
            return false;
        }
        return true;
    }

    @Override
    public ResponseVo getStudentsResumes(GetStudentResumeOrderForm getStudentResumeOrderVo) {

        //分页控制
        PageHelper.startPage(getStudentResumeOrderVo.getPageNum(), getStudentResumeOrderVo.getPageSize());
        //获取StudentResumeList
        List<StudentResume> studentResumeList = resumeMapper.selectAllByOrder(getStudentResumeOrderVo);
        //构建VoList
        List<StudentResumeVo> studentResumeVoList = buildStudentResumeVoList(studentResumeList);
        PageInfo<StudentResumeVo> pageInfo = new PageInfo<>(studentResumeVoList);
        Integer count = resumeMapper.selectAllByOrderCount(getStudentResumeOrderVo);
        return ResponseVo.success(""+count, pageInfo);
    }

    @Override
    public ResponseVo deleteStudentResume(Integer id) {
        //todo 通知 接收到需求 已关闭

        //删除 简历对应的发送和接收
        studentSentMapper.deleteByStudentResumeId(id);
        parentSentMapper.deleteByStudentResumeId(id);
        //删除 对应的time 和 课程
        subjectMapper.deleteByOutKey(id, UserReqConst.SUBJECT_STUDENT_TYPE);
        timeMapper.deleteByOutKey(id, UserReqConst.TIME_STUDENT_TYPE);
        //删除 resume
        resumeMapper.deleteByPrimaryKey(id);
        return ResponseVo.success("删除成功");
    }

    @Override
    public ResponseVo sendStudentResume(StudentSendParentVO studentSendParentVO) {
        //校验简历是否发送超过5次
        List<StudentSent> parentReceiveCount = studentSentMapper.selectByParentNeedId(studentSendParentVO.getpNeedId());
        List<StudentSent> studentSentCount = studentSentMapper.selectByStudentResumeId(studentSendParentVO.getsResumeId());
        if (studentSentCount.size() >= 5) {
            return ResponseVo.error("你的发送已满，请先删除非必要的发送");
        }
        if (parentReceiveCount.size() >= 5) {
            return ResponseVo.error("该家长已收到的简历已满");
        }
        //发送前判断是否发送过
        for (StudentSent studentSent : studentSentCount) {
            if (studentSent.getpNeedId().equals(studentSendParentVO.getpNeedId())) {
                return ResponseVo.error("已发送过，请勿重复发送！");
            }
        }
        //发送简历
        StudentSent studentSent = new StudentSent();
        studentSent.setsResumeId(studentSendParentVO.getsResumeId());
        studentSent.setpNeedId(studentSendParentVO.getpNeedId());
        studentSentMapper.insertSelective(studentSent);
        //发送通知
        if (!messagesService.sendMessageToParent(studentSendParentVO.getpNeedId()))
            return ResponseVo.error("请选择正确的发送目标");

        return ResponseVo.success("简历发送成功");
    }

    public ResponseVo deleteSendStudentResume(StudentSendParentVO studentSendParentVO) {
        int deleteCount = studentSentMapper.deleteBySRIdAndPNId(studentSendParentVO.getsResumeId(), studentSendParentVO.getpNeedId());
        if (deleteCount == 0) {
            return ResponseVo.error("不可匹配的删除");
        }
        return ResponseVo.success("删除发送成功");
    }

    @Override
    public ResponseVo getStudentReceive(Integer id) {
        //获取pNeedIdList
        List<ParentSent> parentSentList = parentSentMapper.selectByStudentResumeId(id);
        List<Integer> pNeedIdList = new ArrayList<Integer>();
        for (ParentSent parentSent : parentSentList) {
            pNeedIdList.add(parentSent.getpNeedId());
        }

        if (pNeedIdList.size() == 0)
            return ResponseVo.success("暂无家长投递");

        //获取pNeedList的NeeList
        List<ParentNeed> parentNeedList = parentNeedMapper.selectByIdList(pNeedIdList);
        //根据NeedList 构建VoList
        List<ParentNeedVo> parentNeedVoList = ParentNeedUtil.buildParentNeedVoList(parentNeedList, timeMapper, subjectMapper);

        return ResponseVo.success("接受的简历查询成功", parentNeedVoList);
    }

    @Override
    public ResponseVo getStudentSent(Integer id) {
        //获取pNeedList
        List<StudentSent> studentSentList = studentSentMapper.selectByStudentResumeId(id);
        List<Integer> pNeedIdList = new ArrayList<Integer>();
        for (StudentSent studentSent : studentSentList) {
            pNeedIdList.add(studentSent.getpNeedId());
        }

        if (pNeedIdList.size() == 0)
            return ResponseVo.success("暂无投递记录");
        //获取pNeedList的NeeList
        List<ParentNeed> parentNeedList = parentNeedMapper.selectByIdList(pNeedIdList);
        //根据NeedList 构建VoList
        List<ParentNeedVo> parentNeedVoList = ParentNeedUtil.buildParentNeedVoList(parentNeedList, timeMapper, subjectMapper);
        return ResponseVo.success("简历投递历史-查询成功", parentNeedVoList);
    }

    @Override
    public ResponseVo getStudentResumeSum() {
        int sum = resumeMapper.selectStudentResumeSum();
        return ResponseVo.success("已经注册的学生简历数", sum);
    }



    //插入新的Subject表
    private void insertNewSubject(Integer outId, StudentResumeVo studentResumeVo) {

        List<Subject> subjectList = studentResumeVo.getSubjectList();
        for (Subject subject : subjectList) {
            subject.setOutId(outId);
            subject.setType(UserReqConst.SUBJECT_STUDENT_TYPE);
        }
        subjectMapper.insertList(subjectList);
    }

    ///设置outId 并插入time表
    private void insertNewTime(Integer outId, StudentResumeVo studentResumeVo) {
        //设置outId 并插入time表
        List<Time> timeList = studentResumeVo.getTimeList();
        for (Time time : timeList) {
            time.setOutId(outId);
            time.setType(UserReqConst.TIME_STUDENT_TYPE);
        }
        timeMapper.insertList(timeList);
    }

    //根据StudentResume 构建 Vo
    private StudentResumeVo buildStudentResumeVo(StudentResume resume) {
        //获取time、subject、receiveCounts
        List<Subject> subjectList = subjectMapper.selectByOutKeyAndType(resume.getId(), UserReqConst.SUBJECT_STUDENT_TYPE);
        List<Time> timeList = timeMapper.selectByOutKeyAndType(resume.getId(), UserReqConst.TIME_STUDENT_TYPE);
        int receiveCounts = parentSentMapper.selectByStudentResumeId(resume.getId()).size();

        //构建Vo
        StudentResumeVo studentResumeVo = new StudentResumeVo();
        studentResumeVo.setStudentResume(resume);
        studentResumeVo.setSubjectList(subjectList);
        studentResumeVo.setTimeList(timeList);
        studentResumeVo.setReceiveCounts(receiveCounts);

        return studentResumeVo;
    }

    //根据StudentResumeList 组装VoList
    private List<StudentResumeVo> buildStudentResumeVoList(List<StudentResume> studentResumeList) {
        //组装Vo List
        List<StudentResumeVo> res = new ArrayList<StudentResumeVo>();
        for (StudentResume resume : studentResumeList) {
            StudentResumeVo studentResumeVo = buildStudentResumeVo(resume);
            res.add(studentResumeVo);
        }
        return res;
    }

    //form表单转Vo
    public StudentResumeVo resumeFormToVo(StudentResumeForm form,Integer uId){
        StudentResumeVo res = new StudentResumeVo();
        //生成规范的性格+爱好+可教授的年级
        String character = StringUtils.join(form.getTags(), " ");
        String hobby = StringUtils.join(form.getHobby(), " ");
        Integer ableClass = ableClassStringToInteger(form.getAbleClassString());
        //生成规范的科目+时间
        List<Subject> subjectList=new ArrayList<>();
        List<Time> timeList=new ArrayList<>();
        for(String time:form.getTimeList()){
            Time temp=new Time();
            temp.setFreeTime(timeStringToInteger(time));
            timeList.add(temp);
        }
        for(String name:form.getSubjectList()){
            Subject temp=new Subject();
            temp.setName(name);
            subjectList.add(temp);
        }

        //将性格+爱好+可教授的年级信息填充进resume中
        StudentResume studentResume = form.getStudentResume();
        studentResume.setuId(uId);
        studentResume.setCharacterCondiction(character);
        studentResume.setHobby(hobby);
        studentResume.setAbleClass(ableClass);

        //填充resume+时间+科目生成Vo
        res.setTimeList(timeList);
        res.setSubjectList(subjectList);
        res.setStudentResume(studentResume);
        return res;
    }
    public Integer timeStringToInteger(String time){
        int res=0;
        String[] split = StringUtils.split(time, " : ");
        if(split[0].equals("周一")){
            res=1;
        }else if(split[0].equals("周二")){
            res=2;
        }else if(split[0].equals("周三")){
            res=3;
        }else if(split[0].equals("周四")){
            res=4;
        }else if(split[0].equals("周五")){
            res=5;
        }else if(split[0].equals("周六")){
            res=6;
        }else if(split[0].equals("周日")){
            res=7;
        }
        if(split[1].equals("上午")){
            res=res*10+1;
        }else if(split[1].equals("下午")){
            res=res*10+2;
        }else if(split[1].equals("晚上")){
            res=res*10+3;
        }
        System.out.println(res);
        return res;
    }
    public Integer ableClassStringToInteger(String ablerClass){
        int res=0;
        if (ablerClass.equals("小学")) {
            res=6;
        }else if(ablerClass.equals("初一")){
            res=7;
        }else if(ablerClass.equals("初二")){
            res=8;
        }else if(ablerClass.equals("初三")){
            res=9;
        }else if(ablerClass.equals("高一")){
            res=10;
        }else if(ablerClass.equals("高二")){
            res=11;
        }else if(ablerClass.equals("高三")){
            res=12;
        }
        System.out.println(res);
        return res;
    }
}
