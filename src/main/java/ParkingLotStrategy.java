import java.util.List;

/**
 * Created by welcome on 23-04-2015.
 */
public interface ParkingLotStrategy {
    public ParkingLot getParkingLotWithSpaceAvailable(List<ParkingLot> parkingLots) throws Exception;
}
