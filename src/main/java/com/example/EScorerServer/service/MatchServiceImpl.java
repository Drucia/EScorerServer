package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Match;
import com.example.EScorerServer.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchRepository matchRepository;

    @Override
    public Match save(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public Optional<List<Match>> getAllMatchesOfUser(String userId) {
        return matchRepository.getAllMatchesOfUser(userId);
    }

    @Override
    public Optional<Match> getMatch(int matchId) {
        return matchRepository.findById(matchId);
    }

    @Override
    public void deleteMatch(int matchId) {
        matchRepository.deleteById(matchId);
    }
}
