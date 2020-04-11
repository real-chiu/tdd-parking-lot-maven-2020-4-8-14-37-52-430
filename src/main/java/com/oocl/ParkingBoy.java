package com.oocl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = Arrays.asList(parkingLots);
    }

    public ParkingTicket park(Car car) {
        ParkingLot firstEmptyParkingLot = findFirstEmptyParkingLot();
        return firstEmptyParkingLot.park(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        ParkingLot firstEmptyParkingLot = findFirstEmptyParkingLot();
        return firstEmptyParkingLot.fetchCar(parkingTicket);
    }

    private ParkingLot findFirstEmptyParkingLot() throws NoSuchElementException {
        try {
            ParkingLot firstEmptyParkingLot = parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().get();
            return firstEmptyParkingLot;
        } catch (NoSuchElementException noSuchElementException) {
            ParkingLot lastParkingLot = parkingLots.get(parkingLots.size() - 1);
            return lastParkingLot;
        }
    }
}
