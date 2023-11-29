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
@Table(name="transfer")
public class Transfer {

    @Id
    @GeneratedValue
    private Integer id;
    @Basic
    @Column(nullable = false, unique = true)
    private String transferNumber;
    @Column(nullable = false)
    private String accountNumberDestiny;
    @Column(nullable = false)
    private String accountNumberOrigin;
    @OneToOne
    @JoinColumn(name="movement_id", nullable=false)
    private Movement movement;

}
