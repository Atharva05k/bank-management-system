package com.bankapp.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

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

public class LoginFrame extends JFrame {

    private final JTextField accountNumberField;
    private final JPasswordField pinField;

    private final AccountDAO accountDAO;


    public LoginFrame() {

        accountDAO =
                new AccountDAO();


        // =========================
        // FRAME
        // =========================

        setTitle(
                "Bank Management System - Login"
        );

        setSize(
                520,
                560
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
                                520,
                                560,
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
                520,
                560
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
                40,
                50,
                440,
                45
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


        JLabel loginLabel =
                new JLabel(
                        "LOGIN TO YOUR ACCOUNT",
                        SwingConstants.CENTER
                );

        loginLabel.setBounds(
                100,
                125,
                320,
                30
        );

        loginLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        loginLabel.setForeground(
                Color.WHITE
        );


        // =========================
        // ACCOUNT NUMBER
        // =========================

        JLabel accountLabel =
                new JLabel(
                        "Account Number"
                );

        accountLabel.setBounds(
                110,
                190,
                300,
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
                110,
                220,
                300,
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
                110,
                285,
                300,
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
                110,
                315,
                300,
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
        // LOGIN BUTTON
        // =========================

        JButton loginButton =
                new JButton(
                        "LOGIN"
                );

        loginButton.setBounds(
                110,
                390,
                300,
                42
        );

        loginButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        loginButton.setBackground(
                new Color(
                        23,
                        107,
                        239
                )
        );

        loginButton.setForeground(
                Color.WHITE
        );

        loginButton.setFocusPainted(
                false
        );


        // =========================
        // CREATE ACCOUNT BUTTON
        // =========================

        JButton registerButton =
                new JButton(
                        "CREATE NEW ACCOUNT"
                );

        registerButton.setBounds(
                160,
                455,
                200,
                35
        );

        registerButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        registerButton.setBackground(
                Color.WHITE
        );

        registerButton.setForeground(
                new Color(
                        13,
                        46,
                        107
                )
        );

        registerButton.setFocusPainted(
                false
        );


        // =========================
        // ADD COMPONENTS
        // =========================

        backgroundLabel.add(
                titleLabel
        );

        backgroundLabel.add(
                loginLabel
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
                loginButton
        );

        backgroundLabel.add(
                registerButton
        );


        setContentPane(
                backgroundLabel
        );


        // ENTER KEY = LOGIN

        getRootPane().setDefaultButton(
                loginButton
        );


        // =========================
        // EVENTS
        // =========================

        loginButton.addActionListener(
                e -> login()
        );


        registerButton.addActionListener(
                e -> openRegisterScreen()
        );
    }


    // =========================
    // LOGIN
    // =========================

    private void login() {

        String accountNumber =
                accountNumberField
                        .getText()
                        .trim();


        String pin =
                new String(
                        pinField.getPassword()
                );


        // ACCOUNT NUMBER VALIDATION

        if (accountNumber.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your account number.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // PIN VALIDATION

        if (!pin.matches("\\d{4}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "PIN must contain exactly 4 digits.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // FIND ACCOUNT

        Account account =
                accountDAO.findByAccountNumber(
                        accountNumber
                );


        if (account == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Account not found.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // =========================
        // ACCOUNT LOCK CHECK
        // =========================

        if (
                accountDAO.isAccountLocked(
                        account.getId()
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Account is temporarily locked.\n"
                            + "Please try again after 5 minutes.",
                    "Account Locked",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =========================
        // CHECK PIN
        // =========================

        if (!account.getPin().equals(pin)) {

            accountDAO.recordFailedLogin(
                    account.getId()
            );


            // CHECK IF THIS ATTEMPT LOCKED ACCOUNT

            if (
                    accountDAO.isAccountLocked(
                            account.getId()
                    )
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Too many incorrect PIN attempts.\n"
                                + "Your account has been locked for 5 minutes.",
                        "Account Locked",
                        JOptionPane.WARNING_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Incorrect PIN.",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }


            return;
        }


        // =========================
        // SUCCESSFUL LOGIN
        // =========================

        accountDAO.resetFailedAttempts(
                account.getId()
        );


        DashboardFrame dashboard =
                new DashboardFrame(
                        account
                );


        dashboard.setVisible(
                true
        );


        dispose();
    }


    // =========================
    // OPEN REGISTER SCREEN
    // =========================

    private void openRegisterScreen() {

        RegisterFrame registerFrame =
                new RegisterFrame();


        registerFrame.setVisible(
                true
        );


        dispose();
    }
}