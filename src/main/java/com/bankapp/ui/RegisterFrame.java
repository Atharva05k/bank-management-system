package com.bankapp.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.math.BigDecimal;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.bankapp.dao.AccountDAO;
import com.bankapp.model.Account;

public class RegisterFrame extends JFrame {

    private final JTextField nameField;
    private final JTextField accountNumberField;
    private final JPasswordField pinField;
    private final JTextField balanceField;

    private final AccountDAO accountDAO;


    public RegisterFrame() {

        accountDAO =
                new AccountDAO();


        // =========================
        // FRAME
        // =========================

        setTitle(
                "Bank Management System - Create Account"
        );

        setSize(
                600,
                650
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);


        // =========================
        // BACKGROUND IMAGE
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
                                600,
                                650,
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
                600,
                650
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
                50,
                35,
                500,
                40
        );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        25
                )
        );

        titleLabel.setForeground(
                Color.WHITE
        );


        JLabel createLabel =
                new JLabel(
                        "CREATE NEW ACCOUNT",
                        SwingConstants.CENTER
                );

        createLabel.setBounds(
                100,
                95,
                400,
                30
        );

        createLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        createLabel.setForeground(
                Color.WHITE
        );


        // =========================
        // NAME
        // =========================

        JLabel nameLabel =
                new JLabel(
                        "Full Name"
                );

        nameLabel.setBounds(
                140,
                150,
                320,
                25
        );

        nameLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        nameLabel.setForeground(
                Color.WHITE
        );


        nameField =
                new JTextField();

        nameField.setBounds(
                140,
                180,
                320,
                40
        );

        nameField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );


        // =========================
        // ACCOUNT NUMBER
        // =========================

        JLabel accountLabel =
                new JLabel(
                        "Account Number"
                );

        accountLabel.setBounds(
                140,
                235,
                320,
                25
        );

        accountLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        accountLabel.setForeground(
                Color.WHITE
        );


        accountNumberField =
                new JTextField();

        accountNumberField.setBounds(
                140,
                265,
                320,
                40
        );

        accountNumberField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );


        // =========================
        // PIN
        // =========================

        JLabel pinLabel =
                new JLabel(
                        "4-Digit PIN"
                );

        pinLabel.setBounds(
                140,
                320,
                320,
                25
        );

        pinLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        pinLabel.setForeground(
                Color.WHITE
        );


        pinField =
                new JPasswordField();

        pinField.setBounds(
                140,
                350,
                320,
                40
        );

        pinField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );


        // =========================
        // OPENING BALANCE
        // =========================

        JLabel balanceLabel =
                new JLabel(
                        "Opening Balance"
                );

        balanceLabel.setBounds(
                140,
                405,
                320,
                25
        );

        balanceLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        balanceLabel.setForeground(
                Color.WHITE
        );


        balanceField =
                new JTextField();

        balanceField.setBounds(
                140,
                435,
                320,
                40
        );

        balanceField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );


        // =========================
        // CREATE BUTTON
        // =========================

        JButton createButton =
                new JButton(
                        "CREATE ACCOUNT"
                );

        createButton.setBounds(
                140,
                505,
                320,
                42
        );

        createButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        createButton.setBackground(
                new Color(
                        23,
                        107,
                        239
                )
        );

        createButton.setForeground(
                Color.WHITE
        );

        createButton.setFocusPainted(
                false
        );


        // =========================
        // BACK BUTTON
        // =========================

        JButton backButton =
                new JButton(
                        "BACK TO LOGIN"
                );

        backButton.setBounds(
                200,
                565,
                200,
                35
        );

        backButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        backButton.setBackground(
                Color.WHITE
        );

        backButton.setForeground(
                new Color(
                        13,
                        46,
                        107
                )
        );

        backButton.setFocusPainted(
                false
        );


        // =========================
        // ADD COMPONENTS
        // =========================

        backgroundLabel.add(
                titleLabel
        );

        backgroundLabel.add(
                createLabel
        );

        backgroundLabel.add(
                nameLabel
        );

        backgroundLabel.add(
                nameField
        );

        backgroundLabel.add(
                accountLabel
        );

        backgroundLabel.add(
                accountNumberField
        );

        backgroundLabel.add(
                pinLabel
        );

        backgroundLabel.add(
                pinField
        );

        backgroundLabel.add(
                balanceLabel
        );

        backgroundLabel.add(
                balanceField
        );

        backgroundLabel.add(
                createButton
        );

        backgroundLabel.add(
                backButton
        );


        setContentPane(
                backgroundLabel
        );


        // ENTER KEY = CREATE ACCOUNT

        getRootPane().setDefaultButton(
                createButton
        );


        // =========================
        // EVENTS
        // =========================

        createButton.addActionListener(
                e -> createAccount()
        );


        backButton.addActionListener(
                e -> {

                    LoginFrame loginFrame =
                            new LoginFrame();

                    loginFrame.setVisible(
                            true
                    );

                    dispose();
                }
        );
    }


    // =========================
    // CREATE ACCOUNT
    // =========================

    private void createAccount() {

        String name =
                nameField
                        .getText()
                        .trim();


        String accountNumber =
                accountNumberField
                        .getText()
                        .trim();


        String pin =
                new String(
                        pinField.getPassword()
                ).trim();


        String balanceText =
                balanceField
                        .getText()
                        .trim();


        // =========================
        // NAME VALIDATION
        // =========================

        if (name.isEmpty()) {

            showError(
                    "Name cannot be empty."
            );

            return;
        }


        if (!name.matches("[A-Za-z ]+")) {

            showError(
                    "Name should contain only letters and spaces."
            );

            return;
        }


        // =========================
        // ACCOUNT NUMBER VALIDATION
        // =========================

        if (accountNumber.isEmpty()) {

            showError(
                    "Account number cannot be empty."
            );

            return;
        }


        if (
                !accountNumber.matches(
                        "[A-Za-z0-9]{5,20}"
                )
        ) {

            showError(
                    "Account number must be 5-20 letters or numbers."
            );

            return;
        }


        // =========================
        // PIN VALIDATION
        // =========================

        if (!pin.matches("\\d{4}")) {

            showError(
                    "PIN must contain exactly 4 digits."
            );

            return;
        }


        // =========================
        // BALANCE VALIDATION
        // =========================

        BigDecimal balance;


        try {

            balance =
                    new BigDecimal(
                            balanceText
                    );

        } catch (NumberFormatException e) {

            showError(
                    "Enter a valid opening balance."
            );

            return;
        }


        if (
                balance.compareTo(
                        BigDecimal.ZERO
                ) < 0
        ) {

            showError(
                    "Balance cannot be negative."
            );

            return;
        }


        // =========================
        // CREATE ACCOUNT OBJECT
        // =========================

        Account account =
                new Account(
                        0,
                        accountNumber,
                        name,
                        pin,
                        balance
                );


        boolean created =
                accountDAO.createAccount(
                        account
                );


        // =========================
        // SUCCESS
        // =========================

        if (created) {

            JOptionPane.showMessageDialog(
                    this,
                    "Account created successfully!"
            );


            LoginFrame loginFrame =
                    new LoginFrame();


            loginFrame.setVisible(
                    true
            );


            dispose();

        } else {

            showError(
                    "Account could not be created.\n"
                            + "The account number may already exist."
            );
        }
    }


    // =========================
    // ERROR MESSAGE
    // =========================

    private void showError(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}