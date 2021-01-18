package com.learn.web.service;

import com.learn.web.form.LoginForm;
import com.learn.web.form.RegisterForm;
import com.learn.web.vo.ResponseVo;

public interface IUserService {
    public ResponseVo Register(RegisterForm form);
    public ResponseVo Login(LoginForm form);
}
