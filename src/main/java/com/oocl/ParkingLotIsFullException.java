package com.oocl;

public class ParkingLotIsFullException extends RuntimeException {
    public ParkingLotIsFullException() {
        super("Not enough position");
    }
}
