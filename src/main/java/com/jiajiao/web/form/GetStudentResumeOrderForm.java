package com.jiajiao.web.form;

public class GetStudentResumeOrderForm {
    /**
     * 0 - 不做排序
     * 1 - 升序
     * 2 - 降序
     */
    private Integer sex=0;
    private Integer academyId=0;
    private Integer salary=0;
    private Integer ableClass=0;

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
