import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by welcome on 21-04-2015.
 */
public class TravellerTest {

    @Test
    public void testParkMyCar(){
        Traveller traveller = new Traveller();
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        int parkingId = traveller.parkMyCar(parkingLot);
        assertNotEquals(0,parkingId);
    }

    @Test
    public void testParkMyCarNotAvailable(){
        Traveller traveller = new Traveller();
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        traveller.parkMyCar(parkingLot);
        traveller.parkMyCar(parkingLot);
        traveller.parkMyCar(parkingLot);
        int parkingId = traveller.parkMyCar(parkingLot);
        assertEquals(0, parkingId);
    }

    @Test
    public void testParkMyCarWhenParkingLotNull(){
        Traveller traveller = new Traveller();
        ParkingLot parkingLot = null;
        int parkingId = traveller.parkMyCar(parkingLot);
        assertEquals(0, parkingId);
    }

    @Test
    public void testUnParkMyCar(){
        Traveller traveller = new Traveller();
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        Car car = traveller.unParkMyCar(parkingLot);
        assertNotNull(car);
    }

}