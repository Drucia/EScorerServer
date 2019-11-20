package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserEntityRepositoryTests {

    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private UserRepository userRepository;

    @Test
    public void injectedComponentsAreNotNull()
    {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(userRepository).isNotNull();
    }

    @Test
    public void whenSavedThenFindById()
    {
        //userRepository.save(new User("testowy", "Test", "User"));
        assertThat(userRepository.findById("testowy").isPresent()).isTrue();
    }

    @Test
    public void whenSavedUpdateUser()
    {
        User user = userRepository.save(new User("testowy", "Test", "User"));
        user.setName("Testus");
        userRepository.save(user);
        assertThat(userRepository.findById("testowy").isPresent()).isTrue();
        assertThat(userRepository.findById("testowy").get().getName()).isEqualTo("Testus");
    }
}
