package com.example.demo.controller;

import com.example.demo.repository.NewBuildingTimetable;
import com.example.demo.repository.NewBuildingTimetableRepository;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

// Contoller가 View page return하는 반면, RestController는 String같은 data나 JSON을 return
@RestController
@Transactional
@RequestMapping(path = "/new", produces = "application/json;charset=UTF-8")
public class NewBuildingTimetableController {

    @Autowired
    private NewBuildingTimetableRepository newBuildingTimetableRepository;

    @GetMapping
    public @ResponseBody List<NewBuildingTimetable> get() {
        // This returns a JSON or XML with the users
        return newBuildingTimetableRepository.findAll();
    }

    @PostMapping // @RequestMapping(method=RequestMethod.POST, value=..)의 축약형
    public @ResponseBody String post(@RequestBody TimetableVO reqTime) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        TimetableService timetableService = new TimetableService();

        NewBuildingTimetable n = new NewBuildingTimetable();
        n.setDay(reqTime.getDay());
        n.setStart(reqTime.getStart());
        n.setEnd(reqTime.getEnd());
        n.setUser(reqTime.getUser());

        if (
                !timetableService.validateDay(reqTime.getDay())
                        || !timetableService.validateTime(reqTime.getStart(), reqTime.getEnd())
                        || timetableService.isDuplicateNew(n,
                        newBuildingTimetableRepository.findByDay(reqTime.getDay()))
        ) {
            Throwable ex = new Throwable();
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Invalid day, time", ex);
        }

        newBuildingTimetableRepository.save(n);
        return "Saved";
    }

    @PatchMapping("/{id}")
    public long patch(@PathVariable("id") long id, @RequestBody NewBuildingTimetable reqTime) {
        TimetableService timetableService = new TimetableService();
        List<NewBuildingTimetable> target = newBuildingTimetableRepository.findById(id);

        System.out.println(!timetableService.validateDay(reqTime.getDay()));
        System.out.println(!timetableService.validateTime(reqTime.getStart(), reqTime.getEnd()));
        System.out.println(timetableService.isDuplicateNewUpdate(reqTime, newBuildingTimetableRepository.findByDay(
                reqTime.getDay())));

        if (
                !timetableService.validateDay(reqTime.getDay())
                        || !timetableService.validateTime(reqTime.getStart(), reqTime.getEnd())
                        || id != reqTime.getId()
                        || timetableService.isDuplicateNewUpdate(reqTime, newBuildingTimetableRepository.findByDay(
                        reqTime.getDay()))
        ) {
            Throwable ex = new Throwable();
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Invalid day, time", ex);
        }

        NewBuildingTimetable targetTime = target.get(0);
        targetTime.setDay(reqTime.getDay());
        targetTime.setStart(reqTime.getStart());
        targetTime.setEnd(reqTime.getEnd());
        newBuildingTimetableRepository.save(targetTime);
        return targetTime.getId();
//        NewBuildingTimetable res = newBuildingTimetableRepository.update(id, reqTime).get(0);
//        return res.getId();
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") long id) {
        if (newBuildingTimetableRepository.findById(id).size() == 0) {
            Throwable ex = new Throwable();
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "There is no such id in DB", ex);
        }
        return newBuildingTimetableRepository.delete(id);
    }
}
