package com.example.EScorerServer.errors;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(int teamId) {
        super("Team not found " + teamId);
    }
}
