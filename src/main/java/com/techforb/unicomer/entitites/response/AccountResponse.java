package com.techforb.unicomer.entitites.response;

import com.techforb.unicomer.entitites.enums.AccountType;
import com.techforb.unicomer.entitites.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    Integer id;
    String accountNumber;
    AccountType accountType;
    Currency currency;
    BigDecimal balance;

}
