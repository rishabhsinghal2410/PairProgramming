import java.util.List;

public interface ParkingLotStrategy {
    public ParkingLot getParkingLotWithSpaceAvailable(List<ParkingLot> parkingLots,Car car) throws Exception;
}
