package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeamEntityRepositoryTests {

    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private TeamRepository teamRepository;

    User user;
    Team team;

    @BeforeAll
    public void initUser()
    {
        user = new User("testowy", "Pan", "Test");
        team = new Team("dream", "team");
    }

    @Test
    public void injectedComponentsAreNotNull()
    {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(teamRepository).isNotNull();
    }

    @Test
    public void saveUserAndThenAddUserTeam()
    {
        team.setUserId(user.getId());
        teamRepository.save(team);
        Optional<List<Team>> result = teamRepository.getAllTeamsOfUser(user.getId());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().size()).isEqualTo(1);
    }

    @Test
    public void whenSavedUpdateUser()
    {
    }
}