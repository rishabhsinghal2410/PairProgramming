import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by welcome on 21-04-2015.
 */
public class TravellerTest {

    @Test
    public void testParkMyCar() throws Exception{
        Traveller traveller = new Traveller(new Car());
        ParkingLot parkingLot = new ParkingLot(2);
        int parkingId = traveller.parkMyCar(parkingLot);
        assertNotEquals(0,parkingId);
    }

    @Test(expected = Exception.class)
    public void testParkingNotDone() throws Exception{
        Traveller traveller = new Traveller(new Car());
        ParkingLot parkingLot = new ParkingLot(2);
        traveller.parkMyCar(parkingLot);
        traveller.parkMyCar(parkingLot);
        traveller.parkMyCar(parkingLot);
    }


    @Test(expected = Exception.class)
    public void testParkMyCarWhenParkingLotDoesNotExists() throws Exception{
        Traveller traveller = new Traveller(new Car());
        ParkingLot parkingLot = null;
        int parkingId = traveller.parkMyCar(parkingLot);
        assertEquals(0, parkingId);
    }

    @Test
    public void testUnParkMyCarAndGetMyCar() throws Exception{
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