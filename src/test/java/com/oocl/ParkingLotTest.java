package com.oocl;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void should_return_parking_ticket_when_parking_boy_park_car() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = parkingLot.park(new Car());
        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_corresponding_car_when_parking_boy_is_given_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        Car parkedCar = parkingLot.fetchCar(parkingTicket);
        Assert.assertEquals(parkedCar, car);
    }

    @Test
    public void should_not_return_car_when_parking_boy_is_given_invalid_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket validParkingTicket = parkingLot.park(car);

        ParkingTicket invalidTicket = new ParkingTicket();
        Car carFetchedByInvalidTicket = parkingLot.fetchCar(invalidTicket);
        Assert.assertNull(carFetchedByInvalidTicket);
    }

    @Test
    public void should_not_return_car_when_parking_boy_is_given_used_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket validParkingTicket = parkingLot.park(car);
        Car carFetchedByValidParkingTicket = parkingLot.fetchCar(validParkingTicket);

        Car anotherCarFetchedByValidParkingTicket = parkingLot.fetchCar(validParkingTicket);

        Assert.assertNull(anotherCarFetchedByValidParkingTicket);
    }

}
