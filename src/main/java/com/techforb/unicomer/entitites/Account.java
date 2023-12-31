package com.techforb.unicomer.entitites;

import com.techforb.unicomer.entitites.enums.AccountType;
import com.techforb.unicomer.entitites.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue
    private Integer id;
    @Basic
    @Column(nullable = false, unique = true)
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(nullable = false)
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

}
