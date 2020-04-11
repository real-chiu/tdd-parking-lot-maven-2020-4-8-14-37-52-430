package com.oocl;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }
    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithMoreCapacity = findParkingLotWithMoreCapacity();
        return parkingLotWithMoreCapacity.park(car);
    }

    public ParkingLot findParkingLotWithMoreCapacity() {
        //stream.max will return first max, therefore handled duplicate max
        ParkingLot parkingLotWithMoreCapacity = parkingLots.stream().max(Comparator.comparing(parkingLot -> parkingLot.getEmptyCapacity())).get();
        return parkingLotWithMoreCapacity;
    }
}
