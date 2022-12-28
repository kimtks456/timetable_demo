package com.example.demo;

import com.example.demo.repository.CustomNewBuildingTimetableRepository;
import com.example.demo.repository.NewBuildingTimetableRepository;
import com.example.demo.service.Timetable;
import com.example.demo.service.TimetableA;
import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public NewBuildingTimetableRepository newBuildingTimetableRepository() {
        return new CustomNewBuildingTimetableRepository(em);
    }

//    @Bean
//    Timetable timeTable() {
//        return new TimetableA();
//    }
}
