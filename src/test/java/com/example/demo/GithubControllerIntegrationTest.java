package com.example.demo;

import com.example.demo.feature.service.GithubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class GithubControllerIntegrationTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {}

    @Test
    public void testGetRepositories_ValidUser_ReturnsRepositories() throws Exception {
        String username = "octocat";

        mockMvc.perform(get("/github/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].repo_name").exists())
                .andExpect(jsonPath("$[0].owner_login").exists())
                .andExpect(jsonPath("$[0].branches").isNotEmpty())
                .andExpect(jsonPath("$[0].branches[0].name").exists())
                .andExpect(jsonPath("$[0].branches[0].commit.sha").exists());
    }

    @Test
    public void testGetRepositories_UserNotFound_ReturnsNotFound() throws Exception {
        String username = "nonexistentuser";

        mockMvc.perform(get("/github/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
