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

    private Integer timeIsChange;

    private Float duration;

    private Integer times;

    private Float salary;

    private String marjor;

    private String characterReq;

    private String chattingReq;

    private String expectGoal;

    private Integer addedBonus;

    private String location;

    private Float arriveHours;

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

    public Integer getTimeIsChange() {
        return timeIsChange;
    }

    public void setTimeIsChange(Integer timeIsChange) {
        this.timeIsChange = timeIsChange;
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

    public String getMarjor() {
        return marjor;
    }

    public void setMarjor(String marjor) {
        this.marjor = marjor;
    }

    public String getCharacterReq() {
        return characterReq;
    }

    public void setCharacterReq(String characterReq) {
        this.characterReq = characterReq;
    }

    public String getChattingReq() {
        return chattingReq;
    }

    public void setChattingReq(String chattingReq) {
        this.chattingReq = chattingReq;
    }

    public String getExpectGoal() {
        return expectGoal;
    }

    public void setExpectGoal(String expectGoal) {
        this.expectGoal = expectGoal;
    }

    public Integer getAddedBonus() {
        return addedBonus;
    }

    public void setAddedBonus(Integer addedBonus) {
        this.addedBonus = addedBonus;
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
}