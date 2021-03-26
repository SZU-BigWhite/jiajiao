package com.jiajiao.web.vo;

public class UserSumVo {
    Integer needSum;
    Integer resumeSum;
    Integer helpSum;
    Integer volunteerSum;

    public Integer getNeedSum() {
        return needSum;
    }

    public void setNeedSum(Integer needSum) {
        this.needSum = needSum;
    }

    public Integer getResumeSum() {
        return resumeSum;
    }

    public void setResumeSum(Integer resumeSum) {
        this.resumeSum = resumeSum;
    }

    public Integer getHelpSum() {
        return helpSum;
    }

    public void setHelpSum(Integer helpSum) {
        this.helpSum = helpSum;
    }

    public Integer getVolunteerSum() {
        return volunteerSum;
    }

    public void setVolunteerSum(Integer volunteerSum) {
        this.volunteerSum = volunteerSum;
    }

    @Override
    public String toString() {
        return "UserSumVo{" +
                "needSum=" + needSum +
                ", resumeSum=" + resumeSum +
                ", helpSum=" + helpSum +
                ", volunteerSum=" + volunteerSum +
                '}';
    }
}
