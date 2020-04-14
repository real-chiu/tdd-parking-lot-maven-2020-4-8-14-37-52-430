package com.oocl;

public class CarHasBeenParkedException extends NullPointerException {
    public CarHasBeenParkedException() {
        super("Car has been parked!");
    }
}