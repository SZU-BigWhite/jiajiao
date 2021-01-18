package com.learn.web.service.Impl;

import com.learn.web.WebApplicationTests;
import com.learn.web.dao.TimeMapper;
import com.learn.web.dao.UserMapper;
import com.learn.web.form.LoginForm;
import com.learn.web.form.RegisterForm;
import com.learn.web.pojo.Time;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class UserServiceImplTest extends WebApplicationTests {

    @Autowired
    UserServiceImpl userService;
    SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss" );
    @Autowired
    TimeMapper timeMapper;
    @Test
    public void register() {
        RegisterForm registerForm=new RegisterForm();
        registerForm.setCode(1234);
        registerForm.setPhone(18312872507L);
        userService.Register(registerForm);
    }

    @Test
    public void login() {
        LoginForm loginForm = new LoginForm();
        loginForm.setPhone(18312872507L);
        userService.Login(loginForm);
    }

    @Test
    //测试 时间对象
    public void testTimeObject()  {
        Time time=new Time();
        try {
            //开始时间
            String begin="12:30:00";
            Date beginDate = sdf.parse(begin);
            time.setBeginTime(beginDate);
            //结束时间
            String end="18:30:00";
            Date endDate = sdf.parse(end);
            time.setEndTime(endDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        time.setOutId(12);
        time.setType((byte) 1);
        time.setWeekday((byte) 7);
        timeMapper.insert(time);
    }
    @Test
    public void testSelectTimeObject(){
        Time[] times = timeMapper.selectByOutKey(12);

        for(Time time : times){
            System.out.println(time);
            Date begin = time.getBeginTime();
            String beginTime = sdf.format(begin);
            String endTime = sdf.format(time.getEndTime());
            System.out.println("biginTime:"+beginTime+",endTIme:"+endTime);
        }

    }
}