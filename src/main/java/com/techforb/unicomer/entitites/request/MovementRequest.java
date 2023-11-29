package com.techforb.unicomer.entitites.request;

import com.techforb.unicomer.entitites.enums.AccountMovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovementRequest {

    private AccountMovementType accountMovementType;
    private BigDecimal amount;

    private String orderNumber;
    private  String company;
    private String name;

    private String transferNumber;
    private String accountNumberDestiny;
    private String accountNumberOrigin;

}
