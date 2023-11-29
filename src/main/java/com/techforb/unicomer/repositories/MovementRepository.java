package com.techforb.unicomer.repositories;

import com.techforb.unicomer.entitites.Movement;
import com.techforb.unicomer.entitites.request.MovementRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovementRepository extends JpaRepository<Movement, Integer> {
    Optional<Movement> findTopByAccountIdOrderByMovementDateDesc(Integer accountId);

}
