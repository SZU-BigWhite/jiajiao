package com.jiajiao.web.form;

import org.springframework.beans.factory.annotation.Value;

public class GetParentNeedOrderForm {
    /**
     * 0 - 不做排序
     * 1 - 升序
     * 2 - 降序
     */
    private Integer duration=0;
    private Integer times=0;
    private Integer salary=0;
    private Integer arriveHours=0;

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
