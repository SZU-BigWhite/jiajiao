package com.jiajiao.web.service.Impl;

import com.jiajiao.web.WebApplicationTests;
import com.jiajiao.web.pojo.Subject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ParentServiceImplTest extends WebApplicationTests {
    @Autowired
    ParentServiceImpl parentService;

    @Test
    public void getSubjectList(){
        List<Subject> subject = parentService.getSubject(1);
        System.out.println(subject);

    }
}