package com.techforb.unicomer.controllers;

import com.techforb.unicomer.entitites.Account;
import com.techforb.unicomer.entitites.request.AccountRequest;
import com.techforb.unicomer.entitites.response.AccountResponse;
import com.techforb.unicomer.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account/")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "accounts")
    public List<AccountResponse> getAccounts(@RequestParam("id") Integer id) {
        return accountService.getAccountsByUserId(id);
    }

    @PostMapping(value = "account")
    public ResponseEntity<AccountResponse> addAccount(@RequestParam("id") Integer id, @RequestBody AccountRequest accountRequest) {
        return ResponseEntity.ok(accountService.addAccount(id, accountRequest));
    }

}
