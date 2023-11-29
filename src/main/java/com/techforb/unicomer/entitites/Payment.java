package com.techforb.unicomer.entitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue
    private Integer id;
    @Basic
    @Column(nullable = false, unique = true)
    private String orderNumber;
    @Column(nullable = false)
    private String company;
    @Column(nullable = false)
    private String name;
    @OneToOne
    @JoinColumn(name="movement_id", nullable=false)
    private Movement movement;

}
