package com.jiajiao.web.form;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

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
    private Integer salary=0;
    private Integer updateTime=0;

    /**
     * 筛选
     * @return
     */
    private Integer times=0;
    private Integer ableClass=0;
    private List<String> freeTimeString;
    private List<String> name;
    private List<Integer> freeTime;

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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getAbleClass() {
        return ableClass;
    }

    public void setAbleClass(Integer ableClass) {
        this.ableClass = ableClass;
    }

    public List<String> getFreeTimeString() {
        return freeTimeString;
    }

    public void setFreeTimeString(List<String> freeTimeString) {
        this.freeTimeString = freeTimeString;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<Integer> getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(List<Integer> freeTime) {
        this.freeTime = freeTime;
    }

    @Override
    public String toString() {
        return "GetParentNeedOrderForm{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", salary=" + salary +
                ", updateTime=" + updateTime +
                ", times=" + times +
                ", ableClass=" + ableClass +
                ", freeTimeString=" + freeTimeString +
                ", name=" + name +
                ", freeTime=" + freeTime +
                '}';
    }
}
