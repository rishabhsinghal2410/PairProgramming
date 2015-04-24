import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParkingLotOwnerTest {

    @Test
    public void testGiveMeMessageAsFullWhenIReceiveFullNotification() {
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        parkingLotOwner.update(null, Boolean.TRUE);
        String message = parkingLotOwner.getMessage();
        assertEquals(message,"parking lot is full");

    }

    @Test
    public void testGiveMeMessageAsSpaceAvailableWhenIReceiveAvailableNotification() {
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        parkingLotOwner.update(null, Boolean.FALSE);
        String message = parkingLotOwner.getMessage();
        assertEquals(message,"parking space is available");

    }
}