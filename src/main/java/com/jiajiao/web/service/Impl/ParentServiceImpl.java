package com.jiajiao.web.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiajiao.web.dao.*;
import com.jiajiao.web.enums.UserReqConst;
import com.jiajiao.web.form.GetParentNeedOrderForm;
import com.jiajiao.web.pojo.*;
import com.jiajiao.web.service.IParentService;
import com.jiajiao.web.utils.StudentResumeUtil;
import com.jiajiao.web.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ParentServiceImpl implements IParentService {
    @Autowired
    ParentNeedMapper parentNeedMapper;
    @Autowired
    SubjectMapper subjectMapper;
    @Autowired
    TimeMapper timeMapper;
    @Autowired
    ParentSentMapper parentSentMapper;
    @Autowired
    StudentSentMapper studentSentMapper;
    @Autowired
    StudentResumeMapper studentResumeMapper;

    @Override
    public ResponseVo getParentNeedByUId(int id) {
        List<ParentNeed> parentNeedList = parentNeedMapper.selectByOutKey(id);
        List<ParentNeedVo> parentNeedVoList = buildParentNeedVoList(parentNeedList);
        return ResponseVo.success("获取需求成功",parentNeedVoList);
    }

    @Override
    public ResponseVo getParentsNeed() {

        //get ParentNeed
        PageHelper.startPage(1,2);
        List<ParentNeed> parentNeedList = parentNeedMapper.selectAll();
        //构建VoList
        List<ParentNeedVo> parentNeedVoList = buildParentNeedVoList(parentNeedList);
        //生成Page信息
        PageInfo<ParentNeedVo> pageInfo=new PageInfo<ParentNeedVo>(parentNeedVoList);
        return ResponseVo.success("获取需求成功",pageInfo);
    }

    @Override
    public ResponseVo getParentsNeedByOrder(GetParentNeedOrderForm parentNeedOrderVo) {

        List<ParentNeed> parentNeedList = parentNeedMapper.selectAllByOrder(parentNeedOrderVo);
        //构建VoList
        List<ParentNeedVo> parentNeedVoList = buildParentNeedVoList(parentNeedList);
        return ResponseVo.success("排序成功",parentNeedList);
    }

    @Override
    public ResponseVo addParentNeedByUId(int uId,ParentNeedVo parentNeedVo) {
        //todo 一个人最多5个需求

        //插入parentNeed表
        ParentNeed parentNeed = parentNeedVo.getParentNeed();
        parentNeed.setuId(uId);
        parentNeedMapper.insert(parentNeed);

        Integer outId = parentNeed.getId();
        //设置outId 并插入subject表
        insertNewSubject(outId,parentNeedVo);
        //设置outId 并插入time表
        insertNewTime(outId,parentNeedVo);

        return ResponseVo.success("需求增加成功");
    }

    @Override
    public ResponseVo updateParentNeed(ParentNeedVo parentNeedVo) {
        //插入parentNeed表
        ParentNeed parentNeed = parentNeedVo.getParentNeed();
        parentNeedMapper.updateByPrimaryKeySelective(parentNeed);

        Integer outId = parentNeed.getId();
        //删除旧的Subject表,并插入新Subject表
        subjectMapper.deleteByOutKey(outId,UserReqConst.SUBJECT_PARENT_TYPE);
        insertNewSubject(outId,parentNeedVo);
        //删除旧的Time表，并插入新Time表
        timeMapper.deleteByOutKey(outId,UserReqConst.TIME_PARENT_TYPE);
        insertNewTime(outId,parentNeedVo);

        return ResponseVo.success("需求更新成功");
    }

    public boolean checkIdAndUId(Integer id,Integer uId){
        ParentNeed parentNeed = parentNeedMapper.selectByPrimaryKey(id);
        if(parentNeed==null||!parentNeed.getuId().equals(uId)){
            return false;
        }
        return true;
    }

    @Override
    public ResponseVo deleteParentNeed(int id) {
        //删除 对应的发送和接收
        studentSentMapper.deleteByParentNeedId(id);
        parentSentMapper.deleteByParentNeedId(id);
        //todo 状态删除 删除对应的需求
        parentNeedMapper.deleteByPrimaryKey(id);
        subjectMapper.deleteByOutKey(id,UserReqConst.SUBJECT_PARENT_TYPE);
        timeMapper.deleteByOutKey(id,UserReqConst.TIME_PARENT_TYPE);
        return ResponseVo.success("删除成功");
    }

    @Override
    public ResponseVo sendNeedToResume(ParentSendStudentVo pSend) {
        //校验 发送 和 接收 次数
        List<ParentSent> parentSentCount = parentSentMapper.selectByParentNeedId(pSend.getpNeedId());
        List<ParentSent> studentReceiveCount = parentSentMapper.selectByStudentResumeId(pSend.getsResumeId());
        if(parentSentCount.size()>=5){
            return ResponseVo.error("你的发送已满，请先删除非必要的发送");
        }
        if(studentReceiveCount.size()>=5){
            return ResponseVo.error("该学生已收到的需求已满");
        }
        //发送需求
        ParentSent parentSent=new ParentSent();
        parentSent.setpNeedId(pSend.getpNeedId());
        parentSent.setsResumeId(pSend.getsResumeId());
        parentSentMapper.insert(parentSent);
        return ResponseVo.success("发送需求成功");
    }

    public ResponseVo deleteSendNeedToResume(ParentSendStudentVo psend){
        int deleteCount = parentSentMapper.deleteByPNIdAndSRId(psend.getpNeedId(), psend.getsResumeId());
        if(deleteCount==0){
            return ResponseVo.error("不可匹配的删除");
        }
        return ResponseVo.success("删除发送成功");
    }

    @Override
    public ResponseVo getParentReceive(Integer id) {
        //获取sResumeIdList
        List<StudentSent> studentSentList = studentSentMapper.selectByParentNeedId(id);
        List<Integer> sResumeIdList=new ArrayList<Integer>();
        for(StudentSent studentSent:studentSentList){
            sResumeIdList.add(studentSent.getsResumeId());
        }
        if(sResumeIdList.size()==0)
            return ResponseVo.success("暂未收到学生的简历");
        //获取StudentResumeList
        List<StudentResume> studentResumeList = studentResumeMapper.selectByIdList(sResumeIdList);
        //根据StudentResumeList 构建VoList
        List<StudentResumeVo> studentResumeVoList = StudentResumeUtil.buildStudentResumeVoList(studentResumeList, timeMapper, subjectMapper);

        return ResponseVo.success("收到学生的简历查询成功",studentResumeVoList);
    }

    @Override
    public ResponseVo getParentSent(Integer id) {
        //获取sResumeIdList
        List<ParentSent> parentSentList = parentSentMapper.selectByParentNeedId(id);
        List<Integer> sResumeIdList=new ArrayList<Integer>();
        for (ParentSent parentSent:parentSentList){
            sResumeIdList.add(parentSent.getsResumeId());
        }
        if(sResumeIdList.size()==0)
            return ResponseVo.success("此需求暂无发送历史");

        //获取StudentResumeList
        List<StudentResume> studentResumeList = studentResumeMapper.selectByIdList(sResumeIdList);
        //构建VoList
        List<StudentResumeVo> studentResumeVoList = StudentResumeUtil.buildStudentResumeVoList(studentResumeList,timeMapper,subjectMapper);

        return ResponseVo.success("发送历史查询成功",studentResumeVoList);
    }

    //根据ParentNeed 构建 Vo
    private ParentNeedVo buildParentNeedVo(ParentNeed parentNeed){
        //获取time、subject
        List<Subject> subjectList = subjectMapper.selectByOutKeyAndType(parentNeed.getId(), UserReqConst.SUBJECT_PARENT_TYPE);
        List<Time> timeList = timeMapper.selectByOutKeyAndType(parentNeed.getId(),UserReqConst.TIME_PARENT_TYPE);
        //构建Vo
        ParentNeedVo parentNeedVo=new ParentNeedVo();
        parentNeedVo.setParentNeed(parentNeed);
        parentNeedVo.setSubjectList(subjectList);
        parentNeedVo.setTimeList(timeList);
        return parentNeedVo;
    }
    //根据ParentNeedList 构建VoList
    private List<ParentNeedVo> buildParentNeedVoList(List<ParentNeed> parentNeedList){
        List<ParentNeedVo> parentNeedVoList=new ArrayList<ParentNeedVo>();
        for (ParentNeed parentNeed:parentNeedList){
            ParentNeedVo parentNeedVo = buildParentNeedVo(parentNeed);
            parentNeedVoList.add(parentNeedVo);
        }
        return parentNeedVoList;
    }
    //插入新的Subject表
    private void insertNewSubject(Integer outId,ParentNeedVo parentNeedVo){

        List<Subject> subjectList = parentNeedVo.getSubjectList();
        for (Subject subject:subjectList){
            subject.setOutId(outId);
            subject.setType(UserReqConst.SUBJECT_PARENT_TYPE);
        }
        subjectMapper.insertList(subjectList);
    }
    ///设置outId 并插入time表
    private void insertNewTime(Integer outId,ParentNeedVo parentNeedVo){
        //设置outId 并插入time表
        List<Time> timeList = parentNeedVo.getTimeList();
        for(Time time:timeList){
            time.setOutId(outId);
            time.setType(UserReqConst.TIME_PARENT_TYPE);
        }
        timeMapper.insertList(timeList);
    }
    public List<Subject> getSubject(int out_id){
        return subjectMapper.selectByOutKeyAndType(out_id,UserReqConst.SUBJECT_PARENT_TYPE);
    }
}
