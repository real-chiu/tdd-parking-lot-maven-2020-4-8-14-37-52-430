package com.oocl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SuperSmartParkingBoyTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_park_into_parking_lot_with_more_capacity_ratio() {
        ParkingLot parkingLotOne = new ParkingLot(2);
        ParkingLot parkingLotTwo = new ParkingLot(4);

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotOne, parkingLotTwo);
        ParkingBoy parkingBoyOne = new ParkingBoy(parkingLotOne);
        ParkingBoy parkingBoyTwo = new ParkingBoy(parkingLotTwo);

        Car carOne = new Car();
        Car carTwo = new Car();
        ParkingTicket parkingTicketFromParkingBoy = parkingBoyTwo.park(carOne);
        ParkingTicket parkingTicketFromSSParkingBoy = superSmartParkingBoy.park(carTwo);

        Car fetchedCarFromParkingLotOne = parkingBoyOne.fetchCar(parkingTicketFromSSParkingBoy);

        Assert.assertEquals(fetchedCarFromParkingLotOne, carTwo);
    }

    @Test
    public void should_park_sequentially_when_all_parking_lots_have_same_capacity() {
        ParkingLot parkingLotOne = new ParkingLot(2);
        ParkingLot parkingLotTwo = new ParkingLot(2);

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotOne, parkingLotTwo);
        ParkingBoy parkingBoyOne = new ParkingBoy(parkingLotOne);

        Car carOne = new Car();
        ParkingTicket parkingTicketFromSSParkingBoy = superSmartParkingBoy.park(carOne);

        Car fetchedCar = parkingBoyOne.fetchCar(parkingTicketFromSSParkingBoy);

        Assert.assertEquals(fetchedCar, carOne);
    }

    @Test
    public void should_fetch_car_from_corresponding_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(4);

        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(firstParkingLot, secondParkingLot);
        Car carOne =  new Car();
        Car carTwo =  new Car();
        ParkingTicket parkingTicketOne = superSmartParkingBoy.park(carOne);
        ParkingTicket parkingTicketTwo = superSmartParkingBoy.park(carTwo);
        Car fetchedCarOne = superSmartParkingBoy.fetchCar(parkingTicketOne);
        Car fetchedCarTwo = superSmartParkingBoy.fetchCar(parkingTicketTwo);
        Assert.assertEquals(fetchedCarOne, carOne);
        Assert.assertEquals(fetchedCarTwo, carTwo);

    }
}

