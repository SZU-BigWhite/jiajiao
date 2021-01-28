package com.jiajiao.web.utils;

import com.jiajiao.web.enums.UserReqConst;
import com.jiajiao.web.execption.UnLoginExecption;
import com.jiajiao.web.execption.UserLoginExecption;
import org.omg.CORBA.UserException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

    public static Cookie getCookieByName(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        if(cookies==null)
            return null;
        Cookie cookie=null;
        for(Cookie c:cookies){
            if(c.getName().equals(UserReqConst.UESR_SESSION)){
                cookie=c;
                break;
            }
        }
        return cookie;
    }
    public static int getUIdFromRedis(HttpServletRequest request, StringRedisTemplate redisTemplate){
        Cookie cookie = getCookieByName(request, UserReqConst.UESR_SESSION);
        if(cookie==null)
            throw new UnLoginExecption();
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String uIdStr = opsForValue.get(cookie.getValue());
        return Integer.parseInt(uIdStr);
    }
}
