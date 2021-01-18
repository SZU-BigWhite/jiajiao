package com.jiajiao.web.service;

import com.jiajiao.web.form.LoginForm;
import com.jiajiao.web.form.RegisterForm;
import com.jiajiao.web.vo.ResponseVo;

public interface IUserService {
    public ResponseVo Register(RegisterForm form);
    public ResponseVo Login(LoginForm form);
}
