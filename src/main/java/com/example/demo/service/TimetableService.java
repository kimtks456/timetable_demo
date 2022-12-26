package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimetableService {

    private static List<String> days = Stream.of(
            "mon", "tue", "wed", "thu", "fri", "sat", "sun")
            .collect(Collectors.toList());

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
}
