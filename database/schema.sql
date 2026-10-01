CREATE DATABASE IF NOT EXISTS bank_management;

USE bank_management;


CREATE TABLE IF NOT EXISTS accounts (

    id INT PRIMARY KEY AUTO_INCREMENT,

    account_number VARCHAR(20)
        UNIQUE
        NOT NULL,

    name VARCHAR(100)
        NOT NULL,

    pin VARCHAR(10)
        NOT NULL,

    balance DECIMAL(12,2)
        NOT NULL
        DEFAULT 0.00,

    failed_attempts INT
        NOT NULL
        DEFAULT 0,

    locked_until TIMESTAMP
        NULL,

    created_at TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS transactions (

    id INT PRIMARY KEY AUTO_INCREMENT,

    account_id INT
        NOT NULL,

    transaction_type VARCHAR(20)
        NOT NULL,

    amount DECIMAL(12,2)
        NOT NULL,

    risk_level VARCHAR(10)
        DEFAULT 'LOW',

    risk_reason VARCHAR(255)
        NULL,

    transaction_time TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (account_id)
        REFERENCES accounts(id)
);