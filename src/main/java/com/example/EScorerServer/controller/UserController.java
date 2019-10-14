package com.example.EScorerServer.controller;

import com.example.EScorerServer.model.User;
import com.example.EScorerServer.service.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/users")
public class UserController {

    private UserRepository repository;

    @RequestMapping(value = "/users")
    public String index()
    {
        return "users";
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestBody User user)
    {
        repository.save(user);
        return "add";
    }

}
