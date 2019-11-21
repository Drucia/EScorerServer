package com.example.EScorerServer.controller;

import com.example.EScorerServer.service.MatchService;
import com.example.EScorerServer.service.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MatchController.class)
class MatchRestControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MatchService matchService;
    @MockBean
    private TeamService teamService;

    @Test
    void injectedComponentsAreNotNull()
    {
        assertNotNull(mockMvc);
        assertNotNull(matchService);
        assertNotNull(teamService);
    }
}
