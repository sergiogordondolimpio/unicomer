package com.techforb.unicomer.services;

import com.techforb.unicomer.entitites.Movement;
import com.techforb.unicomer.entitites.Payment;
import com.techforb.unicomer.entitites.Transfer;
import com.techforb.unicomer.entitites.request.MovementRequest;
import com.techforb.unicomer.entitites.response.MovementResponse;
import com.techforb.unicomer.entitites.response.PaymentResponse;
import com.techforb.unicomer.entitites.response.TransferResponse;
import com.techforb.unicomer.handler.ResponseHandler;
import com.techforb.unicomer.repositories.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MovementService {

    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private  AccountService accountService;
    @Autowired
    private TransferService transferService;
    @Autowired
    private PaymentService paymentService;

    public static final String MISSING_PARAM = "Falta algún parámetro";

    public ResponseEntity<Movement> getMovement(Integer accountId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(movementRepository.findTopByAccountIdOrderByMovementDateDesc(accountId).get());
    }

    public ResponseEntity<Object> addMovement(Integer accountId, MovementRequest movementRequest) {
        TransferResponse transfer = new TransferResponse();
        PaymentResponse payment = new PaymentResponse();

        if (invalidated(movementRequest)) {
            return ResponseHandler.generateResponse(MISSING_PARAM, HttpStatus.BAD_REQUEST, null);
        }

        accountService.updateBalance(accountId, movementRequest.getAmount());

        Movement movement = saveMovement(accountId, movementRequest);

        if (movementRequest.getAccountNumberDestiny() != null) {
            transfer = saveTransfer(movement, movementRequest);
        } else
            transfer = null;

        if (movementRequest.getName() != null) {
            payment = savePayment(movement, movementRequest);
        } else {
            payment = null;
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(MovementResponse.builder()
                .id(movement.getId())
                .accountMovementType(movement.getAccountMovementType())
                .amount(movement.getAmount())
                .movementDate(movement.getMovementDate())
                .payment(payment)
                .transfer(transfer)
                .build());
    }

    private boolean invalidated(MovementRequest movementRequest) {
        return true;
    }

    private PaymentResponse savePayment(Movement movement, MovementRequest movementRequest) {
        Payment payment =  paymentService.addPayment(Payment.builder()
                        .name(movementRequest.getName())
                        .company(movementRequest.getCompany())
                        .orderNumber(movementRequest.getOrderNumber())
                        .movement(movement)
                        .build());
        return PaymentResponse.builder()
                .id(payment.getId())
                .company(payment.getCompany())
                .orderNumber(payment.getOrderNumber())
                .name(payment.getName())
                .build();
    }

    private TransferResponse saveTransfer(Movement movement, MovementRequest movementRequest) {
        Transfer transfer = transferService.addTransfer(Transfer.builder()
                .transferNumber(movementRequest.getTransferNumber())
                .accountNumberDestiny(movementRequest.getAccountNumberDestiny())
                .accountNumberOrigin(movementRequest.getAccountNumberOrigin())
                .movement(movement)
                .build());
        return TransferResponse.builder()
                .transferNumber(transfer.getTransferNumber())
                .id(transfer.getId())
                .accountNumberDestiny(transfer.getAccountNumberDestiny())
                .accountNumberOrigin(transfer.getAccountNumberOrigin())
                .build();
    }

    private Movement saveMovement(Integer accountId, MovementRequest movementRequest) {
        Movement movement = Movement.builder()
                .movementDate(LocalDate.now())
                .accountMovementType(movementRequest.getAccountMovementType())
                .amount(movementRequest.getAmount())
                .account(accountService.getAccountById(accountId))
                .build();
        
        return movementRepository.save(movement);
    }
}
