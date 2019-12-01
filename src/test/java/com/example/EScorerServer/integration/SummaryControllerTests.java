package com.example.EScorerServer.integration;

import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.repository.SummaryRepository;
import com.example.EScorerServer.response.MatchResponse;
import com.example.EScorerServer.response.SetInfoResponse;
import com.example.EScorerServer.response.SummaryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SummaryControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SummaryRepository summaryRepository;

    private List<SummaryResponse> summaries;
    private List<MatchResponse> matchResponses;
    private String userId;
    private HashMap<Integer, SetInfoResponse> sets3;
    private HashMap<Integer, SetInfoResponse> sets4;
    private HashMap<Integer, SetInfoResponse> sets5;

    @BeforeEach
    void init() {
        userId = "user";
        sets3 = new HashMap<Integer, SetInfoResponse>() {{
            put(1, new SetInfoResponse(2, 6, 2, 1, 25, 10, 1, 20));
            put(2, new SetInfoResponse(2, 6, 2, 1, 25, 10, 2, 20));
            put(3, new SetInfoResponse(2, 6, 2, 1, 25, 10, 3, 20));
        }};
        sets4 = new HashMap<Integer, SetInfoResponse>() {{
            put(1, new SetInfoResponse(2, 6, 2, 1, 25, 10, 1, 20));
            put(2, new SetInfoResponse(2, 6, 2, 1, 25, 10, 2, 20));
            put(3, new SetInfoResponse(2, 6, 2, 1, 23, 25, 3, 23));
            put(4, new SetInfoResponse(2, 6, 2, 1, 25, 10, 4, 20));
        }};
        sets5 = new HashMap<Integer, SetInfoResponse>() {{
            put(1, new SetInfoResponse(2, 6, 2, 1, 25, 10, 1, 20));
            put(2, new SetInfoResponse(2, 6, 2, 1, 25, 10, 2, 20));
            put(3, new SetInfoResponse(2, 6, 2, 1, 23, 25, 3, 20));
            put(4, new SetInfoResponse(2, 6, 2, 1, 21, 25, 4, 20));
            put(5, new SetInfoResponse(2, 6, 2, 1, 15, 10, 5, 20));
        }};
        List<Team> teams = new ArrayList<>(Arrays.asList(
                new Team(-1, "team 1", "team"),
                new Team(-2, "team 2", "team"),
                new Team(-3, "team 3", "team"),
                new Team(-4, "team 4", "team")
        ));
        matchResponses = new ArrayList<>(Arrays.asList(
                new MatchResponse(teams.get(0), teams.get(1), "", userId, "21/11/2019 10:00"),
                new MatchResponse(teams.get(1), teams.get(2), "", userId, "21/11/2019 12:00"),
                new MatchResponse(teams.get(2), teams.get(3), "", userId, "21/11/2019 14:00"),
                new MatchResponse(teams.get(3), teams.get(0), "", userId, "21/11/2019 16:00")
        ));
        summaries = new ArrayList<>(Arrays.asList(
                new SummaryResponse("21/11/2019 10:00", matchResponses.get(0), teams.get(0), 3, 2, sets5),
                new SummaryResponse("21/11/2019 12:00", matchResponses.get(1), teams.get(1), 3, 0, sets3),
                new SummaryResponse("21/11/2019 14:00", matchResponses.get(2), teams.get(2), 3, 1, sets4),
                new SummaryResponse("21/11/2019 16:00", matchResponses.get(3), teams.get(3), 3, 0, sets3)
        ));
    }

    @Test
    void injectedComponentsAreNotNull() {
        assertNotNull(mockMvc);
        assertNotNull(objectMapper);
        assertNotNull(summaryRepository);
    }

    @Test
    void saveSummaryOfUserMatch() throws Exception {
        SummaryResponse sr = summaries.get(0);
        mockMvc.perform(post("/summaries/user/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sr)))
                .andExpect(status().isOk());
    }
}