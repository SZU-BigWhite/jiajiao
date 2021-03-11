package com.jiajiao.web.pojo;

import java.util.Date;

public class StudentHelp {
    private Integer id;

    private Integer uId;

    private String ademecy;

    private String subject;

    private String condiction;

    private String helpReq;

    private Float salary;

    private Date createTime;

    private Date updateTim;

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

    public String getAdemecy() {
        return ademecy;
    }

    public void setAdemecy(String ademecy) {
        this.ademecy = ademecy;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCondiction() {
        return condiction;
    }

    public void setCondiction(String condiction) {
        this.condiction = condiction;
    }

    public String getHelpReq() {
        return helpReq;
    }

    public void setHelpReq(String helpReq) {
        this.helpReq = helpReq;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTim() {
        return updateTim;
    }

    public void setUpdateTim(Date updateTim) {
        this.updateTim = updateTim;
    }

    @Override
    public String toString() {
        return "StudentHelp{" +
                "id=" + id +
                ", uId=" + uId +
                ", ademecy='" + ademecy + '\'' +
                ", subject='" + subject + '\'' +
                ", condiction='" + condiction + '\'' +
                ", helpReq='" + helpReq + '\'' +
                ", salary=" + salary +
                ", createTime=" + createTime +
                ", updateTim=" + updateTim +
                '}';
    }
}