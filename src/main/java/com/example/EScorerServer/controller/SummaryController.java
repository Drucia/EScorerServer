package com.example.EScorerServer.controller;

import com.example.EScorerServer.model.Match;
import com.example.EScorerServer.model.Summary;
import com.example.EScorerServer.response.SummaryResponse;
import com.example.EScorerServer.service.MatchService;
import com.example.EScorerServer.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/summaries")
@RequiredArgsConstructor
public class SummaryController {
    private final SummaryService summaryService;
    private final MatchService matchService;

    @GetMapping("/user/{userId}")
    public @ResponseBody List<SummaryResponse> getAllSummariesOfUser(@PathVariable String userId)
    {
        List<Summary> summaries = summaryController.getAllSummariesOfUserMatches(id);
        List<Match> matches = matchController.getAllUserMatches(id);
        return SummaryService.getSummaryResponseFromSummaries(summaries, matches);
    }

    @GetMapping("/{id}/matches/{matchId}")
    public @ResponseBody SummaryResponse getSummaryOfMatch(@PathVariable String id, @PathVariable int matchId)
    {
        Summary summary = summaryController.getSummaryOfUserMatch(id, matchId);
        return SummaryService.getSummaryResponseFromSummary(summary,
                matchController.getUserMatch(id, matchId), summary.getSets());
    }

//    public Summary getSummaryOfUserMatch(String userId, int matchId) {
//        // todo check if is user is connected with this match
//        return repository.findById(matchId).orElseThrow(() -> new MatchNotFoundException(matchId));
//    }
//
//    public Summary saveSummaryForMatch(Match match, Summary summary) {
//        if (match.getId() != summary.getMatchId())
//            throw new MatchNotFoundException(match.getId());
//        else
//            return repository.save(summary);
//    }
//
//    public List<Summary> getAllSummariesOfUserMatches(String userId) {
//        return repository.getAllSummariesOfUser(userId).stream().sorted(Comparator.comparingInt(Summary::getMatchId))
//                .collect(Collectors.toList());
//    }
}
