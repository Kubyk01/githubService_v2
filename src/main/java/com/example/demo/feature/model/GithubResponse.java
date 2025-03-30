package com.example.demo.feature.model;

import com.example.demo.core.model.GithubBranch;
import com.example.demo.core.model.GithubRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GithubResponse {

    @JsonProperty("repo_name")
    private String repoName;
    @JsonProperty("owner_login")
    private String ownerLogin;
    @JsonProperty("branches")
    private List<GithubBranch> branches;

    public GithubResponse(String repoName, String ownerLogin, List<GithubBranch> branches) {
        this.repoName = repoName;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public void setBranches(List<GithubBranch> branches) {
        this.branches = branches;
    }

    public String getRepoName() {
        return repoName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public List<GithubBranch> getBranches() {
        return branches;
    }
}
