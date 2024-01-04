package com.kirana.register.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionInput {
    @JsonProperty("amount")
    private double amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate timestamp;

    @JsonProperty("type")
    private String type;

    private String description;
}