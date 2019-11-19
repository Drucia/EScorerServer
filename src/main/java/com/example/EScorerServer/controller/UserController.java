package com.example.EScorerServer.controller;

import com.example.EScorerServer.model.User;
import com.example.EScorerServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/users")
@RequiredArgsConstructor
public class UserController {

    private final MatchController matchController;
    private final SummaryController summaryController;
    private final SetInfoController setInfoController;

    private final UserService userService;

    @PostMapping
    public @ResponseBody
    User addOrUpdateUser(@Valid @RequestBody User user)
    {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUserById(@PathVariable String id)
    {
        return userService.getUserById(id);
    }
}