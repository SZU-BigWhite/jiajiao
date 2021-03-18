package com.jiajiao.web.vo;

import com.jiajiao.web.pojo.VolunteerCollection;

public class VolunteerCollectionVo {
    VolunteerCollection volunteerCollection;
    Integer count;

    public VolunteerCollection getVolunteerCollection() {
        return volunteerCollection;
    }

    public void setVolunteerCollection(VolunteerCollection volunteerCollection) {
        this.volunteerCollection = volunteerCollection;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
