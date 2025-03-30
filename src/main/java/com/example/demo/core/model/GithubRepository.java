package com.example.demo.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepository {

    @JsonProperty("name")
    private String name;

    @JsonProperty("owner")
    private GithubOwner owner;

    @JsonProperty("fork")
    private boolean isFork;

    private List<GithubBranch> branches;

    public GithubRepository(String name, GithubOwner owner, boolean isFork, List<GithubBranch> branches) {
        this.name = name;
        this.owner = owner;
        this.isFork = isFork;
        this.branches = branches;
    }

    public String getName() {
        return name;
    }

    public GithubOwner getOwner() {
        return owner;
    }

    public boolean isFork() {
        return isFork;
    }

    public List<GithubBranch> getBranches() {
        return branches;
    }

    public void setBranches(List<GithubBranch> branches) {
        this.branches = branches;
    }
}
