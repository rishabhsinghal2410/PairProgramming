import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;

/**
 * Created by welcome on 21-04-2015.
 */
public class TravellerTest {

    @Test
    public void testParkMyCarIsDone() throws Exception{
        /*Traveller traveller = new Traveller(new Car(123));
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        ParkingLot parkingLot1 = new ParkingLot(1,2,parkingLotOwner);
        ParkingLot parkingLot2 = new ParkingLot(2,2,parkingLotOwner);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLotOwner.getParkingLotsToBeDelegated());
        ParkingReciept parkingReciept = traveller.parkMyCar(parkingLotAttendant);
        assertNotNull(parkingReciept);*/
        Car car = new Car(12);
        Traveller traveller = new Traveller(car);
        ParkingLotAttendant parkingLotAttendant = Mockito.mock(ParkingLotAttendant.class);
        //ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        ParkingReciept parkingReciept = new ParkingReciept(1,1);
        Mockito.when(parkingLotAttendant.parkTravellersCar(car)).thenReturn(parkingReciept);
        ParkingReciept parkingReciept1 = traveller.parkMyCar(parkingLotAttendant);
        //assertEquals(parkingReciept,parkingReciept1);
        Mockito.verify(parkingLotAttendant,Mockito.times(1)).parkTravellersCar(car);
        //assertThat(parkingId, is(10));
        //Mockito.verify(parkingLotAttendant,Mockito.times(1)).getParkingLotWithSpaceAvailable();
        //Mockito.verify(parkingLot,Mockito.times(1)).park(Mockito.mock(Car.class));
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

/*
    @Test(expected = Exception.class)
    public void testParkMyCarWhenParkingLotDoesNotExists() throws Exception{
        Traveller traveller = new Traveller(new Car(12));
        ParkingLot parkingLot = null;
        int parkingId = traveller.parkMyCar(parkingLot);
        assertEquals(0, parkingId);
    }*/

    /*@Test
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

    /*@Test
    public void testUnParkMyCarWhenParkingWasNotDone(){
        Traveller traveller = new Traveller(new Car(12));
        Car car = traveller.unParkMyCar(new ParkingLot(1,2 , new ParkingLotOwner()));
        assertNull(car);
    }*/

}