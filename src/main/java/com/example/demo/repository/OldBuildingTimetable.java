package com.example.demo.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class OldBuildingTimetable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String day;
    private Integer start;
    private Integer end;
    private String user;
}
