package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Match;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class CustomMatchRepositoryImpl implements CustomMatchRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<List<Match>> getAllMatchesOfUser(String userId) {
        Query query = entityManager.createNativeQuery("select * from matches where USER_ID LIKE '" + userId + "';",
                Match.class);
        List<Match> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result);
    }
}
