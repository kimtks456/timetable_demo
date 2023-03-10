package com.example.demo.service;

import com.example.demo.repository.NewBuildingTimetable;
import com.example.demo.repository.OldBuildingTimetable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimetableService {

    private static final List<String> days = Stream.of(
            "mon", "tue", "wed", "thu", "fri", "sat", "sun").toList();

    public boolean validateDay(String day) {
        for (String item : days) {
            if (item.equals(day)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateTime(Integer start, Integer end) {
        return (start <= end) && validateTimeInRange(start) && validateTimeInRange(end);
    }

    public boolean validateTimeInRange(Integer time) {
        return (time >= 12) && (time <= 24);
    }

    public boolean isDuplicateNew(NewBuildingTimetable target, List<NewBuildingTimetable> timetable) {
        if (timetable.isEmpty()) {
            return false;
        }
        Integer start = target.getStart();
        Integer end = target.getEnd();
        for (NewBuildingTimetable item : timetable) {
            if (start >= item.getStart() && start < item.getEnd()) {
                return true;
            }
            if (end > item.getStart() && end <= item.getEnd()) {
                return true;
            }
        }
        return false;
    }

    public boolean isDuplicateOld(OldBuildingTimetable target, List<OldBuildingTimetable> timetable) {
        if (timetable.isEmpty()) {
            return false;
        }
        Integer start = target.getStart();
        Integer end = target.getEnd();
        for (OldBuildingTimetable item : timetable) {
            if (start >= item.getStart() && start < item.getEnd()) {
                return true;
            }
            if (end > item.getStart() && end <= item.getEnd()) {
                return true;
            }
        }
        return false;
    }

    public boolean isDuplicateOldUpdate(OldBuildingTimetable target, List<OldBuildingTimetable> timetable) {
//        if (target.size() == 0) {
//            System.out.println("ID zero\n");
//            return true; // requested id doesn't exist in db
//        }

        if (timetable.isEmpty()) {
            return false;
        }

        Integer start = target.getStart();
        Integer end = target.getEnd();

        log.info("REQUIRED : " + target.getDay()+target.getStart()+target.getEnd()+"\n");

        for (OldBuildingTimetable item : timetable) {
            log.info("This item : "+item.getDay()+item.getStart()+item.getEnd()+"\n");

            if (target.getId() == item.getId()) {
                continue;
            }
            if (start >= item.getStart() && start < item.getEnd()) {
                log.info("start invalid\n");
                return true;
            }
            if (end > item.getStart() && end <= item.getEnd()) {
                log.info("end invalid\n");
                return true;
            }
        }
        return false;
    }

    public boolean isDuplicateNewUpdate(NewBuildingTimetable target, List<NewBuildingTimetable> timetable) {
        if (timetable.isEmpty()) {
            return false;
        }

        Integer start = target.getStart();
        Integer end = target.getEnd();

        log.info("REQUIRED : " + target.getDay()+target.getStart()+target.getEnd()+"\n");

        for (NewBuildingTimetable item : timetable) {
            log.info("This item : "+item.getDay()+item.getStart()+item.getEnd()+"\n");

            if (Objects.equals(target.getId(), item.getId())) {
                continue;
            }
            if (start >= item.getStart() && start < item.getEnd()) {
                log.info("start invalid\n");
                return true;
            }
            if (end > item.getStart() && end <= item.getEnd()) {
                log.info("end invalid\n");
                return true;
            }
        }
        return false;
    }
}
