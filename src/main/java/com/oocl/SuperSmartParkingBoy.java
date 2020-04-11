package com.oocl;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy{
    public SuperSmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLottWitHigherCapacityRatio = findParkingLotWitHigherCapacityRatio();
        return parkingLottWitHigherCapacityRatio.park(car);
    }

    public ParkingLot findParkingLotWitHigherCapacityRatio() {
        //stream.max will return first max, therefore handled duplicate max
        ParkingLot parkingLotWithMoreCapacity = parkingLots.stream().max(Comparator.comparing(parkingLot -> {
            double ratio = (double) parkingLot.getEmptyCapacity() / (double) parkingLot.getTotalCapacity();
            return ratio;
        })).get();
        return parkingLotWithMoreCapacity;
    }
}
