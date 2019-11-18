package com.example.EScorerServer;

import com.example.EScorerServer.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.junit.Assert.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserEntityRepositoryTest {

    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private UserRepository userRepository;

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(dataSource, Matchers.notNullValue());
        assertThat(jdbcTemplate, Matchers.notNullValue());
        assertThat(entityManager, Matchers.notNullValue());
        assertThat(userRepository, Matchers.notNullValue());
    }
}
