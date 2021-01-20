package com.jiajiao.web.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiajiao.web.dao.ParentNeedMapper;
import com.jiajiao.web.dao.ParentSentMapper;
import com.jiajiao.web.dao.SubjectMapper;
import com.jiajiao.web.dao.TimeMapper;
import com.jiajiao.web.enums.UserReqConst;
import com.jiajiao.web.pojo.ParentNeed;
import com.jiajiao.web.pojo.ParentSent;
import com.jiajiao.web.pojo.Subject;
import com.jiajiao.web.pojo.Time;
import com.jiajiao.web.service.IParentService;
import com.jiajiao.web.vo.GetParentNeedOrderVo;
import com.jiajiao.web.vo.ParentNeedVo;
import com.jiajiao.web.vo.ParentSendStudentVo;
import com.jiajiao.web.vo.ResponseVo;
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

    @Override
    public ResponseVo getParentNeedByUId(int id) {
        List<ParentNeedVo> parentNeedVoList=new ArrayList<ParentNeedVo>();
        List<ParentNeed> parentNeedList = parentNeedMapper.selectByOutKey(id);
        for (ParentNeed parentNeed:parentNeedList){
            //构建Vo
            ParentNeedVo parentNeedVo=buildParentNeedVo(parentNeed);
            //加入Vo List
            parentNeedVoList.add(parentNeedVo);
        }
        return ResponseVo.success("获取需求成功",parentNeedVoList);
    }

    @Override
    public ResponseVo getParentsNeed() {

        //get ParentNeed
        PageHelper.startPage(1,2);
        List<ParentNeed> parentNeedList = parentNeedMapper.selectAll();
        //构建VoList
        List<ParentNeedVo> parentNeedVoList=new ArrayList<ParentNeedVo>();
        for (ParentNeed parentNeed:parentNeedList){
            ParentNeedVo parentNeedVo = buildParentNeedVo(parentNeed);
            parentNeedVoList.add(parentNeedVo);
        }
        PageInfo<ParentNeedVo> pageInfo=new PageInfo<ParentNeedVo>(parentNeedVoList);
        return ResponseVo.success("获取需求成功",pageInfo);
    }

    @Override
    public ResponseVo getParentsNeedByOrder(GetParentNeedOrderVo parentNeedOrderVo) {

        List<ParentNeed> parentNeedList = parentNeedMapper.selectAllByOrder(parentNeedOrderVo);
        //构建VoList
        List<ParentNeedVo> parentNeedVoList=new ArrayList<ParentNeedVo>();
        for (ParentNeed parentNeed:parentNeedList){
            ParentNeedVo parentNeedVo = buildParentNeedVo(parentNeed);
            parentNeedVoList.add(parentNeedVo);
        }
        return ResponseVo.success("排序成功",parentNeedList);
    }

    @Override
    public ResponseVo addParentNeedByUId(int id,ParentNeedVo parentNeedVo) {
        //todo 一个人最多5个需求

        //插入parentNeed表
        ParentNeed parentNeed = parentNeedVo.getParentNeed();
        parentNeed.setuId(id);
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
        subjectMapper.deleteByOutKey(outId);
        insertNewSubject(outId,parentNeedVo);
        //删除旧的Time表，并插入新Time表
        timeMapper.deleteByOutKey(outId);
        insertNewTime(outId,parentNeedVo);

        return ResponseVo.success("需求更新成功");
    }

    @Override
    public ResponseVo deleteParentNeed(int id) {
        //todo 状态删除
        parentNeedMapper.deleteByPrimaryKey(id);
        return ResponseVo.success("删除成功");
    }

    @Override
    public ResponseVo sendNeedToResume(ParentSendStudentVo pSend) {
        ParentSent parentSent=new ParentSent();
        parentSent.setpNeedId(pSend.getpNeedId());
        parentSent.setsResumeId(pSend.getsResumeId());
        parentSentMapper.insert(parentSent);
        return ResponseVo.success("发送需求成功");
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