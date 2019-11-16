package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
