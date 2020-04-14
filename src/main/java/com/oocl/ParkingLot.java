package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int DEFAULT_CAPACITY = 10;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public int getEmptyCapacity() {
        return capacity - parkingTicketCarMap.size();
    }

    public int getTotalCapacity() {
        return capacity;
    }

    public boolean isFull() {
        return parkingTicketCarMap.size() == capacity;
    }

    public ParkingTicket park(Car car) throws ParkingLotIsFullException {
        if (isCarParkedInThisParkingLot(car)) {
            throw new CarHasBeenParkedException();
        }
        if (isFull()) {
            throw new ParkingLotIsFullException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    private boolean isCarParkedInThisParkingLot(Car carToBeParked) {
        return parkingTicketCarMap.containsValue(carToBeParked);
    }

    public boolean containsCarWithCorrespondingParkingTicket(ParkingTicket parkingTicket) {
        return parkingTicketCarMap.containsKey(parkingTicket);
    }

    public Car fetchCar(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException, NoParkingTicketException {
        if (parkingTicket == null) {
            throw new NoParkingTicketException();
        }
        if (!parkingTicketCarMap.containsKey(parkingTicket)) {
            throw new UnrecognizedParkingTicketException();
        }
        Car car = parkingTicketCarMap.get(parkingTicket);
        parkingTicketCarMap.remove(parkingTicket);
        return car;
    }
}
