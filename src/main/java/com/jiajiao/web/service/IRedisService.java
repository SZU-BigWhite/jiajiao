package com.jiajiao.web.service;

import com.jiajiao.web.pojo.ParentNeed;
import com.jiajiao.web.vo.ParentNeedVo;
import com.jiajiao.web.vo.ResponseVo;
import com.jiajiao.web.vo.StudentResumeVo;

import java.util.List;

public interface IRedisService {
    List<StudentResumeVo> getStudentResumeVoList();
    List<ParentNeedVo> getParentNeedVoList();
    void addNeedRecommend(Integer pNId,StudentResumeVo studentResumeVo,double score);
    List<StudentResumeVo> getNeedRecommend(Integer pNId);
    void addResumeRecommend(Integer sRId,ParentNeedVo parentNeedVo,double score);
    List<ParentNeedVo> getResumeRecommend(Integer sRId);
    void addRelativeResume(Integer sRId,StudentResumeVo studentResumeVo,double score);
    List<StudentResumeVo> getRelativeResume(Integer sRId);
    void addRelativeNeed(Integer sRId,ParentNeedVo parentNeedVo,double score);
    List<ParentNeedVo> getRelativeNeed(Integer pNId);
}
