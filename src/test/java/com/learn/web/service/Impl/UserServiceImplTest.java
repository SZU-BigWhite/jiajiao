package com.learn.web.service.Impl;

import com.learn.web.WebApplicationTests;
import com.learn.web.dao.UserMapper;
import com.learn.web.form.LoginForm;
import com.learn.web.form.RegisterForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserServiceImplTest extends WebApplicationTests {

    @Autowired
    UserServiceImpl userService;
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
}