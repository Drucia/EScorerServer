package com.example.EScorerServer.controller;

import com.example.EScorerServer.model.User;
import com.example.EScorerServer.service.UserService;
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

import static org.hamcrest.Matchers.hasEntry;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private User user;

    @BeforeEach
    void init()
    {
        user = new User("siallala", "nowy", "user");
    }

    @Test
    void injectedComponentsAreNotNull()
    {
        assertNotNull(mockMvc);
        assertNotNull(objectMapper);
        assertNotNull(userService);
    }

    @Test
    void addValidUser() throws Exception
    {
        given(userService.save(user)).willReturn(user);
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasEntry("id", user.getId())))
                .andExpect(jsonPath("$", hasEntry("name", user.getName())))
                .andExpect(jsonPath("$", hasEntry("surname", user.getSurname())));
    }

    @Test
    void addNotValidUser() throws Exception {
        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new User("siallala", "", "user"))))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUserInfo() throws Exception
    {
        given(userService.getUserById(user.getId())).willReturn(user);
        mockMvc.perform(get("/users/{userId}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasEntry("id", user.getId())))
                .andExpect(jsonPath("$", hasEntry("name", user.getName())))
                .andExpect(jsonPath("$", hasEntry("surname", user.getSurname())));
    }
}