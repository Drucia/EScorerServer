package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Match;

import java.util.List;
import java.util.Optional;

public interface CustomMatchRepository {
    Optional<List<Match>> getAllMatchesOfUser(String userId);
}
