package com.oocl;

import org.junit.Assert;
import org.junit.Test;

public class ParkingBoyTest {

    @Test
    public void should_able_to_park_car_when_parking_lot_capacity_is_smaller_than_max_Capacity() {
        ParkingBoy parkingBoy = new ParkingBoy(2);
        Car car = new Car();
        Car carTwo = new Car();
        ParkingTicket parkingTicket = ParkingBoy.park(car);
        ParkingTicket parkingTicketTwo = ParkingBoy.park(carTwo);

        Assert.assertNotNull(parkingTicket);
        Assert.assertNotNull(parkingTicketTwo);
    }
}
