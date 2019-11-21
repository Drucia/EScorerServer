package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserEntityRepositoryTests {

    @Autowired private UserRepository userRepository;

    @Test
    public void injectedComponentsAreNotNull()
    {
        assertThat(userRepository).isNotNull();
    }

    @Test
    public void whenSavedThenFindById()
    {
        userRepository.save(new User("testowy", "Test", "User"));
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
