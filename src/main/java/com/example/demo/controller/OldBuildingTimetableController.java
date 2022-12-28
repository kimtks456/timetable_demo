package com.example.demo.controller;

import com.example.demo.repository.OldBuildingTimetable;
import com.example.demo.repository.OldBuildingTimetableRepository;
import com.example.demo.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/old", produces = "application/json;charset=UTF-8")
public class OldBuildingTimetableController {

    @Autowired
    private OldBuildingTimetableRepository oldBuildingTimetableRepository;

    @PostMapping(path = "/addtime")
    public @ResponseBody String addNewTime (
            @RequestParam String day,
            @RequestParam Integer start,
            @RequestParam Integer end,
            @RequestParam String user) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        TimetableService timetableService = new TimetableService();
        if (!timetableService.validateDay(day) || !timetableService.validateTime(start, end)) {
            Throwable ex = new Throwable();
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Invalid day, time", ex);
        }
        OldBuildingTimetable n = new OldBuildingTimetable();
        n.setDay(day);
        n.setStart(start);
        n.setEnd(end);
        n.setUser(user);
        oldBuildingTimetableRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/alltime")
    public @ResponseBody Iterable<OldBuildingTimetable> getAllTimeTables() {
        // This returns a JSON or XML with the users
        return oldBuildingTimetableRepository.findAll();
    }
}
