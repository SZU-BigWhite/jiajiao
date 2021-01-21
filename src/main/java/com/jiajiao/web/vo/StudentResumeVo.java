package com.jiajiao.web.vo;

import com.jiajiao.web.pojo.StudentAcademy;
import com.jiajiao.web.pojo.StudentResume;
import com.jiajiao.web.pojo.Subject;
import com.jiajiao.web.pojo.Time;

import java.util.List;

public class StudentResumeVo {
    StudentResume studentResume;

    List<Subject> subjectList;

    List<Time> timeList;

    public StudentResume getStudentResume() {
        return studentResume;
    }

    public void setStudentResume(StudentResume studentResume) {
        this.studentResume = studentResume;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<Time> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Time> timeList) {
        this.timeList = timeList;
    }

    @Override
    public String toString() {
        return "StudentResumeVo{" +
                "studentResume=" + studentResume +
                ", subjectList=" + subjectList +
                ", timeList=" + timeList +
                '}';
    }
}
