package com.jiajiao.web.form;

import org.springframework.beans.factory.annotation.Value;

public class GetParentNeedOrderForm {
    /**
     * 默认 第 1 页
     * 默认 一页 10 行
     */
    private Integer pageNum=1;
    private Integer pageSize=10;
    /**
     * 0 - 不做排序
     * 1 - 升序
     * 2 - 降序 --时间降序则最新
     */
    private Integer duration=0;
    private Integer times=0;
    private Integer salary=0;
    private Integer arriveHours=0;
    private Integer updateTime=0;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getArriveHours() {
        return arriveHours;
    }

    public void setArriveHours(Integer arriveHours) {
        this.arriveHours = arriveHours;
    }

    @Override
    public String toString() {
        return "GetParentNeedOrderVo{" +
                "duration=" + duration +
                ", times=" + times +
                ", salary=" + salary +
                ", arriveHours=" + arriveHours +
                '}';
    }
}
