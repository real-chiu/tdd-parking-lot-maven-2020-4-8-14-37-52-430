package com.oocl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ManagerTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_be_able_to_add_parking_boys_to_management_list() {
        ParkingLot parkingLotOne = new ParkingLot(2);
        ParkingLot parkingLotTwo = new ParkingLot(4);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotOne);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotTwo);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotTwo);
        Manager manager = new Manager(parkingLotOne);
        manager.addParkingBoyToList(parkingBoy, smartParkingBoy, superSmartParkingBoy);


        ParkingBoy specifiedParkingBoyOne = manager.getParkingBoyByIndex(0);
        ParkingBoy specifiedParkingBoyTwo = manager.getParkingBoyByIndex(1);
        ParkingBoy specifiedParkingBoyThree = manager.getParkingBoyByIndex(2);

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

        ParkingTicket parkingTicketOne = manager.specifyParkingBoyToPark(carOne);
        ParkingTicket parkingTicketTwo = manager.specifyParkingBoyToPark(carTwo);
        Car fetchedCarOne = manager.specifyParkingBoyToFetchCar(parkingTicketOne);
        Car fetchedCarTwo = manager.specifyParkingBoyToFetchCar(parkingTicketTwo);
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

    @Test(expected = ParkingLotIsFullException.class)
    public void manager_should_return_parking_boy_exception_message_when_all_parking_lots_are_full() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoyOne = new ParkingBoy(firstParkingLot);
        ParkingBoy parkingBoyTwo = new ParkingBoy(secondParkingLot);
        Manager manager = new Manager(firstParkingLot, secondParkingLot);
        manager.addParkingBoyToList(parkingBoyOne, parkingBoyTwo);

        Car carOne =  new Car();
        Car carTwo =  new Car();
        Car carThree =  new Car();
        ParkingTicket parkingTicketOne = manager.specifyParkingBoyToPark(carOne);
        ParkingTicket parkingTicketTwo = manager.specifyParkingBoyToPark(carTwo);
        ParkingTicket parkingTicketThree = manager.specifyParkingBoyToPark(carThree);
        expectedException.expect(ParkingLotIsFullException.class);
        expectedException.expectMessage("Not enough position");
    }

    @Test(expected = UnrecognizedParkingTicketException.class)
    public void should_return_exception_message_when_fetch_with_incorrect_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy((parkingLot));
        Manager manager = new Manager(parkingLot);
        manager.addParkingBoyToList(parkingBoy);

        Car car = new Car();
        manager.specifyParkingBoyToFetchCar(new ParkingTicket());
        expectedException.expect(UnrecognizedParkingTicketException.class);
        expectedException.expectMessage("Unrecognized Parking Ticket");
    }

    @Test(expected = NoParkingTicketException.class)
    public void should_return_exception_message_when_fetch_without_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy((parkingLot));
        Manager manager = new Manager(parkingLot);
        manager.addParkingBoyToList(parkingBoy);

        manager.specifyParkingBoyToFetchCar(null);
        expectedException.expect(NoParkingTicketException.class);
        expectedException.expectMessage("Please provide your parking ticket");
    }
}
