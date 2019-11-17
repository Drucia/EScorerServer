package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Match;
import com.example.EScorerServer.model.SetInfo;
import com.example.EScorerServer.model.Summary;
import com.example.EScorerServer.response.SummaryResponse;

import java.util.List;

public class SummaryService {
    public static Summary getSummaryFromSummaryResponse(SummaryResponse response, Match match)
    {
        return Summary.makeFromBody(response, match);
    }

    public static SummaryResponse getSummaryResponseFromSummary(Summary summary, Match match, List<SetInfo> setInfo) {
        return SummaryResponse.makeFromBody(summary, match, setInfo);
    }
}
