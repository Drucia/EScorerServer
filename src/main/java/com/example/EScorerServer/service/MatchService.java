package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Match;

import java.util.List;
import java.util.Optional;

public interface MatchService {
    Match save(Match match);
    Optional<List<Match>> getAllMatchesOfUser(String userId);
//    public static Match getMatchFromSummaryResponseAndSummary(SummaryResponse response)
//    {
//        return Match.makeFromBody(response.getMatch(), response.getDate());
//    }
}
