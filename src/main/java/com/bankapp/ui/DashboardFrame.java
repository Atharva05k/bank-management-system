package com.bankapp.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import com.bankapp.dao.TransactionDAO;
import com.bankapp.model.Account;
import com.bankapp.model.Transaction;
import com.bankapp.util.RiskEvaluator;

public class DashboardFrame extends JFrame {

    private final Account account;
    private final TransactionDAO transactionDAO;


    public DashboardFrame(Account account) {

        this.account = account;
        transactionDAO = new TransactionDAO();


        // =========================
        // FRAME
        // =========================

        setTitle(
                "Bank Management System - Dashboard"
        );

        setSize(
                700,
                520
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);


        // =========================
        // BACKGROUND
        // =========================

        ImageIcon originalIcon =
                new ImageIcon(
                        getClass().getResource(
                                "/images/bank_background.png"
                        )
                );


        Image scaledImage =
                originalIcon
                        .getImage()
                        .getScaledInstance(
                                700,
                                520,
                                Image.SCALE_SMOOTH
                        );


        JLabel backgroundLabel =
                new JLabel(
                        new ImageIcon(
                                scaledImage
                        )
                );

        backgroundLabel.setBounds(
                0,
                0,
                700,
                520
        );

        backgroundLabel.setLayout(null);


        // =========================
        // TITLE
        // =========================

        JLabel titleLabel =
                new JLabel(
                        "BANK MANAGEMENT SYSTEM",
                        SwingConstants.CENTER
                );

        titleLabel.setBounds(
                100,
                35,
                500,
                40
        );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        27
                )
        );

        titleLabel.setForeground(
                Color.WHITE
        );


        // =========================
        // WELCOME
        // =========================

        JLabel welcomeLabel =
                new JLabel(
                        "Welcome, "
                                + account.getName(),
                        SwingConstants.CENTER
                );

        welcomeLabel.setBounds(
                100,
                100,
                500,
                32
        );

        welcomeLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        21
                )
        );

        welcomeLabel.setForeground(
                Color.WHITE
        );


        // =========================
        // ACCOUNT NUMBER
        // =========================

        JLabel accountLabel =
                new JLabel(
                        "Account Number: "
                                + account.getAccountNumber(),
                        SwingConstants.CENTER
                );

        accountLabel.setBounds(
                100,
                135,
                500,
                25
        );

        accountLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        accountLabel.setForeground(
                new Color(
                        220,
                        230,
                        245
                )
        );


        // =========================
        // TRANSACTION LABEL
        // =========================

        JLabel transactionLabel =
                new JLabel(
                        "Please Select Your Transaction",
                        SwingConstants.CENTER
                );

        transactionLabel.setBounds(
                100,
                185,
                500,
                30
        );

        transactionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        transactionLabel.setForeground(
                Color.WHITE
        );


        // =========================
        // BUTTONS
        // =========================

        JButton depositButton =
                new JButton("DEPOSIT");

        depositButton.setBounds(
                165,
                245,
                165,
                45
        );


        JButton withdrawButton =
                new JButton("WITHDRAW");

        withdrawButton.setBounds(
                370,
                245,
                165,
                45
        );


        JButton balanceButton =
                new JButton("BALANCE");

        balanceButton.setBounds(
                165,
                315,
                165,
                45
        );


        JButton historyButton =
                new JButton("HISTORY");

        historyButton.setBounds(
                370,
                315,
                165,
                45
        );


        JButton logoutButton =
                new JButton("LOGOUT");

        logoutButton.setBounds(
                270,
                395,
                160,
                42
        );


        // =========================
        // BUTTON STYLE
        // =========================

        JButton[] mainButtons = {
                depositButton,
                withdrawButton,
                balanceButton,
                historyButton
        };


        for (JButton button : mainButtons) {

            button.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            14
                    )
            );

            button.setBackground(
                    new Color(
                            23,
                            107,
                            239
                    )
            );

            button.setForeground(
                    Color.WHITE
            );

            button.setFocusPainted(false);
        }


        logoutButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        logoutButton.setBackground(
                new Color(
                        220,
                        38,
                        38
                )
        );

        logoutButton.setForeground(
                Color.WHITE
        );

        logoutButton.setFocusPainted(false);


        // =========================
        // ADD COMPONENTS
        // =========================

        backgroundLabel.add(titleLabel);
        backgroundLabel.add(welcomeLabel);
        backgroundLabel.add(accountLabel);
        backgroundLabel.add(transactionLabel);

        backgroundLabel.add(depositButton);
        backgroundLabel.add(withdrawButton);
        backgroundLabel.add(balanceButton);
        backgroundLabel.add(historyButton);
        backgroundLabel.add(logoutButton);

        setContentPane(backgroundLabel);


        // =========================
        // EVENTS
        // =========================

        depositButton.addActionListener(
                e -> deposit()
        );

        withdrawButton.addActionListener(
                e -> withdraw()
        );

        balanceButton.addActionListener(
                e -> showBalance()
        );

        historyButton.addActionListener(
                e -> showTransactionHistory()
        );

        logoutButton.addActionListener(
                e -> logout()
        );
    }


    // =========================
    // BALANCE
    // =========================

    private void showBalance() {

        JOptionPane.showMessageDialog(
                this,
                "Current Balance: ₹"
                        + account.getBalance(),
                "Balance Inquiry",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // =========================
    // LOGOUT
    // =========================

    private void logout() {

        LoginFrame loginFrame =
                new LoginFrame();

        loginFrame.setVisible(true);

        dispose();
    }


    // =========================
    // DEPOSIT
    // =========================

    private void deposit() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter deposit amount:"
                );


        if (input == null) {
            return;
        }


        BigDecimal amount;


        try {

            amount =
                    new BigDecimal(
                            input.trim()
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid amount.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        if (
                amount.compareTo(
                        BigDecimal.ZERO
                ) <= 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Deposit amount must be greater than zero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        boolean successful =
                transactionDAO.deposit(
                        account,
                        amount
                );


        if (successful) {

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


            JOptionPane.showMessageDialog(
                    this,
                    "Deposit successful!\n"
                            + "New Balance: ₹"
                            + account.getBalance()
            );


            if (!riskLevel.equals("LOW")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Transaction Risk: "
                                + riskLevel
                                + "\nReason: "
                                + riskReason,
                        "Suspicious Transaction Alert",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Deposit failed.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================
    // WITHDRAW
    // =========================

    private void withdraw() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter withdrawal amount:"
                );


        if (input == null) {
            return;
        }


        BigDecimal amount;


        try {

            amount =
                    new BigDecimal(
                            input.trim()
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid amount.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        if (
                amount.compareTo(
                        BigDecimal.ZERO
                ) <= 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Withdrawal amount must be greater than zero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        boolean successful =
                transactionDAO.withdraw(
                        account,
                        amount
                );


        if (successful) {

            JOptionPane.showMessageDialog(
                    this,
                    "Withdrawal successful!\n"
                            + "New Balance: ₹"
                            + account.getBalance()
            );


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


            if (!riskLevel.equals("LOW")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Transaction Risk: "
                                + riskLevel
                                + "\nReason: "
                                + riskReason,
                        "Suspicious Transaction Alert",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Insufficient balance or withdrawal failed.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================
    // TRANSACTION HISTORY PAGE
    // =========================

    private void showTransactionHistory() {

        List<Transaction> transactions =
                transactionDAO.getTransactions(
                        account.getId()
                );


        if (transactions.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No transactions found."
            );

            return;
        }


        // =========================
        // HISTORY WINDOW
        // =========================

        JDialog historyDialog =
                new JDialog(
                        this,
                        "Transaction History",
                        true
                );


        historyDialog.setSize(
                760,
                470
        );

        historyDialog.setLocationRelativeTo(
                this
        );

        historyDialog.setResizable(false);


        // =========================
        // SAME BACKGROUND IMAGE
        // =========================

        ImageIcon originalIcon =
                new ImageIcon(
                        getClass().getResource(
                                "/images/bank_background.png"
                        )
                );


        Image scaledImage =
                originalIcon
                        .getImage()
                        .getScaledInstance(
                                760,
                                470,
                                Image.SCALE_SMOOTH
                        );


        JLabel background =
                new JLabel(
                        new ImageIcon(
                                scaledImage
                        )
                );


        background.setLayout(null);


        // =========================
        // TITLE
        // =========================

        JLabel title =
                new JLabel(
                        "TRANSACTION HISTORY",
                        SwingConstants.CENTER
                );


        title.setBounds(
                130,
                25,
                500,
                35
        );


        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );


        title.setForeground(
                Color.WHITE
        );


        // ACCOUNT INFO

        JLabel accountInfo =
                new JLabel(
                        "Account Number: "
                                + account.getAccountNumber(),
                        SwingConstants.CENTER
                );


        accountInfo.setBounds(
                150,
                65,
                460,
                25
        );


        accountInfo.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );


        accountInfo.setForeground(
                Color.WHITE
        );


        // =========================
        // TABLE DATA
        // =========================

        String[] columns = {
                "Type",
                "Amount",
                "Risk",
                "Date & Time"
        };


        Object[][] data =
                new Object[
                        transactions.size()
                ][4];


        for (
                int i = 0;
                i < transactions.size();
                i++
        ) {

            Transaction transaction =
                    transactions.get(i);


            data[i][0] =
                    transaction.getType();

            data[i][1] =
                    "₹"
                            + transaction.getAmount();

            data[i][2] =
                    transaction.getRiskLevel();

            data[i][3] =
                    transaction.getTransactionTime();
        }


        // =========================
        // TABLE
        // =========================

        JTable table =
                new JTable(
                        data,
                        columns
                );


        table.setEnabled(false);

        table.setRowHeight(30);

        table.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );


        table.setBackground(
                Color.WHITE
        );


        table.setForeground(
                new Color(
                        30,
                        41,
                        59
                )
        );


        // =========================
        // TABLE HEADER
        // =========================

        JTableHeader header =
                table.getTableHeader();


        header.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );


        header.setBackground(
                new Color(
                        13,
                        46,
                        107
                )
        );


        header.setForeground(
                Color.WHITE
        );


        header.setPreferredSize(
                new Dimension(
                        0,
                        35
                )
        );


        // CENTER VALUES

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();


        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        for (
                int i = 0;
                i < table.getColumnCount();
                i++
        ) {

            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            centerRenderer
                    );
        }


        // COLUMN WIDTHS

        table.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(120);

        table.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(120);

        table.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(80);

        table.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(220);


        // =========================
        // SCROLL PANE
        // =========================

        JScrollPane scrollPane =
                new JScrollPane(
                        table
                );


        scrollPane.setBounds(
                45,
                115,
                670,
                240
        );


        // =========================
        // CLOSE BUTTON
        // =========================

        JButton closeButton =
                new JButton(
                        "CLOSE"
                );


        closeButton.setBounds(
                290,
                375,
                180,
                40
        );


        closeButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );


        closeButton.setBackground(
                new Color(
                        23,
                        107,
                        239
                )
        );


        closeButton.setForeground(
                Color.WHITE
        );


        closeButton.setFocusPainted(
                false
        );


        closeButton.addActionListener(
                e -> historyDialog.dispose()
        );


        // =========================
        // ADD TO BACKGROUND
        // =========================

        background.add(title);
        background.add(accountInfo);
        background.add(scrollPane);
        background.add(closeButton);


        historyDialog.setContentPane(
                background
        );


        historyDialog.setVisible(
                true
        );
    }
}