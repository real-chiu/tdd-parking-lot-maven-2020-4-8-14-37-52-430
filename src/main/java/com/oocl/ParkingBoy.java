package com.oocl;

import java.util.Arrays;
import java.util.List;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = Arrays.asList(parkingLots);
    }

    public boolean isAllParkingLotFull() {
        return parkingLots.stream().allMatch(ParkingLot::isFull);
    }

    public boolean isAnyParkingLotContainsCarOfTheTicket(ParkingTicket ticket) {
        return parkingLots.stream().anyMatch(parkingLot -> parkingLot.containsCarWithCorrespondingParkingTicket(ticket));
    }

    public ParkingTicket park(Car car) {
        ParkingLot firstEmptyParkingLot = findCorrespondingParkingLotToParkCar();
        return firstEmptyParkingLot.park(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        ParkingLot correspondingParkingLot = findCorrespondingParkingLotToFetchCar(parkingTicket);
        return correspondingParkingLot.fetchCar(parkingTicket);
    }

    public ParkingLot findCorrespondingParkingLotToFetchCar(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new NoParkingTicketException();
        }
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.containsCarWithCorrespondingParkingTicket(parkingTicket))
                .findFirst()
                .orElseThrow(UnrecognizedParkingTicketException::new);
    }

    protected ParkingLot findCorrespondingParkingLotToParkCar() {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(ParkingLotIsFullException::new);
    }
}
