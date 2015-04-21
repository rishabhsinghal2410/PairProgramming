import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by welcome on 21-04-2015.
 */
public class ParkingLotTest {

    @Test
    public void testGetParkingLotInstance() throws Exception {
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        assertNotNull(parkingLot);
    }

    @Test
    public void testPark() throws Exception {
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        int parkingId = parkingLot.park();
        assertNotEquals(parkingId, 0);
    }

    @Test
    public void testParkingNotAvailable(){
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        int parkingId = parkingLot.park();
        assertEquals(0,parkingId);
    }
}