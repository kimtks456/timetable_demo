package com.example.demo.repository;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// type : old(구관), new(신관) 이거 기존 있는 것들은 default None으로

@Entity // This tells Hibernate to make a table out of this class
public class TimeTable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id; // id가 왜이리 이상하게 증가하는지
    private String day; // mon, tue, wed, thu, fri, sat, sun
    private String start; // 8.5 도 가능은 하게. 일단은 hour 단위로.
    private String end;
    private String user; //

    public Integer getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
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

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setUser(String user) {
        this.user = user;
    }
}