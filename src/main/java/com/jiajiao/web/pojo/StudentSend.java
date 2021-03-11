package com.jiajiao.web.pojo;

import java.util.Date;

public class StudentSend {
    private Integer id;

    private Integer uId;

    private Integer helpId;

    private String condiction;

    private String helpMethod;

    private String wechat;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getHelpId() {
        return helpId;
    }

    public void setHelpId(Integer helpId) {
        this.helpId = helpId;
    }

    public String getCondiction() {
        return condiction;
    }

    public void setCondiction(String condiction) {
        this.condiction = condiction;
    }

    public String getHelpMethod() {
        return helpMethod;
    }

    public void setHelpMethod(String helpMethod) {
        this.helpMethod = helpMethod;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}