package com.jiajiao.web.controller;

import com.jiajiao.web.service.Impl.ParentServiceImpl;
import com.jiajiao.web.utils.CookieUtils;
import com.jiajiao.web.vo.GetParentNeedOrderVo;
import com.jiajiao.web.vo.ParentNeedVo;
import com.jiajiao.web.vo.ParentSendStudentVo;
import com.jiajiao.web.vo.ResponseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ParentController {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ParentServiceImpl parentService;

    /**
     * parent 获取自身的需求表
     * @param request
     * @return
     */
    @GetMapping("/parent/get/needlist")
    public ResponseVo getParentNeedList(HttpServletRequest request){

        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        ResponseVo res = parentService.getParentNeedByUId(uId);

        return res;
    }

    /**
     * 获取所有的 parent 的需求表
     * @return
     */
    @GetMapping("/get/parents/needlist")
    public ResponseVo getParentsNeedList(){
        ResponseVo res = parentService.getParentsNeed();
        return res;
    }

    /**
     * 根据筛选因素 获取 parent 的需求表
     * @param parentOrderVo
     * @return
     */
    @GetMapping("get/parents/needlists/order")
    public ResponseVo getParentsNeedListByOrder(GetParentNeedOrderVo parentOrderVo){
        ResponseVo res = parentService.getParentsNeedByOrder(parentOrderVo);
        return res;
    }

    /**
     * parent 添加自身的需求表
     * @param parentNeedVo
     * @param request
     * @return
     */
    @PostMapping("/parent/add/need")
    public ResponseVo addParentNeed(@RequestBody ParentNeedVo parentNeedVo,HttpServletRequest request){

        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        ResponseVo res = parentService.addParentNeedByUId(uId, parentNeedVo);
        return res;
    }

    /**
     * parent 更新自身的需求表
     * @param parentNeedVo
     * @return
     */
    @PostMapping("/parent/update/need")
    public ResponseVo updateParentNeed(@RequestBody ParentNeedVo parentNeedVo){
        //todo 是否需要校验uid->id的对应
        ResponseVo res = parentService.updateParentNeed(parentNeedVo);
        return res;
    }

    /**
     * parent 删除需求表
     * @param id
     * @return
     */
    @DeleteMapping("/parent/delete/need")
    public ResponseVo deleteParentNeed(@Param("id") Integer id){
        ResponseVo res = parentService.deleteParentNeed(id);
        return res;
    }

    /**
     * 发送需求给 学生
     * @param parentSendStudentVo
     * @return
     */
    @PostMapping("/parent/send/need")
    public ResponseVo sendParentNeed(@RequestBody ParentSendStudentVo parentSendStudentVo){
        ResponseVo res = parentService.sendNeedToResume(parentSendStudentVo);
        return res;
    }
    /**
     * 查看自己发送过的需求
     */


}
