package com.example.demo;

import com.example.demo.core.client.GithubApiFeignClient;
import com.example.demo.core.exception.GithubUserNotFoundException;
import com.example.demo.core.model.GithubRepository;
import com.example.demo.feature.model.GithubResponse;
import com.example.demo.feature.service.GithubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GithubServiceIntegrationTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private GithubService githubService;

    @Autowired
    private GithubApiFeignClient githubApiFeignClient;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetRepositories_ValidUser_ReturnsRepositories() {
        String username = "octocat";

        List<GithubResponse> responses = githubService.getRepositories(username);

        assertFalse(responses.isEmpty(), "The repository list should not be empty");

        GithubResponse firstRepo = responses.get(0);
        assertTrue(firstRepo.getRepoName().length() > 0, "The repository name should not be empty");
        assertTrue(firstRepo.getOwnerLogin().length() > 0, "The owner login should not be empty");
        assertFalse(firstRepo.getBranches().isEmpty(), "Branches should not be empty");
    }

    @Test
    public void testGetRepositories_UserNotFound_ThrowsException() {
        String username = "nonexistentuser";

        try {
            githubService.getRepositories(username);
        } catch (Exception e) {
            assertTrue(e instanceof GithubUserNotFoundException, "Expected GithubUserNotFoundException");
        }
    }
}
