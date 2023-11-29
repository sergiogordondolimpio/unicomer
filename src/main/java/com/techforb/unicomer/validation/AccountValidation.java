package com.techforb.unicomer.validation;

import com.techforb.unicomer.entitites.request.AccountRequest;
import com.techforb.unicomer.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountValidation {

    @Autowired
    private AccountService accountService;

    public boolean isAccountInvalid(Optional<Integer> id, AccountRequest accountRequest) {
        return !id.isPresent() ||
                accountRequest.getAccountType() == null ||
                accountRequest.getCurrency() == null;
    }

    public boolean notHaveAccount(Integer id) {
        return accountService.getAccountById(id) == null;
    }
}
