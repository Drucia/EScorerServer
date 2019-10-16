package com.example.EScorerServer.service;

import com.example.EScorerServer.model.RefereeClass;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RefereeClassesRepository extends JpaRepository<RefereeClass, String> {
}
