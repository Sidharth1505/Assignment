package com.kirana.register;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {
    private double amount;
    private String currency;
    private LocalDate timestamp;
    private String type;
    private String creditDescription;
    private String debitDescription;

    public TransactionDTO(double amount, String currency, LocalDate timestamp, String type,
                          String creditDescription, String debitDescription) {
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.type = type;
        this.creditDescription = creditDescription;
        this.debitDescription = debitDescription;
    }
}
