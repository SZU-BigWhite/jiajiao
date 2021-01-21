package com.jiajiao.web.utils;

import com.jiajiao.web.dao.SubjectMapper;
import com.jiajiao.web.dao.TimeMapper;
import com.jiajiao.web.enums.UserReqConst;
import com.jiajiao.web.pojo.ParentNeed;
import com.jiajiao.web.pojo.Subject;
import com.jiajiao.web.pojo.Time;
import com.jiajiao.web.vo.ParentNeedVo;

import java.util.ArrayList;
import java.util.List;

public class ParentNeedUtil {
    //根据ParentNeed 构建 Vo
    public static ParentNeedVo buildParentNeedVo(ParentNeed parentNeed,TimeMapper timeMapper,SubjectMapper subjectMapper){
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
    public static List<ParentNeedVo> buildParentNeedVoList(List<ParentNeed> parentNeedList,TimeMapper timeMapper,SubjectMapper subjectMapper){
        List<ParentNeedVo> parentNeedVoList=new ArrayList<ParentNeedVo>();
        for (ParentNeed parentNeed:parentNeedList){
            ParentNeedVo parentNeedVo = buildParentNeedVo(parentNeed,timeMapper,subjectMapper);
            parentNeedVoList.add(parentNeedVo);
        }
        return parentNeedVoList;
    }
}
