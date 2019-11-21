package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Summary;
import com.example.EScorerServer.repository.SummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {
    private final SummaryRepository summaryRepository;

    @Override
    public Optional<List<Summary>> getAllSummariesOfUser(String userId) {
        return summaryRepository.getAllSummariesOfUser(userId);
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
