package com.jiajiao.web.service.Impl;

import com.jiajiao.web.pojo.ParentNeed;
import com.jiajiao.web.service.IMessagesServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class StoreToRedisImplTest extends IMessagesServiceTest {

    @Autowired
    StoreToRedisImpl storeToRedis;
    @Test
    public void test1(){
        storeToRedis.storeStudentResume();
    }
    @Test
    public void test2(){
        storeToRedis.storeParentNeed();
    }
    @Test
    public void test3(){
        ParentNeed parentNeed = new ParentNeed();
        parentNeed.setId(10);
        storeToRedis.recommendResumeByNeed(parentNeed);
    }

}