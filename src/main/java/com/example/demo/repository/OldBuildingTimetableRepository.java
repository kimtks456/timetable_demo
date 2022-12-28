package com.example.demo.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface OldBuildingTimetableRepository extends CrudRepository<OldBuildingTimetable, Integer> {

//    private final EntityManager em;
//
//    public List<List<Integer>> findByDay(String day) {
//        List<List<Integer>> timeslot = new ArrayList<List<Integer>>();
//
//        return timeslot;
//    }

}
