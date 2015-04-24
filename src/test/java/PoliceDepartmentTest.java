import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by welcome on 24-04-2015.
 */
public class PoliceDepartmentTest {

    @Test
    public void shouldSendMessageToAPBWhenUpdateMessageCall(){
        ParkingLot parkingLot = mock(ParkingLot.class);
        ParkingReciept parkingReciept = new ParkingReciept(1,20);
        PoliceDepartment policeDepartment = new PoliceDepartment();
        policeDepartment.update(parkingLot, parkingReciept);
        assertEquals(policeDepartment.getReportMessage(),"Car missing with car id :20");
    }

}