package com.jiajiao.web.service.Impl;

import com.jiajiao.web.WebApplicationTests;
import com.jiajiao.web.dao.TimeMapper;
import com.jiajiao.web.form.LoginForm;
import com.jiajiao.web.form.RegisterForm;
import com.jiajiao.web.pojo.Time;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        registerForm.setPhone(18312872234L);
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
        time.setType(1);
        time.setWeekday(7);
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