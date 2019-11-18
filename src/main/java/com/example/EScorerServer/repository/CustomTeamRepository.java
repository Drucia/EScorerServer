package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Team;

import java.util.List;
import java.util.Optional;

public interface CustomTeamRepository {
    Optional<List<Team>> getAllTeamsOfUser(String userId);
}
