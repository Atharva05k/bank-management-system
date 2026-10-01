package com.bankapp.util;

import java.math.BigDecimal;

public class RiskEvaluator {

    public static String getRiskLevel(
            String transactionType,
            BigDecimal amount
    ) {

        if (
                transactionType.equals("WITHDRAWAL")
                && amount.compareTo(
                        new BigDecimal("50000")
                ) >= 0
        ) {
            return "HIGH";
        }

        if (
                amount.compareTo(
                        new BigDecimal("25000")
                ) >= 0
        ) {
            return "MEDIUM";
        }

        return "LOW";
    }


    public static String getRiskReason(
            String transactionType,
            BigDecimal amount
    ) {

        if (
                transactionType.equals("WITHDRAWAL")
                && amount.compareTo(
                        new BigDecimal("50000")
                ) >= 0
        ) {
            return "Large withdrawal amount";
        }

        if (
                amount.compareTo(
                        new BigDecimal("25000")
                ) >= 0
        ) {
            return "Large transaction amount";
        }

        return null;
    }
}