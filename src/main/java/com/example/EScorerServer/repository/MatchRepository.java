package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Integer>,
        CustomMatchRepository {
}
