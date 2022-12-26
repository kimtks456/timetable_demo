package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private final DemoInjection demoInjection;
    private final Timetable timeTable = new TimetableB();

    public DemoService(DemoInjection demoInjection) {
        this.demoInjection = demoInjection;
    }

    public String test() {
        return demoInjection.test();
    }

    public String timetable() {
        return timeTable.get();
    }
}

