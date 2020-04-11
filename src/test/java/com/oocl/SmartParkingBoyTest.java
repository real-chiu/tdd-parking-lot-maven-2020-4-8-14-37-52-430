package com.oocl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SmartParkingBoyTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test(expected = UnrecognizedParkingTicketException.class)
    public void should_park_into_parking_lot_with_more_capacity() {
        ParkingLot parkingLotOne = new ParkingLot(2);
        ParkingLot parkingLotTwo = new ParkingLot(4);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotOne, parkingLotTwo);
        ParkingBoy parkingBoyOne = new ParkingBoy(parkingLotOne);
        ParkingBoy parkingBoyTwo = new ParkingBoy(parkingLotTwo);

        Car carOne = new Car();
        Car carTwo = new Car();
        ParkingTicket parkingTicketOne = smartParkingBoy.park(carOne);
        ParkingTicket parkingTicketTwo = smartParkingBoy.park(carTwo);

        Car firstFetchedCarByBoyTwo = parkingBoyTwo.fetchCar(parkingTicketOne);
        Car secondFetchedCarByBoyTwo = parkingBoyTwo.fetchCar(parkingTicketTwo);

        Assert.assertEquals(firstFetchedCarByBoyTwo, carOne);
        Assert.assertEquals(secondFetchedCarByBoyTwo, carTwo);

        Car firstFetchedCarByBoyOne = parkingBoyOne.fetchCar(parkingTicketOne);
        expectedException.expect(UnrecognizedParkingTicketException.class);
        expectedException.expectMessage("Unrecognized Parking Ticket");
    }

    @Test
    public void should_park_sequentially_when_all_parking_lots_have_same_capacity() {
        ParkingLot parkingLotOne = new ParkingLot(2);
        ParkingLot parkingLotTwo = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotOne, parkingLotTwo);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotOne);

        Car carOne = new Car();
        ParkingTicket parkingTicketOne = smartParkingBoy.park(carOne);

        Car fetchedCar = parkingBoy.fetchCar(parkingTicketOne);
        Assert.assertEquals(fetchedCar, carOne);
    }

    @Test
    public void should_fetch_car_from_corresponding_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(4);

        ParkingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);
        Car carOne =  new Car();
        Car carTwo =  new Car();
        ParkingTicket parkingTicketOne = smartParkingBoy.park(carOne);
        ParkingTicket parkingTicketTwo = smartParkingBoy.park(carTwo);
        Car fetchedCarOne = smartParkingBoy.fetchCar(parkingTicketOne);
        Car fetchedCarTwo = smartParkingBoy.fetchCar(parkingTicketTwo);
        Assert.assertEquals(fetchedCarOne, carOne);
        Assert.assertEquals(fetchedCarTwo, carTwo);

    }
}
