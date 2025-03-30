package com.example.demo.feature.controller;

import com.example.demo.feature.model.GithubResponse;
import com.example.demo.feature.service.GithubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/github/")
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<GithubResponse>> getRepositories(@PathVariable String username) {
        return ResponseEntity.ok(githubService.getRepositories(username));
    }
}
