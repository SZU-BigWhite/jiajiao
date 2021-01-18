package com.learn.web.controller;

import com.learn.web.form.LoginForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class TestValidation {

    @PostMapping("/Login")
    public String toLogin(@Valid LoginForm loginForm, HttpServletRequest httpServletRequest){

        System.out.println(loginForm);
        return "123";
    }
}
