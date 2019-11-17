package com.example.EScorerServer.controller;

import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class TeamController {
    @Autowired
    private TeamRepository repository;

    public Team saveOrUpdateTeam(Team team)
    {
        return repository.save(team);
    }

    public Optional<Team> getTeam(Team team) {
        return repository.findById(team.getId());
    }
}
