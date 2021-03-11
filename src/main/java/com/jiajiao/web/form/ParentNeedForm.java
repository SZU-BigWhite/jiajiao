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

    List<String> tags;      //性格

    public String getStudentClassString() {
        return studentClassString;
    }

    public void setStudentClassString(String studentClassString) {
        this.studentClassString = studentClassString;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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
}
