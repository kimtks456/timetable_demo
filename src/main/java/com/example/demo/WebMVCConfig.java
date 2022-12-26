package com.example.demo;

import com.example.demo.service.Timetable;
import com.example.demo.service.TimetableA;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
public class WebMVCConfig {

    @Bean
    Timetable timeTable() {
        return new TimetableA();
    }
}
