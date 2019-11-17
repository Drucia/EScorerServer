package com.example.EScorerServer.errors;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String id) {
        super("User not found " + id);
    }
    public UserNotFoundException(String id, int matchId) {
        super("User not found " + id + " for match id " + matchId);
    }
}
