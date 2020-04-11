package com.oocl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manager extends ParkingBoy {
    private List<ParkingBoy> parkingBoyList = new ArrayList<>();

    public Manager(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    public void addParkingBoyToList(ParkingBoy... parkingBoys) {
        Arrays.asList(parkingBoys).forEach(parkingBoy -> parkingBoyList.add(parkingBoy));
    }

    public ParkingBoy getParkingBoyByIndex(int index) {
        return parkingBoyList.get(index);
    }

    public ParkingTicket specifyParkingBoyToPark(int index, Car carOne) {
        return getParkingBoyByIndex(index).park(carOne);
    }

    public Car specifyParkingBoyToFetchCar(int index, ParkingTicket parkingTicketOne) {
        return getParkingBoyByIndex(index).fetchCar(parkingTicketOne);
    }
}
