package com.oocl;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void should_return_parking_ticket_when_parking_boy_park_car() {
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_corresponding_car_when_parking_boy_is_given_parking_ticket() {
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car parkedCar = parkingBoy.fetchCar(parkingTicket);
        Assert.assertEquals(parkedCar, car);
    }

    @Test
    public void should_not_return_car_when_parking_boy_is_given_invalid_parking_ticket() {
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        ParkingTicket validParkingTicket = parkingBoy.park(car);

        ParkingTicket invalidTicket = new ParkingTicket();
        Car carFetchedByInvalidTicket = parkingBoy.fetchCar(invalidTicket);
        Assert.assertNull(carFetchedByInvalidTicket);
    }

    @Test
    public void should_not_return_car_when_parking_boy_is_given_used_parking_ticket() {
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        ParkingTicket validParkingTicket = parkingBoy.park(car);
        Car carFetchedByValidParkingTicket = parkingBoy.fetchCar(validParkingTicket);

        Car anotherCarFetchedByValidParkingTicket = parkingBoy.fetchCar(validParkingTicket);

        Assert.assertNull(anotherCarFetchedByValidParkingTicket);
    }
}
