package com.company;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Node {
    private InetAddress nodeIPAddress = null;
    private int nodePort = 0;

    public Node(int inputPort) {
        try {
            nodeIPAddress = InetAddress.getLocalHost();
            nodePort = inputPort;
        } catch (UnknownHostException error) {
            error.printStackTrace();
        }
    }

    public InetAddress getNodeIPAddress() { return nodeIPAddress; }
    public int getNodePort() { return nodePort; }
}