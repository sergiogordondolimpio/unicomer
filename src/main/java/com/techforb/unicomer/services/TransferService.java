package com.techforb.unicomer.services;

import com.techforb.unicomer.entitites.Transfer;
import com.techforb.unicomer.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;
    public Transfer addTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }
}
