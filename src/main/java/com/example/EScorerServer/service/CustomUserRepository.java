package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Player;
import com.example.EScorerServer.model.Team;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository {
    Optional<List<Team>> getAllTeamsOfUser(String userId);
    Optional<Iterable<Player>> getAllPlayersOfUserTeams(String userId);
    Optional<Iterable<Player>> getAllPlayersOfUserSpecificTeam(String userId, int teamId);
    boolean save(Team team);
}
