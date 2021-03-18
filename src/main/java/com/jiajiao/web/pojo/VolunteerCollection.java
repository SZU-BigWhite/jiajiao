package com.jiajiao.web.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class VolunteerCollection {
    private Integer id;

    private Integer uId;

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startTime;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    private String content;

    private String wechet;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updateTime;

    private Integer status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWechet() {
        return wechet;
    }

    public void setWechet(String wechet) {
        this.wechet = wechet;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}