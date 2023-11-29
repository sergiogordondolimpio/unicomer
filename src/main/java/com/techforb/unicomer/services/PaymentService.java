package com.techforb.unicomer.services;

import com.techforb.unicomer.entitites.Payment;
import com.techforb.unicomer.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
