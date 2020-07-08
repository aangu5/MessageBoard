package com.company;

import java.util.concurrent.TimeUnit;

public class Dashboard {
    private final int selectedMessage = 0;
    private final int messageAuthor = 0;
    private final int messageToEdit = 0;
    private final int messageToDelete = 0;
    private final int selectedAuthor = 0;
    private final boolean isSystemOnline = true;
    private User currentUser;
    private final Server server;
    private final Node node;

    public Dashboard(Server server, Node node){
        this.server = server;
        this.node = node;
        runProgram();
    }

    private void runProgram() {
        Logon logonGUI = new Logon(this, server, node);
        while (currentUser == null) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!currentUser.isLoggedIn()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("User logged in!");
        MessageBoard messageBoard = new MessageBoard(this, currentUser);
    }

    public void newMessage(String inputContent) {
        server.newMessage(inputContent, currentUser);
    }

    public boolean userLogon(String inputUsername, String inputPassword) {
        currentUser = server.userLogon(inputUsername, inputPassword);
        if (currentUser == null) {
            return false;
        }
        currentUser.setLoggedIn(true);
        return true;
    }

    public boolean userRegistration(String inputUsername, String inputPassword) {
        return server.newUser(inputUsername, inputPassword);
    }

    public String getMessages() {
        String tempMessages = server.getMessages();
        return getMessageFormat(tempMessages);
    }

    public String getUsersMessages() {
        String tempMessages = server.getUsersMessages(currentUser);
        return getMessageFormat(tempMessages);
    }

    public String searchMessages(String searchTerm) {
        String tempMessages = server.searchMessages(searchTerm);
        return getMessageFormat(tempMessages);
    }

    private String getMessageFormat(String tempMessages) {
        String[] messages = tempMessages.split(";");
        StringBuilder outputMessages = new StringBuilder();
        for (String message : messages) {
            String[] elements = message.split("\\|");
            if (elements.length > 1){
                outputMessages.append(elements[1]).append(": ");
                outputMessages.append(elements[2]).append("\n");
            }
        }
        return outputMessages.toString();
    }

    public void userExit() {
        server.userLogoff();
        currentUser = null;
    }

    public void clearMessages() {
        server.clearMessages();
    }
}
