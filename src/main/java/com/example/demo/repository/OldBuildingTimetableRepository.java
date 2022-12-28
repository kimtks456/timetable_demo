package com.example.demo.repository;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import org.springframework.data.repository.CrudRepository;

public interface OldBuildingTimetableRepository extends CrudRepository<OldBuildingTimetable, Integer> {

}
