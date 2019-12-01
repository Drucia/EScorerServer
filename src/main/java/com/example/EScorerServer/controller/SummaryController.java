package com.example.EScorerServer.controller;

import com.example.EScorerServer.errors.SummaryNotFoundException;
import com.example.EScorerServer.errors.UserNotFoundException;
import com.example.EScorerServer.model.Match;
import com.example.EScorerServer.model.SetInfo;
import com.example.EScorerServer.model.Summary;
import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.response.SummaryResponse;
import com.example.EScorerServer.service.MatchService;
import com.example.EScorerServer.service.SetInfoService;
import com.example.EScorerServer.service.SummaryService;
import com.example.EScorerServer.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/summaries")
public class SummaryController {
    @Autowired
    private SummaryService summaryService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private SetInfoService setInfoService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/user/{userId}")
    public @ResponseBody List<SummaryResponse> getAllSummariesOfUser(@PathVariable String userId)
    {
        Optional<List<Summary>> summariesResult = summaryService.getAllSummariesOfUser(userId);
        if (!summariesResult.isPresent())
            throw new UserNotFoundException(userId);
        List<Summary> summaries = summariesResult.get();
        Optional<List<Match>> matchesResult = matchService.getAllMatchesOfUser(userId);
        if (!matchesResult.isPresent())
            throw new UserNotFoundException(userId);
        List<Match> matches = matchesResult.get();
        assert matches.size() == summaries.size();
        return SummaryResponse.makeFromBody(summaries, matches);
    }

    @PostMapping("/user/{userId}")
    public @ResponseBody SummaryResponse saveSummaryOfUserMatch(@PathVariable String userId,
                                                            @RequestBody SummaryResponse matchSummary)
    {
        Match match = Match.makeFromBody(matchSummary.getMatch());
        match.setUserId(userId);
        boolean isHostWinner = matchSummary.getWinner().getId() == matchSummary.getMatch().getHostTeam().getId();
        Optional<Team> team = teamService.getTeam(match.getHost_team().getId());
        if (!team.isPresent()) {
            match.setHost_team(teamService.saveOrUpdateTeam(match.getHost_team()));
        }
        team = teamService.getTeam(match.getGuest_team().getId());
        if (!team.isPresent()) {
            match.setGuest_team(teamService.saveOrUpdateTeam(match.getGuest_team()));
        }
        match = matchService.save(match);
        Summary summary = Summary.makeFromBody(matchSummary, match);
        summary.setMatchId(match.getId());
        summary.setWinner(isHostWinner ? match.getHost_team() : match.getGuest_team());
        summary = summaryService.save(summary);
        List<SetInfo> setsInfo = SetInfo.makeFromBody(matchSummary.getSets(), summary);
        setsInfo = setInfoService.saveAll(setsInfo);
        return SummaryResponse.makeFromBody(summary, match, setsInfo);
    }

    @GetMapping("/match/{matchId}")
    public @ResponseBody SummaryResponse getSummaryOfMatch(@PathVariable int matchId)
    {
        Optional<Summary> summaryResult = summaryService.getSummaryOfMatch(matchId);
        if (!summaryResult.isPresent())
            throw new SummaryNotFoundException(matchId);
        Summary summary = summaryResult.get();
        Optional<Match> matchResult = matchService.getMatch(matchId);
        Match match = matchResult.get();
        return SummaryResponse.makeFromBody(summary, match, summary.getSets());
    }
}
