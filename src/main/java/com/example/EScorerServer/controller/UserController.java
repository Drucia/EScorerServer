package com.example.EScorerServer.controller;

import com.example.EScorerServer.errors.UserNotFoundException;
import com.example.EScorerServer.model.*;
import com.example.EScorerServer.response.SummaryResponse;
import com.example.EScorerServer.repository.UserRepository;
import com.example.EScorerServer.service.MatchService;
import com.example.EScorerServer.service.SetsInfoService;
import com.example.EScorerServer.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PlayerController playerController;
    @Autowired
    private TeamController teamController;
    @Autowired
    private MatchController matchController;
    @Autowired
    private SummaryController summaryController;
    @Autowired
    private SetInfoController setInfoController;

    @GetMapping
    public @ResponseBody Iterable<User> index()
    {
        return repository.findAll();
    }

    @PostMapping
    public @ResponseBody User updateUser(@RequestBody User user)
    {
        return repository.save(user);
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUserById(@PathVariable String id)
    {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/{id}/teams")
    public @ResponseBody List<Team> getTeams(@PathVariable String id)
    {
        return repository.getAllTeamsOfUser(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/{id}/teams/players")
    public @ResponseBody Iterable<Player> getAllPlayersOfTeams(@PathVariable String id)
    {
        return repository.getAllPlayersOfUserTeams(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/{id}/teams/{teamId}/players")
    public @ResponseBody Iterable<Player> getPlayersOfSpecificTeam(@PathVariable String id, @PathVariable int teamId)
    {
        return repository.getAllPlayersOfUserSpecificTeam(id, teamId)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/{id}/teams")
    public @ResponseBody Pair<Team, Player> updateAllTeam(@PathVariable String id, @RequestBody Pair<Team, Player> pair)
    {
        Team team = pair.getTeam();
        team.setUserId(id);
        Team newTeam = teamController.saveOrUpdateTeam(team);
        List<Player> newPlayers = pair.getPlayers().stream().peek(player -> player.setTeam(newTeam))
                .collect(Collectors.toList());
        playerController.saveSeveralPlayers(pair.getTeam().getId(), newPlayers);

        return new Pair<>(newTeam, newPlayers);
    }

    @PostMapping("/{id}/matches")
    public @ResponseBody SummaryResponse saveSummaryOfMatch(@PathVariable String id,
                                                            @RequestBody SummaryResponse matchSummary)
    {
        Match match = MatchService.getMatchFromSummaryResponseAndSummary(matchSummary);
        match = matchController.saveMatch(id, match);
        Summary summary = SummaryService.getSummaryFromSummaryResponse(matchSummary, match);
        summary = summaryController.saveSummaryForMatch(match, summary);
        List<SetInfo> setInfos = SetsInfoService.getSetsInfoListFromSummaryResponseAndSummary(matchSummary, summary);
        setInfos = setInfoController.saveSetsForSummary(summary, setInfos);
        return SummaryResponse.makeFromBody(summary, match, setInfos);
    }

    @GetMapping("/{id}/matches")
    public @ResponseBody List<SummaryResponse> getAllSummariesOfUser(@PathVariable String id)
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
}