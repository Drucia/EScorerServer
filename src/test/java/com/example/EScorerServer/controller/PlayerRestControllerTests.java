package com.example.EScorerServer.controller;

import com.example.EScorerServer.model.Player;
import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.model.User;
import com.example.EScorerServer.service.PlayerService;
import com.example.EScorerServer.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SummaryController.class)
class PlayerRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private TeamService teamService;

    private List<Player> playersResponse;
    private Team teamResponse;
    private List<Player> players;
    private Team team;
    private String userId;

    @BeforeEach
    void init()
    {
        userId = "user";
        team = new Team(1, "team one", "one", userId);
        players = new ArrayList<>(Arrays.asList(
                new Player(1, "Nowy 1", "Gracz", team),
                new Player(2, "Nowy 2", "Gracz", team),
                new Player(3, "Nowy 3", "Gracz", team),
                new Player(4, "Nowy 4", "Gracz", team),
                new Player(5, "Nowy 5", "Gracz", team),
                new Player(6, "Nowy 6", "Gracz", team)
        ));

        team = new Team(-1, "team one", "one", userId);
        players = new ArrayList<>(Arrays.asList(
                new Player(-1, "Nowy 1", "Gracz", team),
                new Player(-2, "Nowy 2", "Gracz", team),
                new Player(-3, "Nowy 3", "Gracz", team),
                new Player(-4, "Nowy 4", "Gracz", team),
                new Player(-5, "Nowy 5", "Gracz", team),
                new Player(-6, "Nowy 6", "Gracz", team)
        ));
    }

    @Test
    void injectedComponentsAreNotNull() {
        assertNotNull(mockMvc);
        assertNotNull(objectMapper);
        assertNotNull(playerService);
        assertNotNull(teamService);
    }
}
