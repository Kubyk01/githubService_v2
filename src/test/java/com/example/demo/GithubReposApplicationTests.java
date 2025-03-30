package com.example.demo;

import com.example.demo.feature.model.GithubResponse;
import com.example.demo.feature.service.GithubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class GithubReposApplicationTests {

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GithubService githubService;

	@BeforeEach
	public void setUp() {
	}

	@Test
	public void testCompleteFlowForValidUser() throws Exception {
		String username = "octocat";

		mockMvc.perform(get("/github/{username}", username)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].repo_name").exists())
				.andExpect(jsonPath("$[0].owner_login").exists())
				.andExpect(jsonPath("$[0].branches").isNotEmpty())
				.andExpect(jsonPath("$[0].branches[0].name").exists())
				.andExpect(jsonPath("$[0].branches[0].commit.sha").exists())
				.andExpect(jsonPath("$[0].branches[0].commit.commit_details.author").exists());

		List<GithubResponse> responses = githubService.getRepositories(username);
		assert(responses.size() > 0);
		assert(responses.get(0).getRepoName() != null);
		assert(responses.get(0).getOwnerLogin() != null);
		assert(responses.get(0).getBranches() != null && !responses.get(0).getBranches().isEmpty());

		responses.get(0).getBranches().forEach(branch -> {
			assert(branch.getName() != null);
			assert(branch.getCommit() != null);
			assert(branch.getCommit().getSha() != null);
		});
	}

	@Test
	public void testGetRepositories_UserNotFound_ReturnsNotFound() throws Exception {
		String username = "nonexistentuser";

		mockMvc.perform(get("/github/{username}", username)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testGetRepositories_EmptyResponse() throws Exception {
		String username = "emptyreposuser";

		mockMvc.perform(get("/github/{username}", username)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").value("User not found: " + username));
	}

	@Test
	public void testGetRepositories_ResponseContainsValidData() throws Exception {
		String username = "validuser";

		mockMvc.perform(get("/github/{username}", username)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].repo_name").exists())
				.andExpect(jsonPath("$[0].owner_login").exists())
				.andExpect(jsonPath("$[0].branches").isNotEmpty())
				.andExpect(jsonPath("$[0].branches[0].commit.sha").exists());
	}
}
