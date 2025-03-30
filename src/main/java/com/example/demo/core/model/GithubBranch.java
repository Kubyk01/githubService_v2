package com.example.demo.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubBranch {

    private String name;

    @JsonProperty("commit")
    private GithubCommit commit;

    public GithubBranch(String name, GithubCommit commit) {
        this.name = name;
        this.commit = commit;
    }

    public String getName() {
        return name;
    }

    public GithubCommit getCommit() {
        return commit;
    }

    public void setCommit(GithubCommit commit) {
        this.commit = commit;
    }
}