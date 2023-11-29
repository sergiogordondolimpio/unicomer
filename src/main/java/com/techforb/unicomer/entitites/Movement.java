package com.techforb.unicomer.entitites;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techforb.unicomer.entitites.enums.AccountMovementType;
import jakarta.persistence.*;
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
@Entity
@Table(name="movement")
public class Movement {

    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated(EnumType.STRING)
    private AccountMovementType accountMovementType;
    @Column(nullable = false)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDate movementDate;
    @Column(nullable = false)
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

}
