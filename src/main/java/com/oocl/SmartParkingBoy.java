package com.oocl;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot findCorrespondingParkingLotToParkCar() {
        return parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getEmptyCapacity))
                .orElse(null);
    }
}
