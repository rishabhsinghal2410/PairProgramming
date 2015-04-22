import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by welcome on 21-04-2015.
 */
public class TravellerTest {

    @Test
    public void testParkMyCar(){
        Traveller traveller = new Traveller(new Car());
        ParkingLot parkingLot = new ParkingLot(2);
        int parkingId = traveller.parkMyCar(parkingLot);
        assertNotEquals(0,parkingId);
    }

    @Test
    public void testParkingSpaceNotAvailable(){
        Traveller traveller = new Traveller(new Car());
        ParkingLot parkingLot = new ParkingLot(2);
        traveller.parkMyCar(parkingLot);
        traveller.parkMyCar(parkingLot);
        traveller.parkMyCar(parkingLot);
        int parkingId = traveller.parkMyCar(parkingLot);
        assertEquals(0, parkingId);
    }

    @Test
    public void testParkMyCarWhenParkingLotDoesNotExists(){
        Traveller traveller = new Traveller(new Car());
        ParkingLot parkingLot = null;
        int parkingId = traveller.parkMyCar(parkingLot);
        assertEquals(0, parkingId);
    }

    @Test
    public void testUnParkMyCar(){
        Traveller traveller = new Traveller(new Car());
        ParkingLot parkingLot = new ParkingLot(2);
        traveller.parkMyCar(parkingLot);
        Car car = traveller.unParkMyCar(parkingLot);
        assertNotNull(car);
    }

    @Test
    public void testUnParkMyCarWhenParkingWasNotDone(){
        Traveller traveller = new Traveller(new Car());
        Car car = traveller.unParkMyCar(new ParkingLot(2));
        assertNull(car);

    }

}