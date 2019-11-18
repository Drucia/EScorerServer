package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Match;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomMatchRepositoryImpl implements CustomMatchRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Match> getAllMatchesOfUser(String userId) {
        Query query = entityManager.createNativeQuery("select * from matches where USER_ID LIKE '" + userId + "';",
                Match.class);
        return query.getResultList();
    }
}
