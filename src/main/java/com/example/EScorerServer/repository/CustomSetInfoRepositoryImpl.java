package com.example.EScorerServer.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional(readOnly = true)
public class CustomSetInfoRepositoryImpl implements CustomSetInfoRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public boolean deleteAllBySummaryId(int summaryId) {
        Query query = entityManager.createNativeQuery("DELETE FROM sets_info WHERE SUMMARY_ID =" +
                summaryId + ";");
        int result = query.executeUpdate();
        return result != 0;
    }
}