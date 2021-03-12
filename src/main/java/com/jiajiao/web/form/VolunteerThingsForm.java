package com.jiajiao.web.form;

import com.jiajiao.web.pojo.VolunteerThings;

import java.util.List;

public class VolunteerThingsForm {
    VolunteerThings volunteerThings;

    List<String> books;

    public VolunteerThings getVolunteerThings() {
        return volunteerThings;
    }

    public void setVolunteerThings(VolunteerThings volunteerThings) {
        this.volunteerThings = volunteerThings;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }
}
