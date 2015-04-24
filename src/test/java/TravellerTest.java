import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TravellerTest {

    @Test
    public void testParkMyCarIsDone() throws Exception{
        Car car = new Car(12);
        Traveller traveller = new Traveller(car);
        ParkingLotAttendant parkingLotAttendant = Mockito.mock(ParkingLotAttendant.class);
        ParkingReciept parkingReciept = new ParkingReciept(1,1);
        Mockito.when(parkingLotAttendant.parkTravellersCar(car)).thenReturn(parkingReciept);
        ParkingReciept parkingReciept1 = traveller.parkMyCar(parkingLotAttendant);
        Mockito.verify(parkingLotAttendant,Mockito.times(1)).parkTravellersCar(car);
        assertThat(parkingReciept1.getParkingLotId(), is(1));
    }

    @Test(expected = Exception.class)
    public void testParkingNotDone() throws Exception{
        Traveller traveller1 = new Traveller(new Car(12));
        Traveller traveller2 = new Traveller(new Car(23));
        Traveller traveller3 = new Traveller(new Car(34));
        Traveller traveller4 = new Traveller(new Car(45));
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        ParkingLot parkingLot1 = new ParkingLot(1,2,parkingLotOwner);
        ParkingLot parkingLot2 = new ParkingLot(2,1,parkingLotOwner);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLotOwner.getParkingLotsToBeDelegated());
        traveller1.parkMyCar(parkingLotAttendant);
        traveller2.parkMyCar(parkingLotAttendant);
        traveller3.parkMyCar(parkingLotAttendant);
        traveller4.parkMyCar(parkingLotAttendant);
    }


    @Test(expected = Exception.class)
    public void testParkMyCarWhenParkingLotDoesNotExists() throws Exception{
        Car car = new Car(12);
        Traveller traveller = new Traveller(new Car(12));
        ParkingLotAttendant parkingLotAttendant = Mockito.mock(ParkingLotAttendant.class);
        ParkingReciept parkingReciept = new ParkingReciept(1,1);
        Mockito.when(parkingLotAttendant.parkTravellersCar(car)).thenThrow(Exception.class);
        ParkingLot parkingLot = null;
        ParkingReciept parkingReciept1 = traveller.parkMyCar(parkingLotAttendant);
        assertNull(parkingReciept1);
    }

    @Test
    public void testUnParkMyCarAndGetMyCar() throws Exception{
        Traveller traveller = new Traveller(new Car(12));
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        ParkingLot parkingLot1 = new ParkingLot(1,2 , parkingLotOwner);
        ParkingLot parkingLot2 = new ParkingLot(2,2 , parkingLotOwner);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLotOwner.getParkingLotsToBeDelegated());
        parkingLotAttendant.updateParkingLotStrategy(false);
        ParkingReciept parkingReciept = traveller.parkMyCar(parkingLotAttendant);
        Car car = traveller.unParkMyCar(parkingLotAttendant);
        assertNotNull(car);
    }

    @Test(expected = Exception.class)
    public void testUnParkMyCarWhenParkingWasNotDone() throws Exception{
        Traveller traveller = new Traveller(new Car(12));
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        ParkingLot parkingLot1 = new ParkingLot(1,2 , parkingLotOwner);
        ParkingLot parkingLot2 = new ParkingLot(2,2 , parkingLotOwner);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLotOwner.getParkingLotsToBeDelegated());
        parkingLotAttendant.updateParkingLotStrategy(false);
        Car car = traveller.unParkMyCar(parkingLotAttendant);
        assertNull(car);
    }

}