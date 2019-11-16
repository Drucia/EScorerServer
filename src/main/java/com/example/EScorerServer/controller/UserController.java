package com.example.EScorerServer.controller;

import com.example.EScorerServer.errors.UserNotFoundException;
import com.example.EScorerServer.model.Pair;
import com.example.EScorerServer.model.Player;
import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.model.User;
import com.example.EScorerServer.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
}