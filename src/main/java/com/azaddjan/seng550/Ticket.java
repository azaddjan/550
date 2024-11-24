package com.azaddjan.seng550;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private int ticketID;
    private String description;
    private TicketState state;
    private int userID;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Comment> comments;

    public Ticket(int userID, String description) {
        this.userID = userID;
        this.description = description;
        this.state = TicketState.NEW;
        this.createdAt = LocalDateTime.now();
        this.comments = new ArrayList<>();
    }

    // Getters and Setters
    public int getTicketID() { return ticketID; }
    public void setTicketID(int id) { this.ticketID = id; }
    public String getDescription() { return description; }
    public TicketState getState() { return state; }
    public void setState(TicketState state) { this.state = state; }
    public int getUserID() { return userID; }
    public List<Comment> getComments() { return comments; }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + ticketID +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", userID=" + userID +
                ", created=" + createdAt +
                '}';
    }
}