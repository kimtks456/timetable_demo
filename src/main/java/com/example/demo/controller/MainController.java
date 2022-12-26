package com.example.demo.controller;

import com.example.demo.repository.TimeTable;
import com.example.demo.repository.TimeTableRepository;
import com.example.demo.repository.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller // This means that this class is a Controller
@RestController // This means that returns a JSON or XML with the users
@RequestMapping(path="/demo", produces="application/json;charset=UTF-8") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;
    @Autowired
    private TimeTableRepository timeTableRepository;

    @GetMapping(path="")
    public String test() {
        return "/demo : hello, San";
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @PostMapping(path="/addtime")
    public @ResponseBody String addNewTime (
            @RequestParam String day,
            @RequestParam String start,
            @RequestParam String end,
            @RequestParam String user) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        TimeTable n = new TimeTable();
        n.setDay(day);
        n.setStart(start);
        n.setEnd(end);
        n.setUser(user);
        timeTableRepository.save(n);
        return "Saved";
    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path="/alltime")
    public @ResponseBody Iterable<TimeTable> getAllTimeTables() {
        // This returns a JSON or XML with the users
        return timeTableRepository.findAll();
    }
}