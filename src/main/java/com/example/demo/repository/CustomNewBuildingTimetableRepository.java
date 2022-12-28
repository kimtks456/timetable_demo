package com.example.demo.repository;

import jakarta.persistence.EntityManager;
import java.util.List;

public class CustomNewBuildingTimetableRepository implements NewBuildingTimetableRepository {

    private final EntityManager em;

    public CustomNewBuildingTimetableRepository(EntityManager em) {
        this.em = em;
    }


    public NewBuildingTimetable save(NewBuildingTimetable newBuildingTimetable) {
        em.persist(newBuildingTimetable);
        return null;
    }

    public List<NewBuildingTimetable> findAll() {
        return em.createQuery("select n from NewBuildingTimetable n", NewBuildingTimetable.class)
                .getResultList();
    }

    public List<NewBuildingTimetable> findByDay(String day) {
        List<NewBuildingTimetable> result = em.createQuery("select n from NewBuildingTimetable n where n.day = :day", NewBuildingTimetable.class)
                .setParameter("day", day)
                .getResultList();
        return result;
    }
}
