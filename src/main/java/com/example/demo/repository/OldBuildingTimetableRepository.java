package com.example.demo.repository;

import java.util.List;

public interface OldBuildingTimetableRepository {

    OldBuildingTimetable save(OldBuildingTimetable oldBuildingTimetable);
    List<OldBuildingTimetable> findAll();

    List<OldBuildingTimetable> findByDay(String monday);
}
