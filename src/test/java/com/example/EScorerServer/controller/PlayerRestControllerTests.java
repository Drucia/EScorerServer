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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlayerController.class)
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

        teamResponse = new Team(-1, "team one", "one", userId);
        playersResponse = new ArrayList<>(Arrays.asList(
                new Player(-1, "Nowy 1", "Gracz", team),
                new Player(-2, "Nowy 2", "Gracz", team),
                new Player(-3, "Nowy 3", "Gracz", team),
                new Player(-4, "Nowy 4", "Gracz", team),
                new Player(-5, "Nowy 5", "Gracz", team),
                new Player(-6, "Nowy 6", "Gracz", team)
        ));
    }

    @Test
    void injectedComponentsAreNotNull()
    {
        assertNotNull(mockMvc);
        assertNotNull(objectMapper);
        assertNotNull(playerService);
        assertNotNull(teamService);
    }

    @Test
    void saveSeveralPlayersOfTeam() throws Exception {
        Player fp = players.get(0);
        Player fpr = playersResponse.get(0);
        given(teamService.getTeam(team.getId())).willReturn(Optional.of(team));
        given(playerService.saveOrUpdatePlayersOfTeam(team.getId(), playersResponse)).willReturn(players);
        mockMvc.perform(post("/players/team/{teamId}/several", team.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playersResponse)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(playersResponse.size())))
                .andExpect(jsonPath("$[0]", hasEntry("id", fp.getId())))
                .andExpect(jsonPath("$[0]", hasEntry("name", fpr.getName())))
                .andExpect(jsonPath("$[0]", hasEntry("surname", fpr.getSurname())));
    }
}
