package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.SetInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetInfoRepository extends JpaRepository<SetInfo, Integer>, CustomSetInfoRepository {
}
