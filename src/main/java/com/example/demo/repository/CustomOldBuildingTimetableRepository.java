package com.example.demo.repository;

import jakarta.persistence.EntityManager;
import java.util.List;

public class CustomOldBuildingTimetableRepository implements OldBuildingTimetableRepository {

    private final EntityManager em;

    public CustomOldBuildingTimetableRepository(EntityManager em) {
        this.em = em;
    }


    public OldBuildingTimetable save(OldBuildingTimetable oldBuildingTimetable) {
        em.persist(oldBuildingTimetable);
        return null;
    }

    public List<OldBuildingTimetable> findAll() {
        return em.createQuery("select n from OldBuildingTimetable n", OldBuildingTimetable.class)
                .getResultList();
    }

    public List<OldBuildingTimetable> findByDay(String day) {
        List<OldBuildingTimetable> result = em.createQuery("select n from OldBuildingTimetable n where n.day = :day", OldBuildingTimetable.class)
                .setParameter("day", day)
                .getResultList();
        return result;
    }
}
