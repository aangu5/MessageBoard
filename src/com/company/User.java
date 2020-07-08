package com.company;

public class User {
    private String name;
    private int userID;
    private String password;
    private boolean loggedIn = false;

    public User(int inputID, String inputName, String inputPassword) {
        userID = inputID;
        name = inputName;
        password = inputPassword;
    }

    public User(int inputID, String username, String password, boolean loggedIn, int messageCount) {
        userID = inputID;
        name = username;
        this.password = password;
        this.loggedIn = loggedIn;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getUserID() {
        return userID;
    }

    public void logout() { loggedIn = false; }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
