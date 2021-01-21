package com.jiajiao.web.service.Impl;

import com.jiajiao.web.WebApplicationTests;
import com.jiajiao.web.pojo.Subject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;


public class ParentServiceImplTest extends WebApplicationTests {
    @Autowired
    ParentServiceImpl parentService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Test
    public void getSubjectList(){
        List<Subject> subject = parentService.getSubject(1);
        System.out.println(subject);

    }
    @Test
    public void testRedis(){
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String s = opsForValue.get("18312872509");
        System.out.println(s);
    }
}