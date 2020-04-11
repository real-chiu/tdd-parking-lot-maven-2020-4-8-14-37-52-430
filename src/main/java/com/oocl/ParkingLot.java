package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        this.capacity = 10;
    }

    public boolean isFull() {
        return parkingTicketCarMap.size() == capacity;
    }

    public ParkingTicket park(Car car) throws ParkingLotIsFullException {
        if (parkingTicketCarMap.size() >= capacity) {
            throw new ParkingLotIsFullException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public boolean containsCarWithCorrespondingParkingTicket(ParkingTicket parkingTicket) {
        return parkingTicketCarMap.containsKey(parkingTicket);
    }

    public Car fetchCar(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException, NoParkingTicketException {
        if (parkingTicket == null) {
            throw new NoParkingTicketException();
        }
        if (!this.parkingTicketCarMap.containsKey(parkingTicket)) {
            throw new UnrecognizedParkingTicketException();
        }
        Car car = parkingTicketCarMap.get(parkingTicket);
        parkingTicketCarMap.remove(parkingTicket);
        return car;
    }
}
