package com.example.EScorerServer.errors;

public class MatchNotFoundException extends RuntimeException {
    public MatchNotFoundException(int id) {
        super("Match not found " + id);
    }
}
