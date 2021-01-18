package com.jiajiao.web.controller;

import com.jiajiao.web.form.LoginForm;
import com.jiajiao.web.form.RegisterForm;
import com.jiajiao.web.service.Impl.UserServiceImpl;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseVo<String> register(@RequestBody RegisterForm form){
        System.out.println(form);
        //todo 设置session
        return userService.Register(form);
    }
    @PostMapping("/login")
    public ResponseVo login(@RequestBody LoginForm form){
        System.out.println(form);
        //todo 设置session
//        return userService.Login(form);
        return ResponseVo.error();

    }
}
