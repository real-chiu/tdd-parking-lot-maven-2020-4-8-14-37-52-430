package com.oocl;

import org.junit.Assert;
import org.junit.Test;

public class ManagerTest {
    @Test
    public void should_be_able_to_add_parking_boys_to_management_list() {
        ParkingLot parkingLotOne = new ParkingLot(2);
        ParkingLot parkingLotTwo = new ParkingLot(4);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotOne);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotTwo);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotTwo);
        Manager manager = new Manager(parkingLotOne);
        manager.addParkingBoyToList(parkingBoy, smartParkingBoy, superSmartParkingBoy);


        ParkingBoy specifiedParkingBoyOne = manager.getParkingBoyList().get(0);
        ParkingBoy specifiedParkingBoyTwo = manager.getParkingBoyList().get(1);
        ParkingBoy specifiedParkingBoyThree = manager.getParkingBoyList().get(2);

        Assert.assertEquals(specifiedParkingBoyOne, parkingBoy);
        Assert.assertEquals(specifiedParkingBoyTwo, smartParkingBoy);
        Assert.assertEquals(specifiedParkingBoyThree, superSmartParkingBoy);
    }

    @Test
    public void should_be_able_to_specify_a_parking_boy_to_park_and_fetch_car() {
        ParkingLot parkingLotOne = new ParkingLot(2);
        ParkingLot parkingLotTwo = new ParkingLot(4);
        ParkingBoy parkingBoyOne = new ParkingBoy(parkingLotOne);
        SmartParkingBoy parkingBoyTwo = new SmartParkingBoy(parkingLotTwo);
        Manager manager = new Manager(parkingLotOne);
        manager.addParkingBoyToList(parkingBoyOne, parkingBoyTwo);

        Car carOne =  new Car();
        Car carTwo =  new Car();

        ParkingBoy specifiedParkingBoyOne = manager.getParkingBoyList().get(0);
        ParkingBoy specifiedParkingBoyTwo = manager.getParkingBoyList().get(1);
        ParkingTicket parkingTicketOne = specifiedParkingBoyOne.park(carOne);
        ParkingTicket parkingTicketTwo = specifiedParkingBoyTwo.park(carTwo);
        Car fetchedCarOne = manager.getParkingBoyList().get(0).fetchCar(parkingTicketOne);
        Car fetchedCarTwo = manager.getParkingBoyList().get(1).fetchCar(parkingTicketTwo);
        Assert.assertEquals(fetchedCarOne, carOne);
        Assert.assertEquals(fetchedCarTwo, carTwo);
    }

    @Test
    public void manager_should_be_able_to_park_and_fetch_car() {
        ParkingLot parkingLotOne = new ParkingLot(2);
        ParkingLot parkingLotTwo = new ParkingLot(4);
        Manager manager = new Manager(parkingLotOne, parkingLotTwo);

        Car carOne =  new Car();
        Car carTwo =  new Car();

        ParkingTicket parkingTicketOne = manager.park(carOne);
        ParkingTicket parkingTicketTwo = manager.park(carTwo);
        Car fetchedCarOne = manager.fetchCar(parkingTicketOne);
        Car fetchedCarTwo = manager.fetchCar(parkingTicketTwo);
        Assert.assertEquals(fetchedCarOne, carOne);
        Assert.assertEquals(fetchedCarTwo, carTwo);
    }
}
