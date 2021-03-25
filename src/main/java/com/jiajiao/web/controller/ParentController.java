package com.jiajiao.web.controller;

import com.jiajiao.web.form.ParentNeedForm;
import com.jiajiao.web.service.Impl.ParentServiceImpl;
import com.jiajiao.web.utils.CookieUtils;
import com.jiajiao.web.form.GetParentNeedOrderForm;
import com.jiajiao.web.vo.ParentNeedVo;
import com.jiajiao.web.vo.ParentSendStudentVo;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    @GetMapping("/get/parent/need")
    public ResponseVo getParentNeedList(HttpServletRequest request){

        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        ResponseVo res = parentService.getParentNeedByUId(uId);

        return res;
    }

    /**
     * @deprecated
     * 使用默认order查询，则获取所有list
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
    @PostMapping("/get/parents/needlist/order")
    public ResponseVo getParentsNeedListByOrder(@RequestBody GetParentNeedOrderForm parentOrderVo){
        System.out.println(parentOrderVo);
        ResponseVo res = parentService.getParentsNeedByOrder(parentOrderVo);
        return res;
    }

    /**
     * parent 添加自身的需求表
     * @param parentNeedForm
     * @param request
     * @return
     */
    @PostMapping("/add/parent/need")
    public ResponseVo addParentNeed(@RequestBody ParentNeedForm parentNeedForm, HttpServletRequest request){
        System.out.println(parentNeedForm);
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        ParentNeedVo parentNeedVo = parentService.parentNeedFormToVo(parentNeedForm,uId);
        ResponseVo res = parentService.addParentNeedByUId(uId, parentNeedVo);
        return res;
    }

    /**
     * parent 更新自身的需求表
     * @param parentNeedForm
     * @return
     */
    @PostMapping("/update/parent/need")
    public ResponseVo updateParentNeed(@RequestBody ParentNeedForm parentNeedForm,HttpServletRequest request){
        //校验uid->id的对应
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        if(!parentService.checkIdAndUId(parentNeedForm.getParentNeed().getId(),uId)){
            return ResponseVo.error("无法操作他人的需求");
        }
        ParentNeedVo parentNeedVo = parentService.parentNeedFormToVo(parentNeedForm,uId);

        ResponseVo res = parentService.updateParentNeed(parentNeedVo);
        return res;
    }

    /**
     * parent 删除需求表
     * @param id
     * @return
     */
    @DeleteMapping("/delete/parent/need")
    public ResponseVo deleteParentNeed(@RequestParam("id") Integer id,HttpServletRequest request){
        //校验uid->id的对应
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        if(!parentService.checkIdAndUId(id,uId)){
            return ResponseVo.error("无法操作他人的需求");
        }

        ResponseVo res = parentService.deleteParentNeed(id);
        return res;
    }

    /**
     * 根据id获取简历
     * @param id
     * @return
     */
    @GetMapping("/get/parent/nedd/by/id")
    public ResponseVo getParentNeedById(Integer id){
        return parentService.getParentNeedById(id);
    }

    /**
     * 发送需求给 学生
     * @param parentSendStudentVo
     * @return
     */
    @PostMapping("/send/parent/need")
    public ResponseVo sendParentNeed(@Valid @RequestBody ParentSendStudentVo parentSendStudentVo,HttpServletRequest request){
        //校验uid->id的对应
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        if(!parentService.checkIdAndUId(parentSendStudentVo.getpNeedId(),uId)){
            return ResponseVo.error("无法操作他人的需求");
        }
        ResponseVo res = parentService.sendNeedToResume(parentSendStudentVo);
        return res;
    }

    /**
     * 删除需求
     * @param parentSendStudentVo
     * @param request
     * @return
     */
    @PostMapping("/delete/send/parent/need")
    public ResponseVo deleteSendParentNeed(@Valid @RequestBody ParentSendStudentVo parentSendStudentVo,HttpServletRequest request){
        //校验uid->id的对应
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
//        if(!parentService.checkIdAndUId(parentSendStudentVo.getpNeedId(),uId)){
//            return ResponseVo.error("无法操作他人的需求");
//        }
        ResponseVo res = parentService.deleteSendNeedToResume(parentSendStudentVo);
        return res;
    }

    /**
     * 查看需求接收到的简历
     * @param id
     * @return
     */
    @GetMapping("/get/parent/receive")
    public ResponseVo getParentReceive(Integer id,HttpServletRequest request){
        //校验uid->id的对应
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        if(!parentService.checkIdAndUId(id,uId)){
            return ResponseVo.error("无法操作他人的需求");
        }

        ResponseVo res = parentService.getParentReceive(id);
        return res;
    }

    /**
     * 查看需求发送到的简历
     * @param id
     * @return
     */
    @GetMapping("/get/parent/sent")
    public ResponseVo getParentSent(Integer id,HttpServletRequest request){
        //校验uid->id的对应
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        if(!parentService.checkIdAndUId(id,uId)){
            return ResponseVo.error("无法操作他人的需求");
        }
        ResponseVo res = parentService.getParentSent(id);
        return res;
    }

    /**
     * 获取家长需求的总数
     * @return
     */
    @GetMapping("/get/parent/need/sum")
    public ResponseVo getParentNeedSum(){
        ResponseVo res = parentService.getParentNeedSum();
        return res;
    }


}
