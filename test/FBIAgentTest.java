import org.junit.Test;
import org.mockito.Mockito;

import java.util.Observable;

import static org.junit.Assert.*;

/**
 * Created by welcome on 23-04-2015.
 */
public class FBIAgentTest {

    @Test
    public void testIsParkingLot80percentFull(){
        FBIAgent fbiAgent = new FBIAgent();
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        fbiAgent.update(parkingLot, Boolean.TRUE);
        String statusMessage = fbiAgent.getParkingLotStatusMessage();
        assertEquals("80% Full", statusMessage);
    }

    @Test
    public void testIsParkingLotIsLessThan80percentFull(){
        FBIAgent fbiAgent = new FBIAgent();
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        fbiAgent.update(parkingLot, Boolean.FALSE);
        String statusMessage = fbiAgent.getParkingLotStatusMessage();
        assertEquals("Less Than 80%", statusMessage);
    }
}