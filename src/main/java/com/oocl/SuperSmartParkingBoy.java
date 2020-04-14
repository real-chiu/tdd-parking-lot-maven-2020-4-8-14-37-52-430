package com.oocl;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy{
    public SuperSmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot findCorrespondingParkingLotToParkCar() {
        ParkingLot parkingLotWithMoreCapacity = parkingLots.stream().max(Comparator.comparing(parkingLot -> {
            double ratio = (double) parkingLot.getEmptyCapacity() / (double) parkingLot.getTotalCapacity();
            return ratio;
        })).get();
        return parkingLotWithMoreCapacity;
    }
}
