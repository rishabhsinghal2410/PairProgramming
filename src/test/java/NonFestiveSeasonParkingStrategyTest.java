import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Hemanta on 24-04-2015.
 */
public class NonFestiveSeasonParkingStrategyTest {

    @Test
    public void shouldFindParkingLotWhenThereIsOneParkinglot() throws Exception {

        NonFestiveSeasonParkingStrategy nonFestiveSeasonParkingStrategy = new NonFestiveSeasonParkingStrategy();
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(new ParkingLot(1,1,new ParkingLotOwner()));

        ParkingLot parkingLot = nonFestiveSeasonParkingStrategy.getParkingLotWithSpaceAvailable(parkingLotList);
        assertTrue(parkingLot.isSpaceAvailable());
    }



}