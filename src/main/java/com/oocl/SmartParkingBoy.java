package com.oocl;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot findCorrespondingParkingLotToParkCar() {
        ParkingLot parkingLotWithMoreCapacity = parkingLots.stream().max(Comparator.comparing(parkingLot -> parkingLot.getEmptyCapacity())).get();
        return parkingLotWithMoreCapacity;
    }
}
