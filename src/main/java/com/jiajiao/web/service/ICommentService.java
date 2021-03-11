package com.jiajiao.web.service;

import com.jiajiao.web.form.CommentForm;
import com.jiajiao.web.vo.ResponseVo;

public interface ICommentService {
    ResponseVo addComment(CommentForm commentForm, Integer uId);
    ResponseVo deleteComment(Integer id, Integer uId);
    ResponseVo getCommentByOutId(Integer outId);
}
