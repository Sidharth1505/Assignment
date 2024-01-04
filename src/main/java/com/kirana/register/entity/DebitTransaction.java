package com.kirana.register.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "debit_transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DebitTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public DebitTransaction(double amount, String description, Transaction transaction) {
        this.amount = amount;
        this.description = description;
        this.transaction = transaction;
    }
}
