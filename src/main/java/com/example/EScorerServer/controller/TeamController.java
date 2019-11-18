package com.example.EScorerServer.controller;

import com.example.EScorerServer.errors.TeamNotFoundException;
import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public @ResponseBody Team saveOrUpdateTeam(@Valid @RequestBody Team team)
    {
        return teamService.saveOrUpdateTeam(team);
    }

    @GetMapping("/{teamId}")
    public @ResponseBody Team getTeam(@PathVariable int teamId) {
        return teamService.getTeam(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));
    }

    @GetMapping("/user/{userId}")
    public @ResponseBody List<Team> getTeams(@PathVariable String userId)
    {
        return teamService.getAllTeamsOfUser(userId);
    }
}
