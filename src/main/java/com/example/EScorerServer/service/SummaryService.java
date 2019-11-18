package com.example.EScorerServer.service;

public interface SummaryService {
//    public static Summary getSummaryFromSummaryResponse(SummaryResponse response, Match match)
//    {
//        return Summary.makeFromBody(response, match);
//    }
//
//    public static SummaryResponse getSummaryResponseFromSummary(Summary summary, Match match, List<SetInfo> setInfo) {
//        return SummaryResponse.makeFromBody(summary, match, setInfo);
//    }
//
//    public static List<SummaryResponse> getSummaryResponseFromSummaries(List<Summary> summaries, List<Match> matches) {
//        if (summaries.size() != matches.size())
//            throw new SummaryNotFoundException(-1);
//
//        List<SummaryResponse> responses = new ArrayList<>();
//        for (int i = 0; i < summaries.size(); i++)
//        {
//            Summary summary = summaries.get(i);
//            SummaryResponse response = SummaryResponse.makeFromBody(summary, matches.get(i), summary.getSets());
//            responses.add(response);
//        }
//
//        if (responses.size() != summaries.size())
//            throw new SummaryNotFoundException(-1);
//
//        return responses;
//    }
}
