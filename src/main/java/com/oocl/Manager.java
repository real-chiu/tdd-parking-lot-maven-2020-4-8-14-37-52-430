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
        ParkingTicket parkingTicket = parkingBoyList.stream()
                .map(parkingBoy -> parkingBoy.park(carOne))
                .filter(car -> car != null)
                .findFirst()
                .orElseThrow(ParkingLotIsFullException::new);
        return parkingTicket;
    }

    public Car specifyParkingBoyToFetchCar(ParkingTicket parkingTicketOne) {
        if (parkingTicketOne == null) {
            throw new NoParkingTicketException();
        }
        Car car = parkingBoyList.stream()
                .map(parkingBoy -> parkingBoy.fetchCar(parkingTicketOne))
                .filter(parkingTicket -> parkingTicket != null)
                .findFirst()
                .orElseThrow(UnrecognizedParkingTicketException::new);
        return car;
    }
}
