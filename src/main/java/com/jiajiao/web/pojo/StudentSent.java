package com.jiajiao.web.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StudentSent {
    private Integer id;

    private Integer sResumeId;

    private Integer pNeedId;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getsResumeId() {
        return sResumeId;
    }

    public void setsResumeId(Integer sResumeId) {
        this.sResumeId = sResumeId;
    }

    public Integer getpNeedId() {
        return pNeedId;
    }

    public void setpNeedId(Integer pNeedId) {
        this.pNeedId = pNeedId;
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
}