package com.azaddjan.seng550;

import java.time.LocalDateTime;

public class Comment {
    private String content;
    private int userID;
    private LocalDateTime createdAt;

    public Comment(int userID, String content) {
        this.userID = userID;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public String getContent() { return content; }
}
