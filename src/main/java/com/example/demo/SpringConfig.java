package com.example.demo;

import com.example.demo.repository.CustomNewBuildingTimetableRepository;
import com.example.demo.repository.CustomOldBuildingTimetableRepository;
import com.example.demo.repository.NewBuildingTimetableRepository;
import com.example.demo.repository.OldBuildingTimetableRepository;
import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public OldBuildingTimetableRepository oldBuildingTimetableRepository() {
        return new CustomOldBuildingTimetableRepository(em);
    }
}
