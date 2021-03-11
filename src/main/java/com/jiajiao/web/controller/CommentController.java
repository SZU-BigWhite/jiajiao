package com.jiajiao.web.controller;

import com.jiajiao.web.form.CommentForm;
import com.jiajiao.web.service.Impl.CommentServiceImpl;
import com.jiajiao.web.service.Impl.MessagesServiceImpl;
import com.jiajiao.web.utils.CookieUtils;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentController {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    MessagesServiceImpl messagesService;
    @Autowired
    CommentServiceImpl commentService;

    /**
     * 添加评论
     * @param request
     * @param commentForm
     * @return
     */
    @PostMapping("/add/comment")
    public ResponseVo addComment(HttpServletRequest request, @RequestBody CommentForm commentForm){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        return commentService.addComment(commentForm,uId);
    }

    /**
     * 删除评论
     * @param request
     * @param id
     * @return
     */
    @DeleteMapping("/delete/comment")
    public ResponseVo addComment(HttpServletRequest request, Integer id){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        return commentService.deleteComment(id,uId);
    }

    /**
     * 获取简历对应的评论
     * @param outId
     * @return
     */
    @GetMapping("/get/comments")
    public ResponseVo getComments(Integer outId){
        return commentService.getCommentByOutId(outId);
    }
}
