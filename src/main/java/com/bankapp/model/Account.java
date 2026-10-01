package com.bankapp.model;

import java.math.BigDecimal;

public class Account {

    private int id;
    private String accountNumber;
    private String name;
    private String pin;
    private BigDecimal balance;

    public Account(
            int id,
            String accountNumber,
            String name,
            String pin,
            BigDecimal balance
    ) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getPin() {
        return pin;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}