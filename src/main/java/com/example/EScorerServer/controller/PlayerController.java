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
        Optional<Team> teamResult = teamService.getTeam(teamId);
        if (!teamResult.isPresent())
            throw new TeamNotFoundException(teamId);
        Team team = teamResult.get();
        players.forEach(player -> player.setTeam(team));
        return playerService.saveOrUpdatePlayersOfTeam(teamId, players);
    }

    @RequestMapping("/team/{teamId}")
    private @ResponseBody List<Player> getAllPlayersOfTeam(@PathVariable int teamId){
        return playerService.getPlayersOfTeam(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));
    }

    @PostMapping("/team")
    public @ResponseBody Pair<Team, Player> saveOrUpdateWholeTeam(@RequestParam String userId,
                                                                  @RequestBody Pair<Team, Player> pair)
    {
        Team team = pair.getTeam();
        team.setUserId(userId);
        Team newTeam = teamService.saveOrUpdateTeam(team);
        List<Player> newPlayers = pair.getPlayers();
        newPlayers.forEach(player -> player.setTeam(newTeam));
        newPlayers = playerService.saveOrUpdatePlayersOfTeam(pair.getTeam().getId(), newPlayers);

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
