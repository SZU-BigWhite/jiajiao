package com.jiajiao.web.service.Impl;

import com.jiajiao.web.dao.*;
import com.jiajiao.web.enums.UserReqConst;
import com.jiajiao.web.form.GetStudentResumeOrderForm;
import com.jiajiao.web.pojo.*;
import com.jiajiao.web.service.IStudentService;
import com.jiajiao.web.utils.ParentNeedUtil;
import com.jiajiao.web.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

    @Override
    public ResponseVo getStudentAcademy() {
        List<StudentAcademy> academyList = academyMapper.selectAll();
        return ResponseVo.success("学院返回成功",academyList);
    }

    @Override
    public ResponseVo getStudentResumeByUId(Integer uId) {

        List<StudentResume> resumeList = resumeMapper.selectByUId(uId);
        List<StudentResumeVo> studentResumeVoList = buildStudentResumeVoList(resumeList);
        return ResponseVo.success("个人的简历获取成功",studentResumeVoList);

    }

    @Override
    public ResponseVo addStudentResume(StudentResumeVo studentResumeVo,Integer uId) {
        //插入Resume表
        StudentResume studentResume=studentResumeVo.getStudentResume();
        studentResume.setuId(uId);
        resumeMapper.insertSelective(studentResume);

        Integer outId = studentResume.getId();
        //插入subject 表
        insertNewSubject(outId,studentResumeVo);
        //插入Time表
        insertNewTime(outId,studentResumeVo);

        return ResponseVo.success("插入成功");
    }

    @Override
    public ResponseVo updateStudentResume(StudentResumeVo studentResumeVo) {
        //更新StudentResume
        StudentResume studentResume = studentResumeVo.getStudentResume();
        resumeMapper.updateByPrimaryKeySelective(studentResume);

        Integer outId = studentResume.getId();
        //更新对应的Time 表
        timeMapper.deleteByOutKey(outId,UserReqConst.TIME_STUDENT_TYPE);
        insertNewTime(outId,studentResumeVo);
        //更更新对应的Subject 表
        subjectMapper.deleteByOutKey(outId,UserReqConst.SUBJECT_STUDENT_TYPE);
        insertNewSubject(outId,studentResumeVo);

        return ResponseVo.success("简历更新成功");
    }

    @Override
    public ResponseVo getStudentsResumes(GetStudentResumeOrderForm getStudentResumeOrderVo) {
        //获取StudentResumeList
        List<StudentResume> studentResumeList = resumeMapper.selectAllByOrder(getStudentResumeOrderVo);
        //构建VoList
        List<StudentResumeVo> studentResumeVoList = buildStudentResumeVoList(studentResumeList);

        return ResponseVo.success("学生简历获取成功",studentResumeVoList);
    }

    @Override
    public ResponseVo deleteStudentResume(Integer id, Integer uId) {
        resumeMapper.deleteByPrimaryKeyAndUId(id, uId);
        subjectMapper.deleteByOutKey(id,UserReqConst.SUBJECT_STUDENT_TYPE);
        timeMapper.deleteByOutKey(id,UserReqConst.TIME_STUDENT_TYPE);
        return ResponseVo.success("删除成功");
    }

    @Override
    public ResponseVo sendStudentResume(StudentSendParentVO studentSendParentVO) {
        StudentSent studentSent=new StudentSent();
        studentSent.setsResumeId(studentSendParentVO.getsResumeId());
        studentSent.setpNeedId(studentSendParentVO.getpNeedId());
        studentSentMapper.insertSelective(studentSent);
        return ResponseVo.success("简历发送成功");
    }

    @Override
    public ResponseVo getStudentReceive(Integer id) {
        //获取pNeedIdList
        List<ParentSent> parentSentList = parentSentMapper.selectByStudentResumeId(id);
        List<Integer> pNeedIdList=new ArrayList<Integer>();
        for(ParentSent parentSent:parentSentList){
            pNeedIdList.add(parentSent.getpNeedId());
        }

        if(pNeedIdList.size()==0)
            return ResponseVo.success("暂无家长投递");

        //获取pNeedList的NeeList
        List<ParentNeed> parentNeedList = parentNeedMapper.selectByIdList(pNeedIdList);
        //根据NeedList 构建VoList
        List<ParentNeedVo> parentNeedVoList = ParentNeedUtil.buildParentNeedVoList(parentNeedList,timeMapper,subjectMapper);

        return ResponseVo.success("接受的简历查询成功",parentNeedVoList);
    }

    @Override
    public ResponseVo getStudentSent(Integer id) {
        //获取pNeedList
        List<StudentSent> studentSentList = studentSentMapper.selectByStudentResumeId(id);
        List<Integer> pNeedIdList=new ArrayList<Integer>();
        for (StudentSent studentSent:studentSentList){
            pNeedIdList.add(studentSent.getpNeedId());
        }

        if(pNeedIdList.size()==0)
            return ResponseVo.success("暂无投递记录");
        //获取pNeedList的NeeList
        List<ParentNeed> parentNeedList = parentNeedMapper.selectByIdList(pNeedIdList);
        //根据NeedList 构建VoList
        List<ParentNeedVo> parentNeedVoList = ParentNeedUtil.buildParentNeedVoList(parentNeedList,timeMapper,subjectMapper);
        return ResponseVo.success("简历投递历史-查询成功",parentNeedVoList);
    }

    //插入新的Subject表
    private void insertNewSubject(Integer outId,StudentResumeVo studentResumeVo){

        List<Subject> subjectList = studentResumeVo.getSubjectList();
        for (Subject subject:subjectList){
            subject.setOutId(outId);
            subject.setType(UserReqConst.SUBJECT_STUDENT_TYPE);
        }
        subjectMapper.insertList(subjectList);
    }
    ///设置outId 并插入time表
    private void insertNewTime(Integer outId,StudentResumeVo studentResumeVo){
        //设置outId 并插入time表
        List<Time> timeList = studentResumeVo.getTimeList();
        for(Time time:timeList){
            time.setOutId(outId);
            time.setType(UserReqConst.TIME_STUDENT_TYPE);
        }
        timeMapper.insertList(timeList);
    }

    //根据StudentResume 构建 Vo
    private StudentResumeVo buildStudentResumeVo(StudentResume resume){
        //获取time、subject
        List<Subject> subjectList = subjectMapper.selectByOutKeyAndType(resume.getId(), UserReqConst.SUBJECT_STUDENT_TYPE);
        List<Time> timeList = timeMapper.selectByOutKeyAndType(resume.getId(),UserReqConst.TIME_STUDENT_TYPE);
        //构建Vo
        StudentResumeVo studentResumeVo=new StudentResumeVo();
        studentResumeVo.setStudentResume(resume);
        studentResumeVo.setSubjectList(subjectList);
        studentResumeVo.setTimeList(timeList);
        return studentResumeVo;
    }
    //根据StudentResumeList 组装VoList
    private List<StudentResumeVo> buildStudentResumeVoList(List<StudentResume> studentResumeList){
        //组装Vo List
        List<StudentResumeVo> res=new ArrayList<StudentResumeVo>();
        for (StudentResume resume:studentResumeList){
            StudentResumeVo studentResumeVo=buildStudentResumeVo(resume);
            res.add(studentResumeVo);
        }
        return res;
    }
}
