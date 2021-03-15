package com.jiajiao.web.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class VolunteerThings {
    private Integer id;

    private Integer uId;

    private Integer cId;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date time;

    private String wechet;

    @JsonFormat(pattern="yyyy-MM-dd")
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

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

    @Override
    public String toString() {
        return "VolunteerThings{" +
                "id=" + id +
                ", uId=" + uId +
                ", cId=" + cId +
                ", time=" + time +
                ", wechet='" + wechet + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}