package com.company;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Server {
    private InetAddress serverIP;
    private int serverPort;
    private Node currentNode;

    public Server(String hostname, int serverPort, Node currentNode) {
        try {
            serverIP = InetAddress.getByName(hostname);
            this.serverPort = serverPort;
            this.currentNode = currentNode;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String message = "NEW," + currentNode.getNodeIPAddress().getHostAddress() + "," + currentNode.getNodePort();
        sendMessageToServer(message);
        String[] output = awaitMessageFromServer();
        if (output != null) {
            if (output[0].trim().equals("ACCEPTED")) {
                System.out.println("System online :)");
            }
        }
    }

    public void sendMessageToServer(String message) {
        try {
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, serverIP, serverPort);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public String[] awaitMessageFromServer() {
        byte[] buffer = new byte[1024];
        try {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            DatagramSocket socket = new DatagramSocket(currentNode.getNodePort());
            socket.setSoTimeout(0);
            socket.receive(packet);
            socket.close();
            String messages = new String(buffer);
            return messages.split(",");
        } catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }

    public String getMessages() {
        sendMessageToServer("GETMESSAGES," + currentNode.getNodeIPAddress().getHostAddress() + "," + currentNode.getNodePort());
        return getFormattedMessage();
    }

    public String searchMessages(String searchTerm) {
        sendMessageToServer("SEARCHMESSAGES," + currentNode.getNodeIPAddress().getHostAddress() + "," + currentNode.getNodePort() + "," + searchTerm);
        return getFormattedMessage();
    }

    public void newMessage(String inputContent, User author) {
        sendMessageToServer("NEWMESSAGE," + inputContent + ","  + author.getName());
    }

    public User userLogon(String inputUsername, String inputPassword) {
        sendMessageToServer("LOGON," + currentNode.getNodeIPAddress().getHostAddress() + ","  + currentNode.getNodePort() + "," + inputUsername + "," + inputPassword);
        String[] messages = awaitMessageFromServer();
        if (messages[0].equals("LOGONTRUE")) {
            User tempUser = new User(Integer.parseInt(messages[1]), messages[2], messages[3], true, Integer.parseInt(messages[4].trim()));
            System.out.println("Successful logon");
            return tempUser;
        } else {
            System.out.println("Unsuccessful logon");
            return null;
        }
    }

    public void clearMessages() {
        sendMessageToServer("CLEAR");
    }

    public void userLogoff() {
     sendMessageToServer("LOGOFF," + currentNode.getNodeIPAddress().getHostAddress() + "," + currentNode.getNodePort());
    }

    public boolean newUser(String inputUsername, String inputPassword) {
        sendMessageToServer("NEWUSER," + currentNode.getNodeIPAddress().getHostAddress() + "," + currentNode.getNodePort() + "," + inputUsername + "," + inputPassword);
        String[] output = awaitMessageFromServer();
        return output[0].equals("USERCREATED");
    }

    public String getUsersMessages(User inputUser) {
        System.out.println(1);
        sendMessageToServer("MYMESSAGES," + currentNode.getNodeIPAddress().getHostAddress() + "," + currentNode.getNodePort() + "," + inputUser.getName());
        return getFormattedMessage();
    }

    private String getFormattedMessage() {
        String[] messages = awaitMessageFromServer();
        if (messages[0].equals("MESSAGES")) {
            return messages[1];
        } else {
            return null;
        }
    }
}
