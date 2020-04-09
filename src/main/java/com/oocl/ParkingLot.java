package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<ParkingTicket, Car>();
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {

    }

    public int getParkingLotCapacity() {
        return parkingTicketCarMap.size();
    }
    public ParkingTicket park(Car car) {
        if (parkingTicketCarMap.size() >= capacity) {
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        Car car = parkingTicketCarMap.get(parkingTicket);
        parkingTicketCarMap.remove(parkingTicket);
        return car;
    }
}
