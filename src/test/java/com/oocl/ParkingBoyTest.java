package com.oocl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ParkingBoyTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

    @Test(expected = ParkingLotIsFullException.class)
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

    @Test(expected = UnrecognizedParkingTicketException.class)
    public void should_return_exception_message_when_fetch_with_incorrect_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy((parkingLot));
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetchCar(new ParkingTicket());
        expectedException.expect(UnrecognizedParkingTicketException.class);
        expectedException.expectMessage("Unrecognized Parking Ticket");
    }

    @Test(expected = NoParkingTicketException.class)
    public void should_return_exception_message_when_fetch_without_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy((parkingLot));

        parkingBoy.fetchCar(null);
        expectedException.expect(NoParkingTicketException.class);
        expectedException.expectMessage("Please provide your parking ticket");
    }

    @Test(expected = ParkingLotIsFullException.class)
    public void should_return_exception_message_when_park_into_full_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy((parkingLot));
        Car carOne = new Car();
        Car carTwo = new Car();
        parkingBoy.park(carOne);
        parkingBoy.park(carTwo);
        expectedException.expect(ParkingLotIsFullException.class);
        expectedException.expectMessage("Not enough position");
    }

    @Test
    public void should_park_into_first_parking_lot_given_that_the_two_parking_lot_is_not_full() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);

        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        Car carOne =  new Car();
        ParkingTicket parkingTicket = parkingBoy.park(carOne);
        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_park_into_second_parking_lot_when_first_parking_lot_is_full() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);

        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        Car carOne =  new Car();
        Car carTwo =  new Car();
        ParkingTicket parkingTicketOne = parkingBoy.park(carOne);
        ParkingTicket parkingTicketTwo = parkingBoy.park(carTwo);
        Assert.assertNotNull(parkingTicketTwo);
    }

    @Test(expected = ParkingLotIsFullException.class)
    public void should_return_exception_message_when_all_parking_lots_are_full() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);

        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        Car carOne =  new Car();
        Car carTwo =  new Car();
        Car carThree =  new Car();
        ParkingTicket parkingTicketOne = parkingBoy.park(carOne);
        ParkingTicket parkingTicketTwo = parkingBoy.park(carTwo);
        ParkingTicket parkingTicketThree = parkingBoy.park(carThree);
        expectedException.expect(ParkingLotIsFullException.class);
        expectedException.expectMessage("Not enough position");
    }

    @Test
    public void should_fetch_car_from_corresponding_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);

        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        Car carOne =  new Car();
        Car carTwo =  new Car();
        ParkingTicket parkingTicketOne = parkingBoy.park(carOne);
        ParkingTicket parkingTicketTwo = parkingBoy.park(carTwo);
        Car fetchedCar = parkingBoy.fetchCar(parkingTicketTwo);
        Assert.assertEquals(fetchedCar, carTwo);

    }
}
