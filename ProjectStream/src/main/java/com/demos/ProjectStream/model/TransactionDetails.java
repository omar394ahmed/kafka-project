package com.demos.ProjectStream.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TransactionDetails extends Object {

    public TransactionDetails(String national_id, String name, Long amount) {
        this.national_id = national_id;
        this.name = name;
        this.amount = amount;
    }

    private String national_id;
    private String name;
    private Long amount;

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
