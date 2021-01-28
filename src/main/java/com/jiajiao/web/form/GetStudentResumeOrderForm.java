package com.jiajiao.web.form;

public class GetStudentResumeOrderForm {
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
    private Integer sex=0;
    private Integer academyId=0;
    private Integer salary=0;
    private Integer ableClass=0;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Integer academyId) {
        this.academyId = academyId;
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

    @Override
    public String toString() {
        return "GetStudentResumeOrderVo{" +
                "sex=" + sex +
                ", academyId=" + academyId +
                ", salary=" + salary +
                ", ableClass=" + ableClass +
                '}';
    }
}
