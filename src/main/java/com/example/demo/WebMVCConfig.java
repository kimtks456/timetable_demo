package com.example.demo;

import com.example.demo.service.TimeTable;
import com.example.demo.service.TimeTableA;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
public class WebMVCConfig {

    @Bean
    TimeTable timeTable() {
        return new TimeTableA();
    }
}
