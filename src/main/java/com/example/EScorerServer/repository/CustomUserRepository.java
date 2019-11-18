package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Player;
import com.example.EScorerServer.model.Team;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository {
    Optional<Iterable<Player>> getAllPlayersOfUserTeams(String userId);
    Optional<Iterable<Player>> getAllPlayersOfUserSpecificTeam(String userId, int teamId);
    boolean save(Team team);
}
