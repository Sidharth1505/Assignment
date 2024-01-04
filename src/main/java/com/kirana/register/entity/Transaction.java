package com.kirana.register.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false, name = "date")
    private LocalDate timestamp;

    @Column(nullable = false)
    private String type;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private DebitTransaction debitTransaction;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private CreditTransaction creditTransaction;

    public Transaction(double amount, String currency, LocalDate timestamp,
                       String type, DebitTransaction debitTransaction, CreditTransaction creditTransaction) {
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.type = type;
        this.debitTransaction = debitTransaction;
        this.creditTransaction = creditTransaction;
    }
}
