package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class CustomTeamRepositoryImpl implements CustomTeamRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<List<Team>> getAllTeamsOfUser(String userId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM teams WHERE USER_ID LIKE '" +
                userId + "';", Team.class);
        List<Team> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result);
    }
}
