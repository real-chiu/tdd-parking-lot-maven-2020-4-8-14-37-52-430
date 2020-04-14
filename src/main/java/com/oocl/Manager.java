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

    public ParkingTicket specifyParkingBoyToPark(Car carOne) {
        ParkingBoy chosenParkingBoy = parkingBoyList.stream()
                .filter(parkingBoy -> !parkingBoy.isAllParkingLotFull())
                .findFirst()
                .orElseThrow(ParkingLotIsFullException::new);

        return chosenParkingBoy.park(carOne);
    }


    public Car specifyParkingBoyToFetchCar(ParkingTicket parkingTicketOne) {
        if (parkingTicketOne == null) {
            throw new NoParkingTicketException();
        }
        ParkingBoy chosenParkingBoy = parkingBoyList.stream()
                .filter(parkingBoy -> parkingBoy.isAnyParkingLotContainsCarOfTheTicker(parkingTicketOne))
                .findFirst()
                .orElseThrow(UnrecognizedParkingTicketException::new);

        return chosenParkingBoy.fetchCar(parkingTicketOne);
    }
}
