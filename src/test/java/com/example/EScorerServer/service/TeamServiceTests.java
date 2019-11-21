package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.model.User;
import com.example.EScorerServer.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class TeamServiceTests {
    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImpl teamService;

    private List<Team> team;
    private User user;

    @BeforeEach
    void initPlayerList()
    {
        MockitoAnnotations.initMocks(this);
        user = new User("testownik", "test", "nazwisko");
        team = new ArrayList<>(Arrays.asList(
                new Team("team", "dream team", user.getId()),
                new Team("team 2", "dream team 2", user.getId())
        ));
    }

    @Test
    void injectedComponentsAreNotNull()
    {
        assertNotNull(teamRepository);
        assertNotNull(teamService);
    }
}
