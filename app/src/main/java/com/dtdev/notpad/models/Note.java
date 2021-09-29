package com.dtdev.notpad.models;

public class Note {
    Integer id ;
    String title;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTime_note() {
        return time_note;
    }

    public String getDate_note() {
        return date_note;
    }

    String description;
    String time_note;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime_note(String time_note) {
        this.time_note = time_note;
    }

    public void setDate_note(String date_note) {
        this.date_note = date_note;
    }

    String date_note;




}
