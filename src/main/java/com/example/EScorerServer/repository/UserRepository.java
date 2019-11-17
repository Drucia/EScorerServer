package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>, CustomUserRepository {
}
