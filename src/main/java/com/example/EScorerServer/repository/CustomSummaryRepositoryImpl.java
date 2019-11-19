package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Summary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class CustomSummaryRepositoryImpl implements CustomSummaryRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<List<Summary>> getAllSummariesOfUser(String userId) {
        Query query = entityManager.createNativeQuery("select s.ID, s.MATCH_ID, s.WINNER_ID, s.SETS_HOME," +
                " s.SETS_GUEST\n" +
                "from summary s join matches m on s.MATCH_ID = m.ID\n" +
                "where m.USER_ID LIKE '" + userId + "';", Summary.class);
        List<Summary> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result);
    }

    @Override
    public Optional<Summary> getSummaryByMatch(int matchId) {
        Query query = entityManager.createNativeQuery("select * from summary where MATCH_ID = " + matchId +";",
                Summary.class);
        Summary result = (Summary) query.getSingleResult();
        return result == null ? Optional.empty() : Optional.of(result);
    }
}
