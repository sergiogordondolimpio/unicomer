package com.techforb.unicomer.repositories;

import java.util.Optional;

import com.techforb.unicomer.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username); 
}
