package com.example.EScorerServer.controller;

import com.example.EScorerServer.errors.UserNotFoundException;
import com.example.EScorerServer.model.User;
import com.example.EScorerServer.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public @ResponseBody Iterable<User> index()
    {
        return repository.findAll();
    }

    @PostMapping
    public @ResponseBody User updateUser(@RequestBody User user)
    {
        return repository.save(user);
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUserById(@PathVariable String id)
    {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}