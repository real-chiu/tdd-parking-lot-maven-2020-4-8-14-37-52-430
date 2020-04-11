package com.oocl;

public class NoParkingTicketException extends NullPointerException {
    public NoParkingTicketException() {
        super("Please provide your parking ticket");
    }
}
