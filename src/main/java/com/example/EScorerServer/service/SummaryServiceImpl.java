package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Summary;
import com.example.EScorerServer.repository.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SummaryServiceImpl implements SummaryService {
    @Autowired
    private SummaryRepository summaryRepository;

    @Override
    public Optional<List<Summary>> getAllSummariesOfUser(String userId) {
        return summaryRepository.getAllSummariesOfUser(userId);
    }

    @Override
    public void deleteSummary(Summary summary)
    {
        summaryRepository.delete(summary);
    }

    @Override
    public Optional<Summary> getSummaryOfMatch(int matchId) {
        return summaryRepository.getSummaryByMatch(matchId);
    }

    @Override
    public Summary save(Summary summary) {
        return summaryRepository.save(summary);
    }
}
