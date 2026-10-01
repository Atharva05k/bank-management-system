package com.bankapp.dao;

import com.bankapp.db.DatabaseConnection;
import com.bankapp.model.Account;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    public boolean createAccount(Account account) {

        String sql = """
                INSERT INTO accounts
                (account_number, name, pin, balance)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    account.getAccountNumber()
            );

            statement.setString(
                    2,
                    account.getName()
            );

            statement.setString(
                    3,
                    account.getPin()
            );

            statement.setBigDecimal(
                    4,
                    account.getBalance()
            );

            int rowsAffected =
                    statement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Could not create account."
            );

            e.printStackTrace();

            return false;
        }
    }


    public Account findByAccountNumber(
            String accountNumber
    ) {

        String sql = """
                SELECT *
                FROM accounts
                WHERE account_number = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    accountNumber
            );

            try (
                    ResultSet result =
                            statement.executeQuery()
            ) {

                if (result.next()) {

                    int id =
                            result.getInt("id");

                    String name =
                            result.getString("name");

                    String pin =
                            result.getString("pin");

                    BigDecimal balance =
                            result.getBigDecimal(
                                    "balance"
                            );

                    return new Account(
                            id,
                            accountNumber,
                            name,
                            pin,
                            balance
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Could not find account."
            );

            e.printStackTrace();
        }

        return null;
    }

    public boolean isAccountLocked(
                int accountId
        ) {

        String resetExpiredLockSql = """
                UPDATE accounts
                SET failed_attempts = 0,
                        locked_until = NULL
                WHERE id = ?
                AND locked_until IS NOT NULL
                AND locked_until <= NOW()
                """;

        String checkLockSql = """
                SELECT locked_until > NOW() AS is_locked
                FROM accounts
                WHERE id = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection()
        ) {

                try (
                        PreparedStatement resetStatement =
                                connection.prepareStatement(
                                        resetExpiredLockSql
                                )
                ) {

                resetStatement.setInt(
                        1,
                        accountId
                );

                resetStatement.executeUpdate();
                }


                try (
                        PreparedStatement checkStatement =
                                connection.prepareStatement(
                                        checkLockSql
                                )
                ) {

                checkStatement.setInt(
                        1,
                        accountId
                );

                try (
                        var result =
                                checkStatement.executeQuery()
                ) {

                        if (result.next()) {
                        return result.getBoolean(
                                "is_locked"
                        );
                        }
                }
                }

        } catch (SQLException e) {

                System.out.println(
                        "Could not check account lock."
                );

                e.printStackTrace();
        }

        return false;
        }

        public void recordFailedLogin(
                int accountId
        ) {

        String sql = """
                UPDATE accounts
                SET failed_attempts =
                        failed_attempts + 1,

                        locked_until =
                        CASE
                                WHEN failed_attempts + 1 >= 3
                                THEN DATE_ADD(
                                        NOW(),
                                        INTERVAL 5 MINUTE
                                )
                                ELSE locked_until
                        END

                WHERE id = ?
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

                statement.executeUpdate();

        } catch (SQLException e) {

                System.out.println(
                        "Could not record failed login."
                );

                e.printStackTrace();
        }
        }

        public void resetFailedAttempts(
                int accountId
        ) {

        String sql = """
                UPDATE accounts
                SET failed_attempts = 0,
                        locked_until = NULL
                WHERE id = ?
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

                statement.executeUpdate();

        } catch (SQLException e) {

                System.out.println(
                        "Could not reset failed attempts."
                );

                e.printStackTrace();
        }
        }
}