package com.example.EScorerServer.controller;

import com.example.EScorerServer.errors.MatchNotFoundException;
import com.example.EScorerServer.errors.UserNotFoundException;
import com.example.EScorerServer.model.Match;
import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class MatchController {
    @Autowired
    private MatchRepository repository;
    @Autowired
    private TeamController teamController;

    public Match getUserMatch(String userId, int matchId) {
        // todo check if user is owner of match
        return repository.findById(matchId).orElseThrow(() -> new MatchNotFoundException(matchId));
    }

    public Match saveMatch(String id, Match match) {
        if (!id.equals(match.getUserId()))
            throw new UserNotFoundException(id, match.getId());
        else
        {
            Optional<Team> team = teamController.getTeam(match.getHost_team());
            if (!team.isPresent())
                match.setHost_team(teamController.saveOrUpdateTeam(match.getHost_team()));
            team = teamController.getTeam(match.getGuest_team());
            if (!team.isPresent())
                match.setGuest_team(teamController.saveOrUpdateTeam(match.getGuest_team()));
            return repository.save(match);
        }
    }
}
