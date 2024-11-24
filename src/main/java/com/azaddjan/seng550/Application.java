package com.azaddjan.seng550;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(TicketService ticketService) {
        return (args) -> {
            System.out.println("\nTesting Ticket System");
            System.out.println("=====================\n");

            // Test 1: Create a valid ticket
            System.out.println("Test 1: Creating a valid ticket");
            try {
                Ticket ticket1 = ticketService.submitTicket(1, "Network connection issue");
                System.out.println("Created ticket: " + ticket1);
                System.out.println("Test 1: Passed\n");
            } catch (Exception e) {
                System.out.println("Test 1 failed: " + e.getMessage() + "\n");
            }

            // Test 2: Try to create an invalid ticket
            System.out.println("Test 2: Creating an invalid ticket (empty description)");
            try {
                Ticket ticket2 = ticketService.submitTicket(1, "");
                System.out.println("Test 2 failed: Should have thrown exception\n");
            } catch (IllegalArgumentException e) {
                System.out.println("Test 2: Passed - Caught expected exception: " + e.getMessage() + "\n");
            }

            // Test 3: Update ticket status
            System.out.println("Test 3: Updating ticket status");
            try {
                Ticket ticket3 = ticketService.submitTicket(1, "Printer not working");
                ticketService.updateTicketStatus(ticket3, TicketState.IN_PROGRESS);
                System.out.println("Test 3: Passed\n");
            } catch (Exception e) {
                System.out.println("Test 3 failed: " + e.getMessage() + "\n");
            }

            // Test 4: Complete ticket lifecycle
            System.out.println("Test 4: Testing complete ticket lifecycle");
            try {
                Ticket ticket4 = ticketService.submitTicket(1, "Email configuration issue");
                System.out.println("Initial state: " + ticket4.getState());

                ticketService.updateTicketStatus(ticket4, TicketState.OPEN);
                ticketService.addComment(ticket4, 2, "Started working on configuration");

                ticketService.updateTicketStatus(ticket4, TicketState.IN_PROGRESS);
                ticketService.addComment(ticket4, 2, "Configuration in progress");

                ticketService.updateTicketStatus(ticket4, TicketState.RESOLVED);
                ticketService.addComment(ticket4, 2, "Configuration completed");

                ticketService.updateTicketStatus(ticket4, TicketState.CLOSED);
                System.out.println("Test 4: Passed - Full lifecycle completed\n");
            } catch (Exception e) {
                System.out.println("Test 4 failed: " + e.getMessage() + "\n");
            }
        };
    }
}





