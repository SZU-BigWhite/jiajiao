package com.jiajiao.web.form;

import java.util.List;

public class GetStudentResumeOrderForm {
    /**
     * 默认 第 1 页
     * 默认 一页 10 行
     */
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    /**
     * 0 - 不做排序
     * 1 - 升序
     * 2 - 降序 --时间降序则最新
     */
    private Integer salary = 0;
    private Integer sex = 0;
    private Integer updateTime = 0;

    /**
     * 筛选
     *
     * @return
     */
    private Integer ableClass;
    private String academyId;
    private List<String> freeTimeString;
    private List<String> name;
    private List<Integer> freeTime;


    public List<String> getFreeTimeString() {
        return freeTimeString;
    }

    public void setFreeTimeString(List<String> freeTimeString) {
        this.freeTimeString = freeTimeString;
    }

    public List<Integer> getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(List<Integer> freeTime) {
        this.freeTime = freeTime;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }


    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAbleClass() {
        return ableClass;
    }

    public void setAbleClass(Integer ableClass) {
        this.ableClass = ableClass;
    }

    public String getAcademyId() {
        return academyId;
    }

    public void setAcademyId(String academyId) {
        this.academyId = academyId;
    }

    @Override
    public String toString() {
        return "GetStudentResumeOrderForm{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", salary=" + salary +
                ", sex=" + sex +
                ", updateTime=" + updateTime +
                ", ableClass=" + ableClass +
                ", academyId='" + academyId + '\'' +
                ", freeTimeString=" + freeTimeString +
                ", name=" + name +
                ", freeTime=" + freeTime +
                '}';
    }
}
