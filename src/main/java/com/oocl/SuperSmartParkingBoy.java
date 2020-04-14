package com.oocl;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy{
    public SuperSmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot findCorrespondingParkingLotToParkCar() {
        return parkingLots.stream()
                .max(Comparator.comparing(parkingLot -> (double) parkingLot.getEmptyCapacity() / (double) parkingLot.getTotalCapacity()))
                .orElse(null);
    }
}
