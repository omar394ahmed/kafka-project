package com.demos.firstConsumer.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionsWithFees {

    private String national_id;
    private String name;
    private Long amount;
    private Long fees;

    public TransactionsWithFees(String national_id, String name, Long amount, Long fees) {
        this.national_id = national_id;
        this.name = name;
        this.amount = amount;
        this.fees = fees;
    }
}
