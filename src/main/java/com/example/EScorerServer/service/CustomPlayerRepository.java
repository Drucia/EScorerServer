package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Player;

import java.util.List;

public interface CustomPlayerRepository {
    List<Player> getAllPlayersOfTeam(int teamId);
}
