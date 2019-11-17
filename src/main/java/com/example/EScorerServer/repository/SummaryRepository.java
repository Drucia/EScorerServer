package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummaryRepository extends JpaRepository<Summary, Integer> {
}
