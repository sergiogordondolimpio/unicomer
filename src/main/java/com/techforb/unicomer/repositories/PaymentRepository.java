package com.techforb.unicomer.repositories;

import com.techforb.unicomer.entitites.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
