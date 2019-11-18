package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Player;

import java.util.List;
import java.util.Optional;

public interface CustomPlayerRepository {
    Optional<List<Player>> getAllPlayersOfTeam(int teamId);
}
