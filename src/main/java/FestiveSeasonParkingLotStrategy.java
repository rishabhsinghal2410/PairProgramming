import java.util.Collections;
import java.util.List;

public class FestiveSeasonParkingLotStrategy implements ParkingLotStrategy {
    @Override
    public ParkingLot getParkingLotWithSpaceAvailable(List<ParkingLot> parkingLots,Car car) throws Exception {
        Collections.sort(parkingLots);
        for (ParkingLot parkingLot : parkingLots) {
            boolean spaceAvailable = parkingLot.isSpaceAvailable();
            if (spaceAvailable)
                return parkingLot;
        }
        throw new Exception("None of the parking lot has space");
    }
}
