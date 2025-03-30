package com.example.demo.feature.service;

import com.example.demo.core.client.GithubApiFeignClient;
import com.example.demo.core.exception.GithubUserNotFoundException;
import com.example.demo.core.model.GithubBranch;
import com.example.demo.core.model.GithubRepository;
import com.example.demo.feature.model.GithubResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubService {

    private final GithubApiFeignClient githubApiFeignClient;

    @Autowired
    public GithubService(GithubApiFeignClient githubApiFeignClient) {
        this.githubApiFeignClient = githubApiFeignClient;
    }

    public List<GithubResponse> getRepositories(String username) {
        try {
            List<GithubRepository> repositories = githubApiFeignClient.getUserRepositories(username);

            if (repositories.isEmpty()) {
                throw new GithubUserNotFoundException("User not found: " + username);
            }

            return repositories.stream()
                    .filter(repo -> !repo.isFork())
                    .map(repo -> {
                        List<GithubBranch> branches = githubApiFeignClient.getRepositoryBranches(repo.getOwner().getLogin(), repo.getName());
                        return new GithubResponse(repo.getName(), repo.getOwner().getLogin(), branches);
                    })
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new GithubUserNotFoundException("User not found: " + username);
        }
    }
}
