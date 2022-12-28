package com.example.demo.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NewBuildingTimetable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String day;
    private Integer start;
    private Integer end;
    private String user;

    public Integer getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public String getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
