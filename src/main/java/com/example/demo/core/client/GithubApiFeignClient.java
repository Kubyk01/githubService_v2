package com.example.demo.core.client;

import com.example.demo.core.model.GithubBranch;
import com.example.demo.core.model.GithubRepository;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "githubApiClient", url = "${github-api.url}")
public interface GithubApiFeignClient {

    @GetMapping("/users/{username}/repos")
    List<GithubRepository> getUserRepositories(@PathVariable("username") String username);

    @GetMapping("/repos/{owner}/{repo}/branches")
    List<GithubBranch> getRepositoryBranches(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
}
