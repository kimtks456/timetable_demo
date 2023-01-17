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
        return em.createQuery("select n from OldBuildingTimetable n where n.day = :day", OldBuildingTimetable.class)
                .setParameter("day", day)
                .getResultList();
    }



    public List<OldBuildingTimetable> findById(long id) {
        return em.createQuery("select n from OldBuildingTimetable n where n.id = :id", OldBuildingTimetable.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<OldBuildingTimetable> update(long id, OldBuildingTimetable req) {
        return em.createQuery(
                        "UPDATE OldBuildingTimetable o SET o.day = :day, o.start = :start, o.end = :end WHERE o.id = :id",
                        OldBuildingTimetable.class) // 두번째 parameter를 지워야함 !!
                .setParameter("id", id)
                .setParameter("start", req.getStart())
                .setParameter("end", req.getEnd())
                .getResultList(); // executeUpdate();를 call 해야하는듯함.
    }

    public int delete(long id) {
        return em.createQuery("delete from OldBuildingTimetable o where o.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
