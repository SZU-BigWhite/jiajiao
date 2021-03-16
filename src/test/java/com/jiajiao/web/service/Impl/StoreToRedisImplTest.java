package com.jiajiao.web.service.Impl;

import com.jiajiao.web.pojo.ParentNeed;
import com.jiajiao.web.service.IMessagesServiceTest;
import com.jiajiao.web.vo.ParentNeedVo;
import com.jiajiao.web.vo.ResponseVo;
import com.jiajiao.web.vo.StudentResumeVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StoreToRedisImplTest extends IMessagesServiceTest {

    @Autowired
    RedisServiceImpl storeToRedis;
    @Autowired
    RecommendServiceImpl recommendService;
    @Test
    public void test1(){
        storeToRedis.storeStudentResume();
    }
    @Test
    public void test2(){
        storeToRedis.storeParentNeed();
    }
//    @Test
//    public void test3(){
//        ParentNeed parentNeed = new ParentNeed();
//        parentNeed.setId(10);
//        storeToRedis.recommendResumeByNeed(parentNeed);
//    }
//    @Test
//    public void test4(){
//        List<StudentResumeVo> studentResumeVoList = storeToRedis.getStudentResumeVoList();
//        for(StudentResumeVo s:studentResumeVoList){
//            System.out.println(s);
//        }
//    }
//    @Test
//    public void test5(){
//        List<ParentNeedVo> studentResumeVoList = storeToRedis.getParentNeedVoList();
//        for(ParentNeedVo s:studentResumeVoList){
//            System.out.println(s);
//        }
//    }
//    @Test
//    public void test6(){
//        recommendService.recommendResumeByNeed(1);
//    }
//    @Test
//    public void test7(){
//        ResponseVo responseVo = recommendService.recommendResumeByNeed(1);
//    }

}