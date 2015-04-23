import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Observable;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by welcome on 21-04-2015.
 */
public class ParkingLotTest {

    @Test
    public void testIfParkingLotIsGenerated() throws Exception {
        ParkingLot parkingLot = new ParkingLot(3,new ParkingLotOwner());
        assertNotNull(parkingLot);
    }

    @Test
    public void testIfParkingDone() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2,new ParkingLotOwner());
        Car car = new Car();
        int parkingId = parkingLot.park(car);
        assertNotEquals(parkingId, 0);
    }

    @Test
    public void testUnParkWhenCarAlreadyParked() throws Exception{
        ParkingLot parkingLot = new ParkingLot(2,new ParkingLotOwner());
        Car car = new Car();
        int parkingId =  parkingLot.park(car);
        Car returnedCar = parkingLot.unPark(parkingId);
        assertEquals(car, returnedCar);
    }

    @Test
    public void testUnParkAndCheckIfReturnedCarIsSame() throws Exception{
        ParkingLot parkingLot = new ParkingLot(2,new ParkingLotOwner());
        Car car = new Car();
        int parkingId =  parkingLot.park(car);
        parkingLot.unPark(parkingId);
        assertNotEquals(car, new Car());
    }

    @Test
    public void testUnParkWhenCarNotParked(){
        ParkingLot parkingLot = new ParkingLot(2,new ParkingLotOwner());
        Car returnedCar = parkingLot.unPark(10);
        assertNull(returnedCar);
    }

    @Test
    public void testIfOwnerIsNotifiedWhenParkingIsFull(){
        ParkingLotOwner parkingLotOwner = Mockito.mock(ParkingLotOwner.class);

        ParkingLot parkingLot = new ParkingLot(1, parkingLotOwner);
        Mockito.doNothing().when(parkingLotOwner).update(parkingLot, Boolean.TRUE);

        try {
            parkingLot.park(new Car());
            parkingLot.park(new Car());
            fail();
        } catch (Exception e) {
            Mockito.verify(parkingLotOwner, Mockito.times(1)).update(parkingLot, Boolean.TRUE);
        }



    }

    @Test
    public void testIfOwnerIsNotifiedWhenParkingSpaceAvailable(){
        ParkingLotOwner parkingLotOwner = Mockito.mock(ParkingLotOwner.class);

        ParkingLot parkingLot = new ParkingLot(1, parkingLotOwner);
        Mockito.doNothing().when(parkingLotOwner).update(parkingLot, Boolean.FALSE);
        int parkingId =-1;
        try {
            parkingId = parkingLot.park(new Car());
            parkingLot.park(new Car());

            fail();
        } catch (Exception e) {
            parkingLot.unPark(parkingId);
            Mockito.verify(parkingLotOwner, Mockito.times(1)).update(parkingLot, Boolean.FALSE);
        }
    }

    @Test
    public void testIfFBIAgentIsNotifiedOfParkingLot80percentFull()throws Exception{
        FBIAgent fbiAgent = Mockito.mock(FBIAgent.class);
        ParkingLotOwner parkingLotOwner = Mockito.mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(5, parkingLotOwner);
        Mockito.doNothing().when(fbiAgent).update(parkingLot, Boolean.TRUE);
        parkingLot.addParkingLotObservers(fbiAgent);
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        Mockito.verify(fbiAgent,Mockito.times(1)).update(parkingLot, Boolean.TRUE);
    }

    @Test
    public void testIfFBIAgentIsNotifiedOfParkingLotLessThan80percentFull()throws Exception{
        FBIAgent fbiAgent = Mockito.mock(FBIAgent.class);
        ParkingLotOwner parkingLotOwner = Mockito.mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(5, parkingLotOwner);
        Mockito.doNothing().when(fbiAgent).update(parkingLot, Boolean.FALSE);
        parkingLot.addParkingLotObservers(fbiAgent);
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        int parkingId1 = parkingLot.park(new Car());
        int parkingId = parkingLot.park(new Car());
        parkingLot.unPark(parkingId);
        parkingLot.unPark(parkingId1);
        Mockito.verify(fbiAgent,Mockito.times(1)).update(parkingLot, Boolean.FALSE);
    }
}