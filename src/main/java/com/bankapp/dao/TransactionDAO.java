package com.bankapp.dao;

import com.bankapp.db.DatabaseConnection;
import com.bankapp.model.Account;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.bankapp.model.Transaction;
import java.util.ArrayList;
import java.util.List;
import com.bankapp.util.RiskEvaluator;

public class TransactionDAO {

    public boolean deposit(
            Account account,
            BigDecimal amount
    ) {

        String updateBalanceSql = """
                UPDATE accounts
                SET balance = balance + ?
                WHERE id = ?
                """;

        String insertTransactionSql = """
                INSERT INTO transactions
                (account_id, transaction_type, amount, risk_level, risk_reason)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection()
        ) {

            connection.setAutoCommit(false);

            try (
                    PreparedStatement updateStatement =
                            connection.prepareStatement(
                                    updateBalanceSql
                            );

                    PreparedStatement transactionStatement =
                            connection.prepareStatement(
                                    insertTransactionSql
                            )
            ) {

                updateStatement.setBigDecimal(
                        1,
                        amount
                );

                updateStatement.setInt(
                        2,
                        account.getId()
                );

                int updatedRows =
                        updateStatement.executeUpdate();


                if (updatedRows != 1) {

                    connection.rollback();

                    return false;
                }

                String riskLevel =
                        RiskEvaluator.getRiskLevel(
                                "DEPOSIT",
                                amount
                        );

                String riskReason =
                        RiskEvaluator.getRiskReason(
                                "DEPOSIT",
                                amount
                        );
            
                transactionStatement.setInt(
                        1,
                        account.getId()
                );

                transactionStatement.setString(
                        2,
                        "DEPOSIT"
                );

                transactionStatement.setBigDecimal(
                        3,
                        amount
                );

                transactionStatement.setString(
                        4,
                        riskLevel
                );

                transactionStatement.setString(
                        5,
                        riskReason
                );

                transactionStatement.executeUpdate();


                connection.commit();

                account.setBalance(
                        account.getBalance().add(amount)
                );

                return true;

            } catch (SQLException e) {

                connection.rollback();

                throw e;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Deposit failed."
            );

            e.printStackTrace();

            return false;
        }
    }

    public boolean withdraw(
                Account account,
                BigDecimal amount
        ) {

        String updateBalanceSql = """
                UPDATE accounts
                SET balance = balance - ?
                WHERE id = ?
                AND balance >= ?
                """;

        String insertTransactionSql = """
                INSERT INTO transactions
                (account_id, transaction_type, amount, risk_level, risk_reason)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection()
        ) {

                connection.setAutoCommit(false);

                try (
                        PreparedStatement updateStatement =
                                connection.prepareStatement(
                                        updateBalanceSql
                                );

                        PreparedStatement transactionStatement =
                                connection.prepareStatement(
                                        insertTransactionSql
                                )
                ) {

                // 1. Try to deduct the amount
                updateStatement.setBigDecimal(
                        1,
                        amount
                );

                updateStatement.setInt(
                        2,
                        account.getId()
                );

                updateStatement.setBigDecimal(
                        3,
                        amount
                );


                int updatedRows =
                        updateStatement.executeUpdate();


                // 2. If balance was insufficient,
                // no row will be updated
                if (updatedRows != 1) {

                        connection.rollback();

                        return false;
                }


                // 3. Calculate transaction risk
                String riskLevel =
                        RiskEvaluator.getRiskLevel(
                                "WITHDRAWAL",
                                amount
                        );

                String riskReason =
                        RiskEvaluator.getRiskReason(
                                "WITHDRAWAL",
                                amount
                        );


                // 4. Save transaction details
                transactionStatement.setInt(
                        1,
                        account.getId()
                );

                transactionStatement.setString(
                        2,
                        "WITHDRAWAL"
                );

                transactionStatement.setBigDecimal(
                        3,
                        amount
                );

                transactionStatement.setString(
                        4,
                        riskLevel
                );

                transactionStatement.setString(
                        5,
                        riskReason
                );


                transactionStatement.executeUpdate();


                // 5. Make both database changes permanent
                connection.commit();


                // 6. Update Java account object
                account.setBalance(
                        account.getBalance()
                                .subtract(amount)
                );

                return true;

                } catch (SQLException e) {

                connection.rollback();

                throw e;
                }

        } catch (SQLException e) {

                System.out.println(
                        "Withdrawal failed."
                );

                e.printStackTrace();

                return false;
        }
}

        public List<Transaction> getTransactions(
                int accountId
        ) {

        List<Transaction> transactions =
                new ArrayList<>();

        String sql = """
                SELECT id,
                        transaction_type,
                        amount,
                        risk_level,
                        risk_reason,
                        transaction_time
                FROM transactions
                WHERE account_id = ?
                ORDER BY transaction_time DESC
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

                statement.setInt(
                        1,
                        accountId
                );

                try (
                        var result =
                                statement.executeQuery()
                ) {

                while (result.next()) {

                        Transaction transaction =
                                new Transaction(
                                        result.getInt("id"),
                                        result.getString(
                                                "transaction_type"
                                        ),
                                        result.getBigDecimal(
                                                "amount"
                                        ),
                                        result.getString(
                                                "risk_level"
                                        ),
                                        result.getString(
                                                "risk_reason"
                                        ),
                                        result.getTimestamp(
                                                "transaction_time"
                                        )
                                );

                        transactions.add(
                                transaction
                        );
                }
                }

        } catch (SQLException e) {

                System.out.println(
                        "Could not load transactions."
                );

                e.printStackTrace();
        }

        return transactions;
        }

}