package com.techforb.unicomer.repositories;

import com.techforb.unicomer.entitites.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    List<Account> findAllByUserId(Integer id);
}
