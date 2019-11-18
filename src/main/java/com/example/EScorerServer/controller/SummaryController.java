package com.example.EScorerServer.controller;

import com.example.EScorerServer.errors.MatchNotFoundException;
import com.example.EScorerServer.model.Match;
import com.example.EScorerServer.model.Summary;
import com.example.EScorerServer.repository.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SummaryController {
    @Autowired
    private SummaryRepository repository;

    public Summary getSummaryOfUserMatch(String userId, int matchId) {
        // todo check if is user is connected with this match
        return repository.findById(matchId).orElseThrow(() -> new MatchNotFoundException(matchId));
    }

    public Summary saveSummaryForMatch(Match match, Summary summary) {
        if (match.getId() != summary.getMatchId())
            throw new MatchNotFoundException(match.getId());
        else
            return repository.save(summary);
    }

    public List<Summary> getAllSummariesOfUserMatches(String userId) {
        return repository.getAllSummariesOfUser(userId).stream().sorted(Comparator.comparingInt(Summary::getMatchId))
                .collect(Collectors.toList());
    }
}
