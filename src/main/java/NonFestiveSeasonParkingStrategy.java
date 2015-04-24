import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NonFestiveSeasonParkingStrategy implements ParkingLotStrategy {
    @Override
    public ParkingLot getParkingLotWithSpaceAvailable(List<ParkingLot> parkingLots,Car car) throws Exception {
        if(CarType.HATCHBACK.equals(car.getCarType())) {
          return getParkingLotForLargeCar(parkingLots);
        }
        for (ParkingLot parkingLot : parkingLots) {
            boolean spaceAvailable = parkingLot.isSpaceAvailable();
            if (spaceAvailable)
                return parkingLot;
        }
        throw new Exception("None of the parking lot has space");
    }
    private ParkingLot getParkingLotForLargeCar(List<ParkingLot> parkingLots) {
        Collections.sort(parkingLots, compareLotsBasedOnMostFreeSpace);

        return parkingLots.get(0);
    }
    private Comparator<ParkingLot> compareLotsBasedOnMostFreeSpace = new Comparator<ParkingLot>() {
        @Override
        public int compare(ParkingLot parkingLot1, ParkingLot parkingLot2) {
            int freeSpaceinLot1 = parkingLot1.getParkingLotSize() - parkingLot1.getCurrentParkingOccupied();
            int freeSpaceinLot2 = parkingLot2.getParkingLotSize() - parkingLot2.getCurrentParkingOccupied();
            return freeSpaceinLot2 - freeSpaceinLot1;
        }
    };
}
