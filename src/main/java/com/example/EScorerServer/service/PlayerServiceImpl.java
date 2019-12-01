package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Player;
import com.example.EScorerServer.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Optional<List<Player>> getPlayersOfTeam(int teamId) {
        return playerRepository.getAllPlayersOfTeam(teamId);
    }

    @Override
    public void delete(Player player) {
        playerRepository.delete(player);
    }

    @Override
    public void deleteAll(List<Player> players) {
        playerRepository.deleteAll(players);
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public List<Player> saveOrUpdatePlayersOfTeam(int teamId, List<Player> players) {
        Optional<List<Player>> oldPlayers = getPlayersOfTeam(teamId);
        if (!oldPlayers.isPresent())
            oldPlayers = Optional.of(new ArrayList<>());
        List<Player> playersToDelete = new ArrayList<>(oldPlayers.get());
        playersToDelete.removeAll(players);
        if (!playersToDelete.isEmpty())
            deleteAll(playersToDelete);
        return playerRepository.saveAll(players);
    }
}
