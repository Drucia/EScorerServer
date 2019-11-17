package com.example.EScorerServer.controller;

import com.example.EScorerServer.model.Player;
import com.example.EScorerServer.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class PlayerController {
    @Autowired
    private PlayerRepository repository;

    public Player savePlayer(Player player)
    {
        return repository.save(player);
    }

    public void saveSeveralPlayers(int teamId, List<Player> players)
    {
        List<Player> oldPlayers = getAllPlayersOfTeam(teamId);
        List<Player> playersToDelete = new ArrayList<>(oldPlayers);
        playersToDelete.removeAll(players);
        if (!playersToDelete.isEmpty())
            deleteSeveralPlayers(playersToDelete);
        repository.saveAll(players);
    }

    private List<Player> getAllPlayersOfTeam(int teamId){
        return repository.getAllPlayersOfTeam(teamId);
    }

    @DeleteMapping
    public void deletePlayer(@RequestBody Player player)
    {
        repository.delete(player);
    }

    public void deleteSeveralPlayers(Iterable<Player> players)
    {
        repository.deleteAll(players);
    }
}
