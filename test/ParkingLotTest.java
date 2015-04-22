import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by welcome on 21-04-2015.
 */
public class ParkingLotTest {

    @Test
    public void testIfParkingLotIsAvalaible() throws Exception {
        ParkingLot parkingLot = new ParkingLot(3);
        assertNotNull(parkingLot);
    }

    @Test
    public void testIfParkingDone() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        int parkingId = parkingLot.park(car);
        assertNotEquals(parkingId, 0);
    }

    @Test
    public void testParkingSpaceNotAvailable(){
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        parkingLot.park(car1);
        Car car2 = new Car();
        int parkingId = parkingLot.park(car2);
        assertEquals(0, parkingId);
    }

    @Test
    public void testUnParkWhenCarAlreadyParked(){
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        int parkingId =  parkingLot.park(car);
        Car returnedCar = parkingLot.unPark(parkingId);
        assertEquals(car,returnedCar);
    }

    @Test
    public void testUnParkAndCheckIfReturnedCarIsSame(){
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        int parkingId =  parkingLot.park(car);
        parkingLot.unPark(parkingId);
        assertNotEquals(car,new Car());
    }

    @Test
    public void testUnParkWhenCarNotParked(){
        ParkingLot parkingLot = new ParkingLot(2);
        Car returnedCar = parkingLot.unPark(10);
        assertNull(returnedCar);
    }
}