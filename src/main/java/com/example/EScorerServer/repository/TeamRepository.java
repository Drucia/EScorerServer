package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer>, CustomTeamRepository {
}
