package com.jiajiao.web.service;

import com.jiajiao.web.pojo.ParentNeed;
import com.jiajiao.web.vo.ResponseVo;

public interface IRecommendService {
    ResponseVo recommendNeedByNeed(Integer pNId);
    ResponseVo recommendResumeByNeed(Integer pNId);
    ResponseVo recommendNeedByResume(Integer sRId);
    ResponseVo recommendResumeByResume(Integer sRId);
}
