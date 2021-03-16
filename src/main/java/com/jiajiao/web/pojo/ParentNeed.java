package com.jiajiao.web.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ParentNeed {
    private Integer id;

    private Integer uId;

    private String name;

    private String wechat;

    private Integer studentClass;

    private String gradeCondiction;

    private String characterCondiction;

    private Float duration;

    private Integer times;

    private Float salary;

    private String expectGoal;

    private String location;

    private Float arriveHours;

    private Integer education;

    private String teachReq;

    private String learnCondiction;

    private String expectFeedback;

    private String teachBySelf;

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

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Integer getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(Integer studentClass) {
        this.studentClass = studentClass;
    }

    public String getGradeCondiction() {
        return gradeCondiction;
    }

    public void setGradeCondiction(String gradeCondiction) {
        this.gradeCondiction = gradeCondiction;
    }

    public String getCharacterCondiction() {
        return characterCondiction;
    }

    public void setCharacterCondiction(String characterCondiction) {
        this.characterCondiction = characterCondiction;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getExpectGoal() {
        return expectGoal;
    }

    public void setExpectGoal(String expectGoal) {
        this.expectGoal = expectGoal;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getArriveHours() {
        return arriveHours;
    }

    public void setArriveHours(Float arriveHours) {
        this.arriveHours = arriveHours;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getTeachReq() {
        return teachReq;
    }

    public void setTeachReq(String teachReq) {
        this.teachReq = teachReq;
    }

    public String getLearnCondiction() {
        return learnCondiction;
    }

    public void setLearnCondiction(String learnCondiction) {
        this.learnCondiction = learnCondiction;
    }

    public String getExpectFeedback() {
        return expectFeedback;
    }

    public void setExpectFeedback(String expectFeedback) {
        this.expectFeedback = expectFeedback;
    }

    public String getTeachBySelf() {
        return teachBySelf;
    }

    public void setTeachBySelf(String teachBySelf) {
        this.teachBySelf = teachBySelf;
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

    @Override
    public String toString() {
        return "ParentNeed{" +
                "id=" + id +
                ", uId=" + uId +
                ", name='" + name + '\'' +
                ", wechat='" + wechat + '\'' +
                ", studentClass=" + studentClass +
                ", gradeCondiction='" + gradeCondiction + '\'' +
                ", characterCondiction='" + characterCondiction + '\'' +
                ", duration=" + duration +
                ", times=" + times +
                ", salary=" + salary +
                ", expectGoal='" + expectGoal + '\'' +
                ", location='" + location + '\'' +
                ", arriveHours=" + arriveHours +
                ", education=" + education +
                ", teachReq='" + teachReq + '\'' +
                ", learnCondiction='" + learnCondiction + '\'' +
                ", expectFeedback='" + expectFeedback + '\'' +
                ", teachBySelf='" + teachBySelf + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}