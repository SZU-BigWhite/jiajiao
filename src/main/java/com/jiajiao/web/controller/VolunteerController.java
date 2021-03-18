package com.jiajiao.web.controller;

import com.jiajiao.web.form.VolunteerThingsForm;
import com.jiajiao.web.pojo.VolunteerCollection;
import com.jiajiao.web.service.Impl.UserServiceImpl;
import com.jiajiao.web.service.Impl.VolunteerServiceImpl;
import com.jiajiao.web.utils.CookieUtils;
import com.jiajiao.web.vo.ResponseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class VolunteerController {
    @Autowired
    VolunteerServiceImpl volunteerService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 增加捐赠物品
     * @param request
     * @param volunteerThingsForm
     * @return
     */
    @PostMapping("/add/volunteer/things")
    public ResponseVo addVolunteerThings(HttpServletRequest request, @RequestBody VolunteerThingsForm volunteerThingsForm){
        int uId = CookieUtils.getUIdFromRedis(request,redisTemplate);
        return volunteerService.addVolunteerThings(volunteerThingsForm,uId);
    }

    /**
     * 删除捐赠物品
     * @param request
     * @param id
     * @return
     */
    @DeleteMapping("/delete/volunteer/things")
    public ResponseVo deleteVolunteerThings(HttpServletRequest request,Integer id){
        int uId = CookieUtils.getUIdFromRedis(request,redisTemplate);
        return volunteerService.deleteVolunteerThings(id,uId);
    }

    /**
     * 获取自己的捐赠物品
     * @param request
     * @return
     */
    @GetMapping("/get/volunteer/things/by/uid")
    public ResponseVo getVolunteerThingsByUId(HttpServletRequest request){
        int uId = CookieUtils.getUIdFromRedis(request,redisTemplate);
        return volunteerService.getVolunteerThingsByUId(uId);
    }

    /**
     * 获取项目的捐赠物品
     * @param cId
     * @return
     */
    @GetMapping("/get/volunteer/things/by/cid")
    public ResponseVo getVolunteerThingsByCId(@Param("cId") Integer cId,@Param("status") Integer status){
        return volunteerService.getVolunteerThingsByCId(cId,status);
    }

    /**
     * 添加捐赠项目
     * @param request
     * @param volunteerCollection
     * @return
     */
    @PostMapping("/add/volunteer/collection")
    public ResponseVo addVolunteerCollection(HttpServletRequest request, @RequestBody VolunteerCollection volunteerCollection){
        int uId = CookieUtils.getUIdFromRedis(request,redisTemplate);
        volunteerCollection.setuId(uId);
        return volunteerService.addVolunteerCollection(volunteerCollection);
    }

    /**
     * 更新捐赠项目
     * @param request
     * @param volunteerCollection
     * @return
     */
    @PostMapping("/update/volunteer/collection")
    public ResponseVo updateVolunteerCollection(HttpServletRequest request, @RequestBody VolunteerCollection volunteerCollection){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        return volunteerService.updateVolunteerCollection(volunteerCollection,uId);
    }

    /**
     * 获取所有的捐赠项目
     * @return
     */
    @GetMapping("/get/all/volunteer/collection")
    public ResponseVo selectAllVolunteerCollection(){
        return volunteerService.getAllVolunteerCollection();
    }

    /**
     * 获取捐赠项目byId
     * @return
     */
//    @GetMapping("/get/volunteer/collection/by/id")
//    public ResponseVo getVolunteerCollectionByIdAndOrder(@Param("id") Integer id){
//        return volunteerService.getVolunteerCollectionById(id);
//    }

    /**
     * 获取个人的捐赠项目
     * @param request
     * @return
     */
    @GetMapping("/get/my/volunteer/collection")
    public ResponseVo getMyVolunteerCollection(HttpServletRequest request){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        return volunteerService.getMyVolunteerCollection(uId);
    }

    /**
     * 删除捐赠项目
     * @param request
     * @param id
     * @return
     */
    @DeleteMapping("/delete/volunteer/collection")
    public ResponseVo deleteVolunteerCollection(HttpServletRequest request,Integer id){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        return volunteerService.deleteVolunteerCollection(id,uId);
    }

    @GetMapping("/set/things/status")
    public ResponseVo setThingsStatus(@Param("id") Integer id,@Param("status") Integer status){
        return volunteerService.setThingsStatus(id,status);
    }

    @GetMapping("/set/collection/status")
    public ResponseVo setCollectionStatus(HttpServletRequest request,@Param("id")Integer id,@Param("status") Integer status){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        return volunteerService.setCollectionStatus(id,status,uId);
    }

}
