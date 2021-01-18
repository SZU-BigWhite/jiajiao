package com.learn.web.pojo;

import java.util.Date;

public class Time {
    private Integer id;

    private Integer outId;

    private Byte type;

    private Byte weekday;

    private Date beginTime;

    private Date endTime;

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", outId=" + outId +
                ", type=" + type +
                ", weekday=" + weekday +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }

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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getWeekday() {
        return weekday;
    }

    public void setWeekday(Byte weekday) {
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