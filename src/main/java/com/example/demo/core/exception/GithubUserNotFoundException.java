package com.example.demo.core.exception;

public class GithubUserNotFoundException extends RuntimeException {

    public GithubUserNotFoundException(String message) {
        super(message);
    }
}
