package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Player;
import com.example.EScorerServer.model.Team;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class CustomUserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<List<Team>> getAllTeamsOfUser(String userId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM teams WHERE USER_ID LIKE '" +
                userId + "';", Team.class);
        List<Team> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result);
    }

    @Override
    public Optional<Iterable<Player>> getAllPlayersOfUserTeams(String userId) {
        Query query = entityManager.createNativeQuery("select * from players " +
                "where TEAM_ID in " +
                "(select t.ID from users u join teams t on u.ID = t.USER_ID " +
                "where u.ID LIKE '" +  userId +
                "');", Player.class);
        List<Player> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result);
    }

    @Override
    public Optional<Iterable<Player>> getAllPlayersOfUserSpecificTeam(String userId, int teamId) {
        Query query = entityManager.createNativeQuery("select * from players " +
                "where TEAM_ID in " +
                "(select t.ID from users u join teams t on u.ID = t.USER_ID " +
                "where u.ID LIKE '" +  userId +
                "') and TEAM_ID = " + teamId + ";", Player.class);
        List<Player> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result);
    }

    @Override
    public boolean save(Team team) {
        Query query = entityManager.createNativeQuery("update teams t " +
                "set t.NAME = " + "'" + team.getName() +"', " +
                "t.SHORT_NAME = " + "'" + team.getShortName() + "' " +
                "where t.ID = " + team.getId() + "; ");
        return query.executeUpdate() == 1;
    }

}
