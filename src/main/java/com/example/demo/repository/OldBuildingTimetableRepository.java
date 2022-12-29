package com.example.demo.repository;

import java.util.List;

public interface OldBuildingTimetableRepository {

    OldBuildingTimetable save(OldBuildingTimetable oldBuildingTimetable);
    List<OldBuildingTimetable> findAll();

    List<OldBuildingTimetable> findByDay(String monday);

    List<OldBuildingTimetable> findById(long id);

    List<OldBuildingTimetable> update(long id, OldBuildingTimetable reqTime);
}
