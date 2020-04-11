package com.oocl;

import org.omg.SendingContext.RunTime;

public class UnrecognizedParkingTicketException extends RuntimeException {
    public UnrecognizedParkingTicketException() {
        super("Unrecognized Parking Exception");
    }
}
