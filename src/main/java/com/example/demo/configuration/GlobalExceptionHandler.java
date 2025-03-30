package com.example.demo.configuration;

import com.example.demo.core.exception.GithubUserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GithubUserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleGithubUserNotFoundException(GithubUserNotFoundException ex) {
        Map<String, Object> response = Map.of(
                "status", HttpStatus.NOT_FOUND.value(),
                "message", ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
