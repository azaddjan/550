package com.azaddjan.seng550;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TicketService {
    private final NotificationService notificationService;
    private int nextTicketId = 1;

    @Autowired
    public TicketService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public Ticket submitTicket(int userID, String description) {
        validateTicketInput(userID, description);
        Ticket ticket = new Ticket(userID, description);
        ticket.setTicketID(generateTicketId());
        notificationService.notifyNewTicket(ticket);
        return ticket;
    }

    public void updateTicketStatus(Ticket ticket, TicketState newState) {
        ticket.setState(newState);
        notificationService.notifyTicketUpdate(ticket);
        System.out.println("Ticket " + ticket.getTicketID() + " status updated to: " + newState);
    }

    public void addComment(Ticket ticket, int userID, String content) {
        validateCommentInput(userID, content);
        Comment comment = new Comment(userID, content);
        ticket.getComments().add(comment);
        System.out.println("Comment added to ticket " + ticket.getTicketID() + ": " + content);
    }

    private void validateTicketInput(int userID, String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (userID <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }

    private void validateCommentInput(int userID, String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment cannot be empty");
        }
        if (userID <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }

    private synchronized int generateTicketId() {
        return nextTicketId++;
    }
}