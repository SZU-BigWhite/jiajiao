package com.jiajiao.web.pojo;

public class Time {
    private Integer id;

    private Integer outId;

    private Integer type;

    private Integer freeTime;

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

    public Integer getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(Integer freeTime) {
        this.freeTime = freeTime;
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", outId=" + outId +
                ", type=" + type +
                ", freeTime=" + freeTime +
                '}';
    }
}