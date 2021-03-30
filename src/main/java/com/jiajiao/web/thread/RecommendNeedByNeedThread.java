package com.jiajiao.web.thread;

import com.jiajiao.web.service.Impl.RecommendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class RecommendNeedByNeedThread extends Thread {
    RecommendServiceImpl recommendService;
    Integer pNId;
    public RecommendNeedByNeedThread(){}
    public RecommendNeedByNeedThread(Integer pNId,RecommendServiceImpl recommendService){
        this.pNId=pNId;
        this.recommendService=recommendService;
    }

    @Override
    public void run() {
        recommendService.recommendNeedByNeed(pNId);
    }
}
