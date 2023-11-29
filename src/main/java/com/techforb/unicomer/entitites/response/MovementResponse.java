package com.techforb.unicomer.entitites.response;

import com.techforb.unicomer.entitites.enums.AccountMovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovementResponse {

    private Integer id;
    private AccountMovementType accountMovementType;
    private LocalDate movementDate;
    private BigDecimal amount;

    private TransferResponse transfer;
    private PaymentResponse payment;

}
