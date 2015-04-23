import java.util.Collections;
import java.util.List;

/**
 * Created by welcome on 23-04-2015.
 */
public class FestiveSeasonParkingLotStrategy implements ParkingLotStrategy {
    @Override
    public ParkingLot getParkingLotWithSpaceAvailable(List<ParkingLot> parkingLots) throws Exception{
        Collections.sort(parkingLots);
        for(ParkingLot parkingLot : parkingLots){
            boolean spaceAvailable = parkingLot.isSpaceAvailable();
            if(spaceAvailable)
                return parkingLot;
        }
        throw new Exception("None of the parking lot has space");
    }
}
