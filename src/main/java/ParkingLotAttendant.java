import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ParkingLotAttendant {

    List<ParkingLot> parkingLots;
    private ParkingLotStrategy parkingLotStrategy;


    public ParkingLotAttendant(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    private ParkingLot getParkingLotWithSpaceAvailable(Car car) throws Exception {
        return parkingLotStrategy.getParkingLotWithSpaceAvailable(parkingLots,car);
    }

    private ParkingLot getParkingLotForParkingReciept(ParkingReciept parkingReciept) throws Exception {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getParkingLotId() == parkingReciept.getParkingLotId())
                return parkingLot;
        }
        throw new Exception("Invalid Parking Reciept !!!");
    }

    public ParkingReciept parkTravellersCar(Car car) throws Exception {
        ParkingLot parkingLot = null;
        parkingLot = getParkingLotWithSpaceAvailable(car);
        parkingLot.park(car);
        return createReciept(parkingLot, car);
    }

    private ParkingReciept createReciept(ParkingLot parkingLot, Car car) {
        ParkingReciept parkingReciept = null;
        if (parkingLot != null && car != null) {
            parkingReciept = new ParkingReciept(parkingLot.getParkingLotId(), car.getCarId());
        }
        return parkingReciept;
    }

    public Car unParkTravellersCar(ParkingReciept parkingReciept) throws Exception {
        if (parkingReciept == null)
            throw new Exception("Parking reciept is not available");
        ParkingLot parkingLot = getParkingLotForParkingReciept(parkingReciept);
        Car car = parkingLot.unPark(parkingReciept);
        return car;
    }

    public void updateParkingLotStrategy(boolean todayIsFestival) {
        if (todayIsFestival) {
            this.parkingLotStrategy = new FestiveSeasonParkingLotStrategy();
            return;
        }
        this.parkingLotStrategy = new NonFestiveSeasonParkingStrategy();
    }


}
