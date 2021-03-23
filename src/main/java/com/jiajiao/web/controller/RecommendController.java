package com.jiajiao.web.controller;

import com.jiajiao.web.service.Impl.RecommendServiceImpl;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpCookie;

@RestController
public class RecommendController {
    @Autowired
    RecommendServiceImpl recommendService;

    /**
     * 根据需求推荐简历
     * @param request
     * @param pNId
     * @return
     */
    @GetMapping("/recommend/resume/by/need")
    public ResponseVo recommendResumeByNeed(HttpServletRequest request,Integer pNId){
        return recommendService.recommendResumeByNeed(pNId);
    }

    /**
     * 根据简历推荐需求
     * @param request
     * @param sRId
     * @return
     */
    @GetMapping("/recommend/need/by/resume")
    public ResponseVo recommendNeedByResume(HttpServletRequest request,Integer sRId){
        return recommendService.recommendNeedByResume(sRId);
    }

    /**
     * 根据简历推荐简历
     * @param request
     * @param sRId
     * @return
     */
    @GetMapping("/recommend/resume/by/resume")
    public ResponseVo recommendResumeByResume(HttpServletRequest request,Integer sRId){
        return recommendService.recommendResumeByResume(sRId);
    }

    /**
     * 根据需求推荐需求
     * @param request
     * @param pNId
     * @return
     */
    @GetMapping("/recommend/need/by/need")
    public ResponseVo recommendNeedByNeed(HttpServletRequest request,Integer pNId){
        return recommendService.recommendNeedByNeed(pNId);
    }
}
