package com.oocl;


public class UnrecognizedParkingTicketException extends RuntimeException {
    public UnrecognizedParkingTicketException() {
        super("Unrecognized Parking Exception");
    }
}
