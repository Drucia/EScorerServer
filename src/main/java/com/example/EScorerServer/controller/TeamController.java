package com.example.EScorerServer.controller;

import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TeamController {
    @Autowired
    private TeamRepository repository;

    public Team saveOrUpdateTeam(Team team)
    {
        return repository.save(team);
    }
}
