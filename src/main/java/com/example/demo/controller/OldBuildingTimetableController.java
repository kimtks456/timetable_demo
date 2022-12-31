package com.example.demo.controller;

import com.example.demo.repository.OldBuildingTimetable;
import com.example.demo.repository.OldBuildingTimetableRepository;
import com.example.demo.repository.TimetableVO;
import com.example.demo.service.TimetableService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Transactional
@RequestMapping(path = "/old", produces = "application/json;charset=UTF-8")
public class OldBuildingTimetableController {

    @Autowired
    private OldBuildingTimetableRepository oldBuildingTimetableRepository;

    @GetMapping
    public @ResponseBody Iterable<OldBuildingTimetable> get() {
        // This returns a JSON or XML with the users
        return oldBuildingTimetableRepository.findAll();
    }

    @PostMapping
    public @ResponseBody String post(@RequestBody TimetableVO reqTime) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        TimetableService timetableService = new TimetableService();

        OldBuildingTimetable n = new OldBuildingTimetable();
        n.setDay(reqTime.getDay());
        n.setStart(reqTime.getStart());
        n.setEnd(reqTime.getEnd());
        n.setUser(reqTime.getUser());

        if (
                !timetableService.validateDay(reqTime.getDay())
                || !timetableService.validateTime(reqTime.getStart(), reqTime.getEnd())
                || timetableService.isDuplicateOld(n, oldBuildingTimetableRepository.findByDay(reqTime.getDay()))
            ) {
            Throwable ex = new Throwable();
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Invalid day, time", ex);
        }

        oldBuildingTimetableRepository.save(n);
        return "Saved";
    }

    @PatchMapping("/{id}")
    public long patch(@PathVariable("id") long id, @RequestBody OldBuildingTimetable reqTime) {
        TimetableService timetableService = new TimetableService();
        List<OldBuildingTimetable> target = oldBuildingTimetableRepository.findById(id);

        System.out.println(!timetableService.validateDay(reqTime.getDay()));
        System.out.println(!timetableService.validateTime(reqTime.getStart(), reqTime.getEnd()));
        System.out.println(timetableService.isDuplicateOldUpdate(reqTime, oldBuildingTimetableRepository.findByDay(
                reqTime.getDay())));

        if (
                !timetableService.validateDay(reqTime.getDay())
                        || !timetableService.validateTime(reqTime.getStart(), reqTime.getEnd())
                        || id != reqTime.getId()
                        || timetableService.isDuplicateOldUpdate(reqTime, oldBuildingTimetableRepository.findByDay(
                        reqTime.getDay()))
        ) {
            Throwable ex = new Throwable();
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Invalid day, time", ex);
        }

        OldBuildingTimetable targetTime = target.get(0);
        targetTime.setDay(reqTime.getDay());
        targetTime.setStart(reqTime.getStart());
        targetTime.setEnd(reqTime.getEnd());
        oldBuildingTimetableRepository.save(targetTime);
        return targetTime.getId();
//        OldBuildingTimetable res = oldBuildingTimetableRepository.update(id, reqTime).get(0);
//        return res.getId();
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") long id) {
        if (oldBuildingTimetableRepository.findById(id).size() == 0) {
            Throwable ex = new Throwable();
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "There is no such id in DB", ex);
        }

        return oldBuildingTimetableRepository.delete(id);
    }


}
