package com.jiajiao.web.controller;

import com.jiajiao.web.form.VolunteerThingsForm;
import com.jiajiao.web.pojo.VolunteerCollection;
import com.jiajiao.web.service.Impl.UserServiceImpl;
import com.jiajiao.web.service.Impl.VolunteerServiceImpl;
import com.jiajiao.web.utils.CookieUtils;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class VolunteerController {
    @Autowired
    VolunteerServiceImpl volunteerService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    StringRedisTemplate redisTemplate;

    @PostMapping("/add/volunteer/things")
    public ResponseVo addVolunteerThings(HttpServletRequest request, @RequestBody VolunteerThingsForm volunteerThingsForm){
        int uId = CookieUtils.getUIdFromRedis(request,redisTemplate);
        return volunteerService.addVolunteerThings(volunteerThingsForm,uId);
    }
    @PostMapping("/add/Volunteer/collection")
    public ResponseVo addVolunteerCollection(HttpServletRequest request, @RequestBody VolunteerCollection volunteerCollection){
        int uId = CookieUtils.getUIdFromRedis(request,redisTemplate);
        volunteerCollection.setuId(uId);
        return volunteerService.addVolunteerCollection(volunteerCollection);
    }
}
