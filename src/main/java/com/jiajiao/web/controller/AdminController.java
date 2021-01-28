package com.jiajiao.web.controller;

import com.jiajiao.web.service.Impl.AdminServiceImpl;
import com.jiajiao.web.utils.CookieUtils;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class AdminController {

    /**
     * redis 保存 uId简历违规次数 -- hashmap
     *       保存 封存账号       -- hashmap
     */
    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    AdminServiceImpl adminService;
    @DeleteMapping("/admin/delete/student/resume")
    public ResponseVo adminDeleteStudentResum(Integer id, HttpServletRequest request){
        int uId = CookieUtils.getUIdFromRedis(request,redisTemplate);
        if (!adminService.checkAdmin(uId)) {
            return ResponseVo.error("你不是管理员!");
        }

        ResponseVo res = adminService.deleteStudentResume(id);
        return res;
    }
    @DeleteMapping("/admin/delete/parent/need")
    public ResponseVo adminDeleteParentNeed(Integer id, HttpServletRequest request){
        //检查 是否为管理员
        int uId = CookieUtils.getUIdFromRedis(request,redisTemplate);
        if (!adminService.checkAdmin(uId)) {
            return ResponseVo.error("你不是管理员!");
        }

        ResponseVo res = adminService.deleteParentNeed(id);
        return res;
    }

}
