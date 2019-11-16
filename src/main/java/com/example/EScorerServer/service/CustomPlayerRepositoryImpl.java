package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Player;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CustomPlayerRepositoryImpl implements CustomPlayerRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Player> getAllPlayersOfTeam(int teamId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM players WHERE TEAM_ID = " +
                teamId + ";", Player.class);
        return query.getResultList();
    }
}
