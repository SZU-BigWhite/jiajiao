package com.jiajiao.web.controller;

import com.jiajiao.web.service.Impl.MessagesServiceImpl;
import com.jiajiao.web.utils.CookieUtils;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MessagesController {

    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    MessagesServiceImpl messagesService;

    /**
     * 用户获取消息
     * @param request
     * @return
     */
    @GetMapping("/get/user/messages")
    public ResponseVo getUserMessages(HttpServletRequest request){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        ResponseVo res = messagesService.getMessagesByUId(uId);
        return res;
    }

    /**
     * 删除消息
     * @param request
     * @param id
     * @return
     */
    @DeleteMapping("/delete/user/message")
    public ResponseVo deleteUserMessage(HttpServletRequest request,Integer id){
        int uId = CookieUtils.getUIdFromRedis(request,redisTemplate);
        if(!messagesService.checkIdAndUId(id,uId)){
            return ResponseVo.error("无法操作他人的消息");
        }
        ResponseVo res = messagesService.deleteMessageById(id);
        return res;
    }

    @GetMapping("/set/message/to/read")
    public ResponseVo setMessageToRead(HttpServletRequest request,Integer id){
        int uId = CookieUtils.getUIdFromRedis(request,redisTemplate);
        if(!messagesService.checkIdAndUId(id,uId)){
            return ResponseVo.error("无法操作他人的消息");
        }
        ResponseVo res = messagesService.setMessageToRead(id);
        return res;
    }
}
