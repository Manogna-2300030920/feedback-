package com.example.feedback.repository;

import com.example.feedback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> { // Assuming Long id is the ID
    Optional<User> findByUsername(String username);
}



