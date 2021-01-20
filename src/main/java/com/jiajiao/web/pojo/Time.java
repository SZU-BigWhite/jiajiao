package com.jiajiao.web.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Time {
    private Integer id;

    private Integer outId;

    private Integer type;

    private Integer weekday;

    @JsonFormat(pattern = "HH:mm")
    private Date beginTime;

    @JsonFormat(pattern = "HH:mm")
    private Date endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}