package com.jiajiao.web.vo;

import com.jiajiao.web.pojo.ParentNeed;
import com.jiajiao.web.pojo.Subject;
import com.jiajiao.web.pojo.Time;

import java.util.List;

public class ParentNeedVo {
    ParentNeed parentNeed;

    private List<Subject> subjectList;

    private List<Time> timeList;

    Integer receiveCounts;

    public Integer getReceiveCounts() {
        return receiveCounts;
    }

    public void setReceiveCounts(Integer receiveCounts) {
        this.receiveCounts = receiveCounts;
    }

    public ParentNeed getParentNeed() {
        return parentNeed;
    }

    public void setParentNeed(ParentNeed parentNeed) {
        this.parentNeed = parentNeed;
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
}
