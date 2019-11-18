package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Match;

import java.util.List;

public interface CustomMatchRepository {
    List<Match> getAllMatchesOfUser(String userId);
}
