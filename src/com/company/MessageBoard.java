package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MessageBoard {
    private JTextField Input;
    private JTextField title;
    private JTextArea messageArea;
    private JButton button1;
    private JButton myMessagesButton;
    private JButton searchButton;
    private JTextField searchBox;
    private JPanel panel;
    private JButton logoutButton;
    private JButton sendButton;
    private JScrollPane scrollPane;
    private JButton clearMessages;
    final Dashboard dashboard;
    final User currentUser;

    public MessageBoard(Dashboard dashboard, User currentUser) {
        this.dashboard = dashboard;
        this.currentUser = currentUser;
        String messages = dashboard.getMessages();
        messageArea.setText((messages != null) ? messages : "There are no messages available...");
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
        title.setText("Welcome to the message board - " + currentUser.getName());
        JFrame frame = new JFrame("App");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setTitle("Angus's Message Board");

        Input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    sendMessage();
                    messageArea.setText(dashboard.getMessages());
                }
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messages = dashboard.getMessages();
                messageArea.setText((messages != null) ? messages : "There are no messages available...");
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
                messageArea.setText(dashboard.getMessages());
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboard.userExit();
                System.exit(0);
            }
        });
        myMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageArea.setText(dashboard.getUsersMessages());
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMessages();
            }
        });
        searchBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    searchMessages();
                }
            }
        });
        clearMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboard.clearMessages();
                messageArea.setText(dashboard.getMessages());
            }
        });
    }

    private void sendMessage() {
        String tempString = Input.getText();
        if (tempString != null && !tempString.equals("") && !tempString.isEmpty()) {
            String tempContent = Input.getText();
            dashboard.newMessage(tempContent);
            Input.setText("");
        }
    }

    private void searchMessages() {
        messageArea.setText(dashboard.searchMessages(searchBox.getText()));
    }
}


