package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    Optional<List<Player>> getPlayersOfTeam(int teamId);
    void delete(Player player);
    void deleteAll(List<Player> players);
    Player save(Player player);
    List<Player> saveOrUpdatePlayersOfTeam(int teamId, List<Player> players);
}
