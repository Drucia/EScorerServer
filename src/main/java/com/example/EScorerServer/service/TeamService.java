package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> getAllTeamsOfUser(String userId);
    Team saveOrUpdateTeam(Team team);
    Optional<Team> getTeam(int teamId);
}
