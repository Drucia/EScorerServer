package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Summary;

import java.util.List;
import java.util.Optional;

public interface SummaryService {
    Optional<List<Summary>> getAllSummariesOfUser(String userId);
    void deleteSummary(int summaryId);
    Optional<Summary> getSummaryOfMatch(int matchId);
    Summary save(Summary summary);
    Optional<Summary> getSummary(int summaryId);
}
