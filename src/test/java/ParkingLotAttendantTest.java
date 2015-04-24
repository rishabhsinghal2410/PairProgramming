import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class ParkingLotAttendantTest {

    @Test
    public void testAttendantAbleParkCar() throws Exception{
        ParkingLotOwner parkingLotOwner = Mockito.mock(ParkingLotOwner.class);
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        ParkingLot parkingLot1 = Mockito.mock(ParkingLot.class);
        when(parkingLot1.isSpaceAvailable()).thenReturn(false);
        ParkingLot parkingLot2 = Mockito.mock(ParkingLot.class);
        when(parkingLot2.isSpaceAvailable()).thenReturn(true);
        Car car = new Car(12);
        when(parkingLot2.park(car)).thenReturn(new ParkingReciept(1, 1));
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
        parkingLotAttendant.updateParkingLotStrategy(false);
        ParkingReciept parkingReciept = parkingLotAttendant.parkTravellersCar(car);
        assertNotNull(parkingReciept);
    }

    @Test(expected = Exception.class)
    public void testAttendantHandlingUnParking() throws Exception{
        ParkingLotOwner parkingLotOwner = Mockito.mock(ParkingLotOwner.class);
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        ParkingLot parkingLot1 = Mockito.mock(ParkingLot.class);
        when(parkingLot1.isSpaceAvailable()).thenReturn(false);
        ParkingLot parkingLot2 = Mockito.mock(ParkingLot.class);
        when(parkingLot2.isSpaceAvailable()).thenReturn(false);
        Car car = new Car(12);
        ParkingReciept parkingReciept = new ParkingReciept(1,1);
        when(parkingLot2.park(car)).thenReturn(parkingReciept);
        when(parkingLot2.unPark(parkingReciept)).thenReturn(car);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
        parkingLotAttendant.updateParkingLotStrategy(true);
        ParkingReciept parkingReciept1 = parkingLotAttendant.parkTravellersCar(car);
        Car car1 = parkingLotAttendant.unParkTravellersCar(parkingReciept1);
        assertEquals(car, car1);
    }

    @Test
    public void testParkingInFestiveSeason() throws Exception{
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        ParkingLot parkingLot1 = new ParkingLot(1,5,parkingLotOwner);
        ParkingLot parkingLot2 = new ParkingLot(2,8,parkingLotOwner);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLotOwner.getParkingLotsToBeDelegated());
        parkingLotAttendant.updateParkingLotStrategy(true);
        parkingLotOwner.assignParkingLotAttendant(parkingLotAttendant);
        parkingLotOwner.informParkingAttendantAboutTodaysFestival();
        ParkingReciept parkingReciept = parkingLotAttendant.parkTravellersCar(new Car(12));
        assertThat(parkingReciept.getParkingLotId(), is(2));
    }

    @Test

    public void testIfLargeCarsCanBeDirectedToLotsWithMostFreeSpace() throws Exception {
        ParkingLot parkingLot1= mock(ParkingLot.class);
        when(parkingLot1.getParkingLotSize()).thenReturn(5);
        when(parkingLot1.getCurrentParkingOccupied()).thenReturn(2);
        when(parkingLot1.getParkingLotId()).thenReturn(101);
        ParkingLot parkingLot2= mock(ParkingLot.class);
        when(parkingLot2.getParkingLotSize()).thenReturn(3);
        when(parkingLot2.getCurrentParkingOccupied()).thenReturn(1);
        when(parkingLot2.getParkingLotId()).thenReturn(102);
        ParkingLot parkingLot3= mock(ParkingLot.class);
        when(parkingLot3.getParkingLotSize()).thenReturn(4);
        when(parkingLot3.getCurrentParkingOccupied()).thenReturn(2);
        when(parkingLot3.getParkingLotId()).thenReturn(103);
        List<ParkingLot> parkingLots=new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);
        ParkingLotAttendant parkingLotAttendant=new ParkingLotAttendant(parkingLots);
        parkingLotAttendant.updateParkingLotStrategy(false);
       ParkingReciept parkingReciept= parkingLotAttendant.parkTravellersCar(new Car(101, CarType.HATCHBACK));
        assertEquals(101,parkingReciept.getParkingLotId());

    }
}