package com.jiajiao.web.service.Impl;

import com.jiajiao.web.dao.CommentMapper;
import com.jiajiao.web.enums.UserReqConst;
import com.jiajiao.web.form.CommentForm;
import com.jiajiao.web.pojo.Comment;
import com.jiajiao.web.service.ICommentService;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    CommentMapper commentMapper;
    @Override
    public ResponseVo addComment(CommentForm commentForm, Integer uId) {
        Comment comment = commentForm.getComment();
        comment.setuId(uId);
        commentMapper.insertSelective(comment);
        return ResponseVo.success("评论成功");
    }

    @Override
    public ResponseVo deleteComment(Integer id, Integer uId) {
        Comment comment = commentMapper.selectByPrimaryKey(id);
        if(comment==null||!comment.getuId().equals(uId)){
            return ResponseVo.error("无法操作他人的评论");
        }
        commentMapper.deleteByPrimaryKey(id);
        return ResponseVo.success("评论删除成功");
    }

    @Override
    public ResponseVo getCommentByOutId(Integer outId) {
        List<Comment> comments = commentMapper.selectByOutId(outId);
        return ResponseVo.success("评论查询成功",comments);
    }
}
