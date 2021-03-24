package com.jiajiao.web.form;

import com.jiajiao.web.pojo.ParentNeed;
import com.jiajiao.web.pojo.Subject;
import com.jiajiao.web.pojo.Time;

import java.util.List;

public class ParentNeedForm {

    ParentNeed parentNeed;

    String studentClassString;

    List<String> subjectList;

    List<String> timeList;


    public String getStudentClassString() {
        return studentClassString;
    }

    public void setStudentClassString(String studentClassString) {
        this.studentClassString = studentClassString;
    }


    public ParentNeed getParentNeed() {
        return parentNeed;
    }

    public void setParentNeed(ParentNeed parentNeed) {
        this.parentNeed = parentNeed;
    }

    public List<String> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<String> subjectList) {
        this.subjectList = subjectList;
    }

    public List<String> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<String> timeList) {
        this.timeList = timeList;
    }

    @Override
    public String toString() {
        return "ParentNeedForm{" +
                "parentNeed=" + parentNeed +
                ", studentClassString='" + studentClassString + '\'' +
                ", subjectList=" + subjectList +
                ", timeList=" + timeList +
                '}';
    }
}
