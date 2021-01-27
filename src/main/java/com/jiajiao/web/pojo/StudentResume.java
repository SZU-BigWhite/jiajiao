package com.jiajiao.web.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StudentResume {
    private Integer id;

    private Integer uId;

    private String name;

    private Integer sex;

    private String wechat;

    private Integer academyId;

    private Float salary;

    private Integer ableClass;

    private String characterCondiction;

    private String grade;

    private String prepareStress;

    private String teachStress;

    private String teachGoal;

    private String feedback;

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Integer getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Integer academyId) {
        this.academyId = academyId;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Integer getAbleClass() {
        return ableClass;
    }

    public void setAbleClass(Integer ableClass) {
        this.ableClass = ableClass;
    }

    public String getCharacterCondiction() {
        return characterCondiction;
    }

    public void setCharacterCondiction(String characterCondiction) {
        this.characterCondiction = characterCondiction;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPrepareStress() {
        return prepareStress;
    }

    public void setPrepareStress(String prepareStress) {
        this.prepareStress = prepareStress;
    }

    public String getTeachStress() {
        return teachStress;
    }

    public void setTeachStress(String teachStress) {
        this.teachStress = teachStress;
    }

    public String getTeachGoal() {
        return teachGoal;
    }

    public void setTeachGoal(String teachGoal) {
        this.teachGoal = teachGoal;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
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