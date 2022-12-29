package com.example.demo.repository;

import java.util.List;

public interface NewBuildingTimetableRepository {

    NewBuildingTimetable save(NewBuildingTimetable newBuildingTimetable);
    List<NewBuildingTimetable> findAll();

    List<NewBuildingTimetable> findByDay(String monday);

    List<NewBuildingTimetable> findById(long id);

    int delete(long id);
}
