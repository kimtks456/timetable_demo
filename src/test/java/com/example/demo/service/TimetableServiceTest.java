package com.example.demo.service;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TimetableServiceTest {

    TimetableService timetableService = new TimetableService();

    @Test
    void validateDay() {
        String day = "san";
        System.out.println(timetableService.validateDay(day));

        assertThat(timetableService.validateDay(day)).isEqualTo(false);
    }

    @Test
    void validateTime() {
    }

    @Test
    void validateTimeInRange() {
    }
}