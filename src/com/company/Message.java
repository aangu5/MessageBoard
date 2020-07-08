package com.company;

import java.util.concurrent.TimeUnit;

public class Message {
    private int messageID;
    private final String subject = "";
    private String content;
    private final int viewCount = 0;
    private final int author;

    public Message(int inputID, String inputContent, int inputAuthor) {
        messageID = inputID;
        content = inputContent;
        author = inputAuthor;
    }

    public Message(int inputID, String inputContent, int inputAuthor,TimeUnit postedTime) {
        messageID = inputID;
        content = inputContent;
        author = inputAuthor;
    }

    public String getContent() {
        return content;
    }

    public int getAuthor() {
        return author;
    }
}
