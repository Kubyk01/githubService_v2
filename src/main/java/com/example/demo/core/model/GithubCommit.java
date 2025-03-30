package com.example.demo.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubCommit {

    @JsonProperty("sha")
    private final String sha;

    public GithubCommit(String sha) {
        this.sha = sha;
    }

    public String getSha() {
        return sha;
    }
}
