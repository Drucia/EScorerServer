package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Match;
import com.example.EScorerServer.response.SummaryResponse;

public class MatchService {
    public static Match getMatchFromSummaryResponseAndSummary(SummaryResponse response)
    {
        return Match.makeFromBody(response.getMatch(), response.getDate());
    }
}
