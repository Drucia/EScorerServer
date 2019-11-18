package com.example.EScorerServer.service;

import com.example.EScorerServer.errors.SummaryNotFoundException;
import com.example.EScorerServer.model.Match;
import com.example.EScorerServer.model.SetInfo;
import com.example.EScorerServer.model.Summary;
import com.example.EScorerServer.response.SummaryResponse;

import java.util.ArrayList;
import java.util.List;

public class SummaryService {
    public static Summary getSummaryFromSummaryResponse(SummaryResponse response, Match match)
    {
        return Summary.makeFromBody(response, match);
    }

    public static SummaryResponse getSummaryResponseFromSummary(Summary summary, Match match, List<SetInfo> setInfo) {
        return SummaryResponse.makeFromBody(summary, match, setInfo);
    }

    public static List<SummaryResponse> getSummaryResponseFromSummaries(List<Summary> summaries, List<Match> matches) {
        if (summaries.size() != matches.size())
            throw new SummaryNotFoundException(-1);

        List<SummaryResponse> responses = new ArrayList<>();
        for (int i = 0; i < summaries.size(); i++)
        {
            Summary summary = summaries.get(i);
            SummaryResponse response = SummaryResponse.makeFromBody(summary, matches.get(i), summary.getSets());
            responses.add(response);
        }

        if (responses.size() != summaries.size())
            throw new SummaryNotFoundException(-1);

        return responses;
    }
}
