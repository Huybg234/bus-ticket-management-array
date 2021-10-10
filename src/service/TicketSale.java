package service;

import entity.Passenger;
import entity.Ticket;

import java.util.Arrays;

public class TicketSale {
    private Passenger passenger;
    private Ticket[] tickets;
    private int ticketNumber;
    private float ticketPrice;

    public TicketSale() {
    }

    public TicketSale(Passenger passenger, Ticket[] tickets, int ticketNumber) {
        this.passenger = passenger;
        this.tickets = tickets;
        this.ticketNumber = ticketNumber;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Override
    public String toString() {
        return "TicketSale{" +
                "passenger=" + passenger +
                ", tickets=" + Arrays.toString(tickets) +
                ", ticketNumber=" + ticketNumber +
                '}';
    }
}
