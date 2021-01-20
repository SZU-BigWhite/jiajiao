package com.jiajiao.web.service;

import com.jiajiao.web.form.LoginForm;
import com.jiajiao.web.form.RegisterForm;
import com.jiajiao.web.pojo.User;
import com.jiajiao.web.vo.ResponseVo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IUserService {
    public ResponseVo setCode(Long phone);
    public ResponseVo register(RegisterForm form);
    public ResponseVo login(LoginForm form);
    public ResponseVo logout(Cookie cookie);
    public void setSession(User user, HttpServletResponse response);
    public ResponseVo updateUser(User user,Cookie cookie);
}
