package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Player;

import java.util.List;

public interface CustomPlayerRepository {
    List<Player> getAllPlayersOfTeam(int teamId);
}
