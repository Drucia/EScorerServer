package com.example.EScorerServer.controller;

import com.example.EScorerServer.model.RefereeClass;
import com.example.EScorerServer.repository.RefereeClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/refereeClasses")
public class RefereeClassController {

    @Autowired
    private RefereeClassesRepository repository;

    @PostMapping()
    public @ResponseBody RefereeClass addRefereeClass(@RequestBody RefereeClass refereeClass)
    {
        return repository.save(refereeClass);
    }

    @GetMapping
    public @ResponseBody Iterable<RefereeClass> getAllRefereeClasses()
    {
        return repository.findAll();
    }
}
