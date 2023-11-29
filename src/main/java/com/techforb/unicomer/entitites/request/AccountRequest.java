package com.techforb.unicomer.entitites.request;

import com.techforb.unicomer.entitites.enums.AccountType;
import com.techforb.unicomer.entitites.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    AccountType accountType;

    Currency currency;

}
