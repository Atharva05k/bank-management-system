package com.bankapp.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {

    private final int id;
    private final String type;
    private final BigDecimal amount;
    private final String riskLevel;
    private final String riskReason;
    private final Timestamp transactionTime;

    public Transaction(
            int id,
            String type,
            BigDecimal amount,
            String riskLevel,
            String riskReason,
            Timestamp transactionTime
    ) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.riskLevel = riskLevel;
        this.riskReason = riskReason;
        this.transactionTime = transactionTime;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Timestamp getTransactionTime() {
        return transactionTime;
    }

    public String getRiskLevel(){
        return riskLevel;
    }

    public String getRiskReason(){
        return riskReason;
    }
}