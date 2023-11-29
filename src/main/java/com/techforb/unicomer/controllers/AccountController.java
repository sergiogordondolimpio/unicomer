package com.techforb.unicomer.controllers;

import com.techforb.unicomer.entitites.request.AccountRequest;
import com.techforb.unicomer.handler.ResponseHandler;
import com.techforb.unicomer.services.AccountService;
import com.techforb.unicomer.validation.AccountValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/account/")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountValidation accountValidation;

    public static final String MISSING_PARAM = "Falta id de la cuenta o algun parametro del body";
    public static final String WITHOUT_ACCOUNT = "El usuario no tiene cuenta";

    @GetMapping(value = "accounts")
    public ResponseEntity<Object> getAccounts(@RequestParam("id") Optional<Integer> id) {
        if (!id.isPresent()){
            return ResponseHandler.generateResponse(MISSING_PARAM, HttpStatus.BAD_REQUEST, null);
        }
        return ResponseHandler.generateResponse(null, HttpStatus.OK, accountService.getAccountsByUserId(id.get()));
    }

    @PostMapping(value = "account")
    public ResponseEntity<Object> addAccount(@RequestParam("id") Optional<Integer> id, @RequestBody AccountRequest accountRequest) {
        if (accountValidation.isAccountInvalid(id, accountRequest)){
            return ResponseHandler.generateResponse(MISSING_PARAM, HttpStatus.BAD_REQUEST, null);
        }
        if (accountValidation.notHaveAccount(id.get())) {
            return ResponseHandler.generateResponse(WITHOUT_ACCOUNT, HttpStatus.NOT_FOUND, null);
        }
        return ResponseEntity.ok(accountService.addAccount(id.get(), accountRequest));
    }

}
