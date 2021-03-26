package com.jiajiao.web.controller;

import com.jiajiao.web.enums.UserReqConst;
import com.jiajiao.web.form.LoginForm;
import com.jiajiao.web.form.RegisterForm;
import com.jiajiao.web.pojo.User;
import com.jiajiao.web.service.Impl.SendSmsImpl;
import com.jiajiao.web.service.Impl.UserServiceImpl;
import com.jiajiao.web.utils.CookieUtils;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    StringRedisTemplate redisTemplate;


    /**
     * 注册并登录接口
     * @param form
     * @param response
     * @return
     */
    @PostMapping("/register")
    public ResponseVo<String> register(@Valid @RequestBody RegisterForm form,HttpServletRequest request, HttpServletResponse response){

        ResponseVo res = userService.register(form);
        if(res.getStatus().equals(200)) {
            userService.setSession((User)res.getData(),response);
        }
        return res;
    }

    /**
     * 登录接口
     * @param form
     * @param response
     * @return
     */
    @PostMapping("/login")
    public ResponseVo login(@Valid @RequestBody LoginForm form,HttpServletRequest request, HttpServletResponse response){

        ResponseVo res=userService.login(form);
        //设置session
        if(res.getStatus().equals(200)){
            userService.setSession((User)res.getData(),response);
        }
        return res;
    }

    /**
     * 登出退出接口
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public ResponseVo login(HttpServletRequest request,HttpServletResponse response){
        //获取保存在Cookie 中的user_session
        Cookie cookie = CookieUtils.getCookieByName(request, UserReqConst.UESR_SESSION);

        //删除Session、Cookie
        ResponseVo res = userService.logout(cookie);
        if(res.getStatus().equals(200)){
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return res;
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @PostMapping("/update/user")
    public ResponseVo updateUser(@RequestBody User user,HttpServletRequest request){

        int uId = CookieUtils.getUIdFromRedis(request,redisTemplate);
        ResponseVo res = userService.updateUser(user,uId);
        return res;
    }

    @GetMapping("/get/data/sum")
    public ResponseVo getDataSum(){
        return userService.getDataSum();
    }

}
