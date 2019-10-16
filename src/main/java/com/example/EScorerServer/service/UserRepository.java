package com.example.EScorerServer.service;

import com.example.EScorerServer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
