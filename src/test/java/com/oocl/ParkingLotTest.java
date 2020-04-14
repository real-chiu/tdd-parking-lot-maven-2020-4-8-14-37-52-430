package com.oocl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ParkingLotTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

    @Test(expected = UnrecognizedParkingTicketException.class)
    public void should_not_return_car_when_parking_boy_is_given_invalid_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket validParkingTicket = parkingLot.park(car);

        ParkingTicket invalidTicket = new ParkingTicket();
        Car carFetchedByInvalidTicket = parkingLot.fetchCar(invalidTicket);

        Assert.assertNotNull(validParkingTicket);
        Assert.assertNull(carFetchedByInvalidTicket);
    }

    @Test(expected = UnrecognizedParkingTicketException.class)
    public void should_not_return_car_when_parking_boy_is_given_used_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket validParkingTicket = parkingLot.park(car);
        Car carFetchedByValidParkingTicket = parkingLot.fetchCar(validParkingTicket);

        Car anotherCarFetchedByValidParkingTicket = parkingLot.fetchCar(validParkingTicket);

        Assert.assertNotNull(carFetchedByValidParkingTicket);
        Assert.assertNull(anotherCarFetchedByValidParkingTicket);
    }

    @Test(expected = CarHasBeenParkedException.class)
    public void should_not_park_car_when_the_car_has_been_parked() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);
        parkingLot.park(car);
        expectedException.expect(CarHasBeenParkedException.class);
        expectedException.expectMessage("Car has been parked!");
    }

    @Test(expected = NoCarToParkException.class)
    public void should_not_park_car_when_car_is_not_provided() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(null);
        expectedException.expect(NoCarToParkException.class);
        expectedException.expectMessage("Please provide your car!");
    }
}
