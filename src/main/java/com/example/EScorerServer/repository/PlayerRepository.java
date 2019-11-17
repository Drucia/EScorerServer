package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlayerRepository extends JpaRepository<Player, Integer>, CustomPlayerRepository {
}
