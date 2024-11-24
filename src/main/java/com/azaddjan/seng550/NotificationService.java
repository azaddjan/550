package com.azaddjan.seng550;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void notifyNewTicket(Ticket ticket) {
        System.out.println("Notification: New ticket created - " + ticket.getTicketID());
    }

    public void notifyTicketUpdate(Ticket ticket) {
        System.out.println("Notification: Ticket updated - " + ticket.getTicketID());
    }
}