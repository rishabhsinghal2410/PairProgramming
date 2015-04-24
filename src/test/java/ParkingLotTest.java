import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ParkingLotTest {

    @Test
    public void testIfParkingLotIsGenerated() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1,3,new ParkingLotOwner());
        assertNotNull(parkingLot);
    }

    @Test
    public void testIfParkingDone() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1,2,new ParkingLotOwner());
        Car car = new Car(123);
        ParkingReciept parkingReciept = parkingLot.park(car);
        assertNotNull(parkingReciept);
    }

    @Test
    public void testUnParkWhenCarAlreadyParked() throws Exception{
        ParkingLot parkingLot = new ParkingLot(1,2,new ParkingLotOwner());
        Car car = new Car(123);
        ParkingReciept parkingReciept =  parkingLot.park(car);
        Car returnedCar = parkingLot.unPark(parkingReciept);
        assertEquals(car, returnedCar);
    }

    @Test
    public void testUnParkAndCheckIfReturnedCarIsSame() throws Exception{
        ParkingLot parkingLot = new ParkingLot(1,2,new ParkingLotOwner());
        Car car = new Car(123);
        ParkingReciept parkingReciept =  parkingLot.park(car);
        parkingLot.unPark(parkingReciept);
        assertNotEquals(car, new Car(234));
    }

    @Test
    public void testUnParkWhenCarNotParked(){
        ParkingLot parkingLot = new ParkingLot(1,2,new ParkingLotOwner());
        Car returnedCar = parkingLot.unPark(new ParkingReciept(1, 123));
        assertNull(returnedCar);
    }

    @Test
    public void testIfOwnerIsNotifiedWhenParkingIsFull(){
        ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);

        ParkingLot parkingLot = new ParkingLot(1,1, parkingLotOwner);
        Mockito.doNothing().when(parkingLotOwner).update(parkingLot, Boolean.TRUE);

        try {
            parkingLot.park(new Car(123));
            parkingLot.park(new Car(234));
            fail();
        } catch (Exception e) {
            Mockito.verify(parkingLotOwner, Mockito.times(1)).update(parkingLot, Boolean.TRUE);
        }



    }

    @Test
    public void testIfOwnerIsNotifiedWhenParkingSpaceAvailable(){
        ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);

        ParkingLot parkingLot = new ParkingLot(1,1, parkingLotOwner);
        Mockito.doNothing().when(parkingLotOwner).update(parkingLot, Boolean.FALSE);
        ParkingReciept parkingReciept = null;
        try {
            parkingReciept = parkingLot.park(new Car(123));
            parkingLot.park(new Car(234));

            fail();
        } catch (Exception e) {
            parkingLot.unPark(parkingReciept);
            Mockito.verify(parkingLotOwner, Mockito.times(1)).update(parkingLot, Boolean.FALSE);
        }
    }

    @Test
    public void testIfFBIAgentIsNotifiedOfParkingLot80percentFull()throws Exception{
        FBIAgent fbiAgent = mock(FBIAgent.class);
        ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(1,5, parkingLotOwner);
        Mockito.doNothing().when(fbiAgent).update(parkingLot, Boolean.TRUE);
        parkingLot.addParkingLotObservers(fbiAgent);
        parkingLot.park(new Car(12));
        parkingLot.park(new Car(23));
        parkingLot.park(new Car(34));
        parkingLot.park(new Car(45));
        Mockito.verify(fbiAgent, Mockito.times(1)).update(parkingLot, Boolean.TRUE);
    }

    @Test
    public void testIfFBIAgentIsNotifiedOfParkingLotLessThan80percentFull()throws Exception{
        FBIAgent fbiAgent = mock(FBIAgent.class);
        ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(1,5, parkingLotOwner);
        Mockito.doNothing().when(fbiAgent).update(parkingLot, Boolean.FALSE);
        parkingLot.addParkingLotObservers(fbiAgent);
        parkingLot.park(new Car(12));
        parkingLot.park(new Car(23));
        parkingLot.park(new Car(34));
        ParkingReciept parkingReciept1 = parkingLot.park(new Car(45));
        ParkingReciept parkingReciept2 = parkingLot.park(new Car(56));
        parkingLot.unPark(parkingReciept1);
        parkingLot.unPark(parkingReciept2);
        Mockito.verify(fbiAgent,Mockito.times(1)).update(parkingLot, Boolean.FALSE);
    }

    @Test
    public void testIfFBIAgentIsNotifiedOfParkingLot80percentFullAndOwnerIsNotNotified()throws Exception{
        FBIAgent fbiAgent = mock(FBIAgent.class);
        ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(1,5, parkingLotOwner);
        Mockito.doNothing().when(fbiAgent).update(parkingLot, Boolean.TRUE);
        Mockito.doNothing().when(parkingLotOwner).update(parkingLot, Boolean.TRUE);
        parkingLot.addParkingLotObservers(fbiAgent);
        parkingLot.park(new Car(12));
        parkingLot.park(new Car(23));
        parkingLot.park(new Car(34));
        parkingLot.park(new Car(45));
        Mockito.verify(parkingLotOwner,Mockito.times(0)).update(parkingLot, Boolean.TRUE);
    }

    @Test
    public void testIfSpaceAvailableInParkingLotUpdatesCorrectly() throws Exception{
        ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(1,5, parkingLotOwner);
        parkingLot.park(mock(Car.class));
        parkingLot.park(mock(Car.class));
        parkingLot.park(mock(Car.class));
        boolean spaceAvailable = parkingLot.isSpaceAvailable();
        assertThat(spaceAvailable, is(true));
    }

    @Test
    public void testIfSpaceAvailableInParkingLotUpdatesCorrectlyWhenFull() throws Exception{
        ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(1,5, parkingLotOwner);
        parkingLot.park(mock(Car.class));
        parkingLot.park(mock(Car.class));
        parkingLot.park(mock(Car.class));
        parkingLot.park(mock(Car.class));
        parkingLot.park(mock(Car.class));
        boolean spaceAvailable = parkingLot.isSpaceAvailable();
        assertThat(spaceAvailable, is(false));
    }

    @Test
    public void shouldNotifyPoliceDepartmentWhenCarIsNotFoundWhileUnparkTheCar() throws Exception {

        PoliceDepartment policeDepartment = mock(PoliceDepartment.class);
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(1, 3, parkingLotOwner);
        parkingLot.addMissingCarObservers(policeDepartment);
        ParkingReciept reciept = new ParkingReciept(1,1);
        Car car = parkingLot.unPark(reciept);
        assertNull(car);
        verify(policeDepartment).missingVehicleUpdate(parkingLot, reciept);

    }

    @Test
    public void shouldNotNotifyPoliceDepartmentWhenCarIsFoundWhileUnparkTheCar() throws Exception {
        PoliceDepartment policeDepartment = mock(PoliceDepartment.class);
        Car car = new Car(2);
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(1, 3, parkingLotOwner);
        parkingLot.addMissingCarObservers(policeDepartment);
        ParkingReciept reciept = parkingLot.park(car);
        Car carReceived = parkingLot.unPark(reciept);
        assertNotNull(carReceived);
        verify(policeDepartment,times(0)).missingVehicleUpdate(parkingLot, reciept);
    }

    @Test
    public void shouldNotifyFBIAgentWhenCarIsNotFoundWhileUnparkTheCar() throws Exception {

        FBIAgent fbiAgent = mock(FBIAgent.class);
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(1, 3, parkingLotOwner);
        parkingLot.addMissingCarObservers(fbiAgent);
        ParkingReciept reciept = new ParkingReciept(1,1);
        Car car = parkingLot.unPark(reciept);
        assertNull(car);
        verify(fbiAgent).missingVehicleUpdate(parkingLot, reciept);

    }

    @Test
    public void shouldNotNotifyFBIAgentWhenCarIsFoundWhileUnparkTheCar() throws Exception {
        FBIAgent fbiAgent = mock(FBIAgent.class);
        Car car = new Car(2);
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(1, 3, parkingLotOwner);
        parkingLot.addMissingCarObservers(fbiAgent);
        ParkingReciept reciept = parkingLot.park(car);
        Car carReceived = parkingLot.unPark(reciept);
        assertNotNull(carReceived);
        verify(fbiAgent,times(0)).missingVehicleUpdate(parkingLot, reciept);
    }
}