package com.example.EScorerServer.service;

import com.example.EScorerServer.errors.UserNotFoundException;
import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public List<Team> getAllTeamsOfUser(String userId) {
        return teamRepository.getAllTeamsOfUser(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public Team saveOrUpdateTeam(Team team)
    {
        return teamRepository.save(team);
    }

    @Override
    public Optional<Team> getTeam(int teamId) {
        return teamRepository.findById(teamId);
    }
}
