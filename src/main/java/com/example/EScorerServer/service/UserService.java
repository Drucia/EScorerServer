package com.example.EScorerServer.service;

import com.example.EScorerServer.model.User;

public interface UserService {
    User save(User user);
    User getUserById(String userId);
}
