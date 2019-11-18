package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Summary;
import com.example.EScorerServer.model.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomSummaryRepositoryImpl implements CustomSummaryRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Summary> getAllSummariesOfUser(String userId) {
        Query query = entityManager.createNativeQuery("select s.ID, s.MATCH_ID, s.WINNER_ID, s.SETS_HOME," +
                " s.SETS_GUEST\n" +
                "from summary s join matches m on s.MATCH_ID = m.ID\n" +
                "where m.USER_ID LIKE '" + userId + "';", Summary.class);
        return query.getResultList();
    }
}
