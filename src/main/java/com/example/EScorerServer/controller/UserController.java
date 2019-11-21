package com.example.EScorerServer.controller;

import com.example.EScorerServer.model.User;
import com.example.EScorerServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public @ResponseBody User addOrUpdateUser(@Valid @RequestBody User user)
    {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUserById(@PathVariable String id)
    {
        return userService.getUserById(id);
    }
}