package com.jiajiao.web.form;

import com.jiajiao.web.pojo.Comment;

public class CommentForm {
    Comment comment;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentForm{" +
                "comment=" + comment +
                '}';
    }
}
