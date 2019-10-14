package com.example.EScorerServer.controller;

import com.example.EScorerServer.model.RefereeClass;
import com.example.EScorerServer.service.RefereeClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/refereeClasses")
public class RefereeClassController {

    @Autowired
    private RefereeClassRepository repository;

    @PostMapping(path="/add")
    public @ResponseBody String addRefereeClass(@RequestBody RefereeClass refereeClass)
    {
        repository.save(refereeClass);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<RefereeClass> getAllRefereeClasses()
    {
        return repository.loadAll();
    }
}
