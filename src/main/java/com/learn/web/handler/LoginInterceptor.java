package com.learn.web.handler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//实现HandleInterceptor接口，编写一个拦截器
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    //在Mapping后调用api方法前，会调用preHandle方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //输出请求链接
        System.out.println("hi,"+" "+request.getServletPath());
        //重定位
//        response.sendRedirect(request.getContextPath()+"/toLogin");
        return true;        //true --往后的程序会执行  ---false 往后的程序不会执行
    }
}
