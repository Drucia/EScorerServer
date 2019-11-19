package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Summary;

import java.util.List;
import java.util.Optional;

public interface CustomSummaryRepository {
    Optional<List<Summary>> getAllSummariesOfUser(String userId);
    Optional<Summary> getSummaryByMatch(int matchId);
}
