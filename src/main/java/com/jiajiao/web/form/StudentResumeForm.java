package com.jiajiao.web.form;

import com.jiajiao.web.pojo.StudentResume;

import java.util.List;

public class StudentResumeForm {
    StudentResume studentResume;

    List<String> tags;      //性格

    List<String> hobby;

    List<String> subjectList;

    List<Integer> timeList;

    public StudentResume getStudentResume() {
        return studentResume;
    }

    public void setStudentResume(StudentResume studentResume) {
        this.studentResume = studentResume;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public List<String> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<String> subjectList) {
        this.subjectList = subjectList;
    }

    public List<Integer> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Integer> timeList) {
        this.timeList = timeList;
    }
}
