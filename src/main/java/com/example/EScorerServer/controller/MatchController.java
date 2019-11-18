package com.example.EScorerServer.controller;

import com.example.EScorerServer.errors.MatchNotFoundException;
import com.example.EScorerServer.errors.UserNotFoundException;
import com.example.EScorerServer.model.Match;
import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.repository.MatchRepository;
import com.example.EScorerServer.response.MatchResponse;
import com.example.EScorerServer.service.MatchService;
import com.example.EScorerServer.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/matches")
@RequiredArgsConstructor
public class MatchController {
    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

//    public Match getUserMatch(String userId, int matchId) {
//        // todo check if user is owner of match
//        return repository.findById(matchId).orElseThrow(() -> new MatchNotFoundException(matchId));
//    }

    @PostMapping("/user/{userId}")
    public @ResponseBody MatchResponse save(@PathVariable String userId, @Valid @RequestBody MatchResponse match)
    {
        Match matchEntity = Match.makeFromBody(match);
        matchEntity.setUserId(userId);
        Optional<Team> team = teamService.getTeam(matchEntity.getHost_team().getId());
        if (!team.isPresent())
            matchEntity.setHost_team(teamService.saveOrUpdateTeam(matchEntity.getHost_team()));
        team = teamService.getTeam(matchEntity.getGuest_team().getId());
        if (!team.isPresent())
            matchEntity.setGuest_team(teamService.saveOrUpdateTeam(matchEntity.getGuest_team()));
        return MatchResponse.makeFromBody(matchService.save(matchEntity));
    }

    @RequestMapping
    public @ResponseBody List<MatchResponse> getAllUserMatches(String userId) {
        Optional<List<Match>> result = matchService.getAllMatchesOfUser(userId);
        if (!result.isPresent())
            throw new UserNotFoundException(userId);
        return result.get().stream().map(MatchResponse::makeFromBody).collect(Collectors.toList());
    }
}
