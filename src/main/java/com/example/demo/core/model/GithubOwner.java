package com.example.demo.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GithubOwner {

    @JsonProperty("login")
    private String login;

    public String getLogin() {
        return login;
    }
}
