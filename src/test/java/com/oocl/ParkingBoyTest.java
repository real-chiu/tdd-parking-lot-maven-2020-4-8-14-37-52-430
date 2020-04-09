package com.oocl;

import org.junit.Assert;
import org.junit.Test;

public class ParkingBoyTest {

    @Test
    public void should_able_to_park_car_when_parking_lot_capacity_is_smaller_than_max_Capacity() {
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Car carTwo = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        ParkingTicket parkingTicketTwo = parkingBoy.park(carTwo);

        Assert.assertNotNull(parkingTicket);
        Assert.assertNotNull(parkingTicketTwo);
    }

    @Test
    public void should_not_be_able_to_park_car_when_parking_lot_capacity_is_max() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Car carTwo = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        ParkingTicket parkingTicketTwo = parkingBoy.park(carTwo);

        Assert.assertNotNull(parkingTicket);
        Assert.assertNull(parkingTicketTwo);
    }
}
