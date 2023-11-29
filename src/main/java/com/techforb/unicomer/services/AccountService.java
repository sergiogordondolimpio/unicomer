package com.techforb.unicomer.services;

import com.techforb.unicomer.entitites.Account;
import com.techforb.unicomer.entitites.request.AccountRequest;
import com.techforb.unicomer.entitites.response.AccountResponse;
import com.techforb.unicomer.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;

    public List<AccountResponse> getAccountsByUserId(Integer id) {
        List<Account> accounts = accountRepository.findAllByUserId(id);
        return accounts.stream()
                .map(account ->
                        AccountResponse.builder()
                                .id(account.getId())
                                .accountNumber(account.getAccountNumber())
                                .balance(account.getBalance())
                                .accountType(account.getAccountType())
                                .currency(account.getCurrency())
                                .build())
                .collect(Collectors.toList());
    }

    public AccountResponse addAccount(Integer id, AccountRequest accountRequest) {

        Account account = Account.builder()
                .accountNumber(generateAccountNumber())
                .accountType(accountRequest.getAccountType())
                .user(userService.getUser(id))
                .balance(BigDecimal.ZERO)
                .currency(accountRequest.getCurrency())
                .build();

        Account accountSaved = accountRepository.save(account);

        return AccountResponse.builder()
                .id(accountSaved.getId())
                .accountNumber(accountSaved.getAccountNumber())
                .accountType(accountSaved.getAccountType())
                .balance(accountSaved.getBalance())
                .currency(accountSaved.getCurrency())
                .build();

    }

    private String generateAccountNumber() {
        String generateUUIDNo = String.format("%010d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));
        String accountNumber = generateUUIDNo.substring( generateUUIDNo.length() - 20);
        return accountNumber;
    }

    public Account getAccountById(Integer accountId) {
        return accountRepository.findById(accountId).get();
    }

    public void updateBalance(Integer accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).get();
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }
}
