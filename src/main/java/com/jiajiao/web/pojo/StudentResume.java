package com.jiajiao.web.pojo;

import java.util.Date;

public class StudentResume {
    private Integer id;

    private Integer uId;

    private String name;

    private Integer sex;

    private String wechat;

    private String academyId;

    private Float salary;

    private Integer ableClass;

    private String characterCondiction;

    private Float grade;

    private String teachStress;

    private String teachGoal;

    private String feedback;

    private String university;

    private Integer education;

    private String hobby;

    private String learnMethods;

    private String showSelf;

    private String motto;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    public StudentResume(){}

    public StudentResume(String name, Integer sex, String wechat, String academyId, Float salary, Integer ableClass, String characterCondiction, Float grade, String teachStress, String teachGoal, String feedback, String university, Integer education, String hobby, String learnMethods, String showSelf, String motto) {
        this.name = name;
        this.sex = sex;
        this.wechat = wechat;
        this.academyId = academyId;
        this.salary = salary;
        this.ableClass = ableClass;
        this.characterCondiction = characterCondiction;
        this.grade = grade;
        this.teachStress = teachStress;
        this.teachGoal = teachGoal;
        this.feedback = feedback;
        this.university = university;
        this.education = education;
        this.hobby = hobby;
        this.learnMethods = learnMethods;
        this.showSelf = showSelf;
        this.motto = motto;
    }

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

    public String getAcademyId() {
        return academyId;
    }

    public void setAcademyId(String academyId) {
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

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
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

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getLearnMethods() {
        return learnMethods;
    }

    public void setLearnMethods(String learnMethods) {
        this.learnMethods = learnMethods;
    }

    public String getShowSelf() {
        return showSelf;
    }

    public void setShowSelf(String showSelf) {
        this.showSelf = showSelf;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
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