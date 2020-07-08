package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Logon {
    private JTabbedPane tabbedPane1;
    private JPanel panel;
    private JPanel Register;
    private JPanel Login;
    private JTextField usernameField;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JTextField registerUsername;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton registerButton;
    private final Dashboard dashboard;
    private final JFrame frame;

    public Logon(Dashboard dashboard, Server server, Node node) {
        this.dashboard = dashboard;
        frame = new JFrame("App");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setTitle("Angus's Message Board - Please Login or Register");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userLogon();
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    userLogon();
                }
                loginButton.setEnabled(!passwordField.getText().isEmpty());
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userRegistration();
            }
        });
        passwordField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    userRegistration();
                }
                registerButton.setEnabled(!passwordField2.getText().isEmpty() || !passwordField1.getText().isEmpty());
            }
        });
    }

    private void userLogon() {
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();
        if (!inputUsername.isEmpty() && !inputPassword.isEmpty()) {
            if (dashboard.userLogon(inputUsername, inputPassword)) {
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Password!");
            }
        }
    }

    private void userRegistration() {
        String inputUsername = registerUsername.getText();
        String inputPassword1 = passwordField1.getText();
        String inputPassword2 = passwordField2.getText();
        if (!inputUsername.isEmpty() && !inputPassword1.isEmpty() ) {
            if (inputPassword1.equals(inputPassword2)) {
                if (!inputUsername.contains(" ") && !inputPassword1.contains(" ")) {
                    if (dashboard.userRegistration(inputUsername, inputPassword1)) {
                        tabbedPane1.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(null, "User registered! Please login...");
                        registerUsername.setText("");
                        passwordField1.setText("");
                        passwordField2.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "An error occurred, please try again...");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Spaces are not allowed in usernames or passwords...");
                }
            }
        } else {
                JOptionPane.showMessageDialog(null, "These passwords don't match!");
        }
    }
}

