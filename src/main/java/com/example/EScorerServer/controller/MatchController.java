package com.example.EScorerServer.controller;

import com.example.EScorerServer.errors.MatchNotFoundException;
import com.example.EScorerServer.errors.UserNotFoundException;
import com.example.EScorerServer.model.Match;
import com.example.EScorerServer.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MatchController {
    @Autowired
    private MatchRepository repository;

    public Match getUserMatch(String userId, int matchId) {
        // todo check if user is owner of match
        return repository.findById(matchId).orElseThrow(() -> new MatchNotFoundException(matchId));
    }

    public Match saveMatch(String id, Match match) {
        if (!id.equals(match.getUserId()))
            throw new UserNotFoundException(id, match.getId());
        else
        {
            return repository.save(match);
        }
    }
}
