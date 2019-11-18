package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Summary;

import java.util.List;

public interface CustomSummaryRepository {
    List<Summary> getAllSummariesOfUser(String userId);
}
