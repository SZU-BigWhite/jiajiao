package com.jiajiao.web.form;

import com.jiajiao.web.pojo.ParentNeed;
import com.jiajiao.web.pojo.Subject;
import com.jiajiao.web.pojo.Time;

import java.util.List;

public class ParentNeedForm {

    ParentNeed parentNeed;

    List<String> subjectList;

    List<Integer> timeList;

    List<String> tags;      //性格

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

    public List<Integer> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Integer> timeList) {
        this.timeList = timeList;
    }
}
