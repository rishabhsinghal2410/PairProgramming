import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class FestiveSeasonParkingLotStrategyTest {

    @Test
    public void shouldFindParkingLotWhenThereIsOneParkinglot() throws Exception {

        FestiveSeasonParkingLotStrategy festiveSeasonParkingLotStrategy = new FestiveSeasonParkingLotStrategy();
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(new ParkingLot(1,2,new ParkingLotOwner()));
        ParkingLot parkingLot = festiveSeasonParkingLotStrategy.getParkingLotWithSpaceAvailable(parkingLotList);
        assertNotNull(parkingLot);
    }

    @Test
    public void shouldFindParkingLotWithMaximumSizeWhenParkingSpaceIsAvailable() throws Exception{
        FestiveSeasonParkingLotStrategy festiveSeasonParkingLotStrategy = new FestiveSeasonParkingLotStrategy();
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotList.add(new ParkingLot(1,2,owner));
        parkingLotList.add(new ParkingLot(2,3,owner));
        parkingLotList.add(new ParkingLot(3,5,owner));

        ParkingLot parkingLot = festiveSeasonParkingLotStrategy.getParkingLotWithSpaceAvailable(parkingLotList);
        assertThat(parkingLot.getParkingLotId(), is(3));

    }

    @Test
    public void shouldNotFindParkingSpaceWhenAllParkingLotsAreFull() throws Exception{
        String errMsg = null;
        FestiveSeasonParkingLotStrategy festiveSeasonParkingLotStrategy = new FestiveSeasonParkingLotStrategy();
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        ParkingLot parkingLot = mock(ParkingLot.class);

        parkingLotList.add(parkingLot);

        when(parkingLot.isSpaceAvailable()).thenReturn(false);
        try {
            festiveSeasonParkingLotStrategy.getParkingLotWithSpaceAvailable(parkingLotList);
        }catch(Exception e){
            errMsg = e.getMessage();
        }

        assertEquals(errMsg,"None of the parking lot has space");
    }
}
