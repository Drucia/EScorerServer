package com.example.EScorerServer;

import com.example.EScorerServer.controller.*;
import com.example.EScorerServer.model.User;
import com.example.EScorerServer.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {PlayerController.class, TeamController.class, MatchController.class, SummaryController.class,
        SetInfoController.class, UserController.class})
public class UserRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PlayerController playerController;

    @MockBean
    private TeamController teamController;

    @MockBean
    private MatchController matchController;

    @MockBean
    private SummaryController summaryController;

    @MockBean
    private SetInfoController setInfoController;

    @Test
    public void contextLoads() {
        assertThat(mockMvc).isNotNull();
        assertThat(objectMapper).isNotNull();
        assertThat(userRepository).isNotNull();
        assertThat(playerController).isNotNull();
        assertThat(teamController).isNotNull();
        assertThat(matchController).isNotNull();
        assertThat(summaryController).isNotNull();
        assertThat(setInfoController).isNotNull();
    }

    @Test
    public void whenValidInput_thenReturns200() throws Exception {
        mockMvc.perform(post("/users")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
}