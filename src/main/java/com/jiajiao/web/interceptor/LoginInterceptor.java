package com.jiajiao.web.interceptor;

import com.jiajiao.web.enums.UserReqConst;
import com.jiajiao.web.execption.UserLoginExecption;
import com.jiajiao.web.utils.CookieUtils;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * true 继续往下走，false异常则中断继续走
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie cookie = CookieUtils.getCookieByName(request, UserReqConst.UESR_SESSION);
        if(cookie==null){
            throw new UserLoginExecption();
        }
        return true;
    }
}
