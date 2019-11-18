package com.example.EScorerServer.controller;

import com.example.EScorerServer.errors.TeamNotFoundException;
import com.example.EScorerServer.model.Pair;
import com.example.EScorerServer.model.Player;
import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.service.PlayerService;
import com.example.EScorerServer.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    @PostMapping
    public @ResponseBody Player savePlayer(@Valid @RequestBody Player player)
    {
        return playerService.save(player);
    }

    @PostMapping("/team/{teamId}/several")
    public @ResponseBody List<Player> saveOrUpdateSeveralPlayersOfTeam(
            @PathVariable int teamId, @RequestBody List<Player> players)
    {
        return playerService.saveOrUpdatePlayersOfTeam(teamId, players);
    }

    @RequestMapping("/team/{teamId}")
    private @ResponseBody List<Player> getAllPlayersOfTeam(@PathVariable int teamId){
        return playerService.getPlayersOfTeam(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));
    }

    @PostMapping("/team")
    public @ResponseBody Pair<Team, Player> updateWholeTeam(@PathVariable String id, @RequestBody Pair<Team, Player> pair)
    {
        Team team = pair.getTeam();
        team.setUserId(id);
        Team newTeam = teamService.saveOrUpdateTeam(team);
        List<Player> newPlayers = pair.getPlayers().stream().peek(player -> player.setTeam(newTeam))
                .collect(Collectors.toList());
        playerService.saveOrUpdatePlayersOfTeam(pair.getTeam().getId(), newPlayers);

        return new Pair<>(newTeam, newPlayers);
    }

    @DeleteMapping
    private @ResponseBody boolean deletePlayer(@RequestBody Player player)
    {
        playerService.delete(player);
        return true;
    }

    @DeleteMapping("several")
    public @ResponseBody boolean deleteSeveralPlayers(@RequestBody List<Player> players)
    {
        playerService.deleteAll(players);
        return true;
    }
}
