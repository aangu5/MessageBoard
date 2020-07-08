package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        if (args.length != 3) {
            System.out.println("That's not enough arguments!");
            System.exit(0);
        } else  {
            int nodePort = Integer.parseInt(args[0]);
            String serverHostname = args[1];
            int serverPort = Integer.parseInt(args[2]);

            Node node = new Node(nodePort);
            Server server = new Server(serverHostname, serverPort, node);
            Dashboard dashboard = new Dashboard(server, node);
            //server.userLogon("angus", "angus");
        }
    }
}
