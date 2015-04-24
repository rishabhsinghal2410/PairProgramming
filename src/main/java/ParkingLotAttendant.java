import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by welcome on 23-04-2015.
 */
public class ParkingLotAttendant {

    List<ParkingLot>parkingLots;
    private ParkingLotStrategy parkingLotStrategy;

    public ParkingLotAttendant(List<ParkingLot> parkingLots){
        this.parkingLots = parkingLots;
    }


    private ParkingLot getParkingLotWithSpaceAvailable() throws Exception {
        return parkingLotStrategy.getParkingLotWithSpaceAvailable(parkingLots);
    }

    private ParkingLot getParkingLotForParkingReciept(ParkingReciept parkingReciept) throws Exception{
        for(ParkingLot parkingLot : parkingLots){
            if(parkingLot.getParkingLotId() == parkingReciept.getParkingLotId())
                return parkingLot;
        }
        throw new Exception("Invalid Parking Reciept !!!");
    }

    public ParkingReciept parkTravellersCar(Car car)  throws Exception{
        ParkingLot parkingLot = getParkingLotWithSpaceAvailable();
        return parkingLot.park(car);
    }

    public Car unParkTravellersCar(ParkingReciept parkingReciept) throws Exception{
        if(parkingReciept == null)
            throw new Exception("Parking reciept is not available");
        ParkingLot parkingLot = getParkingLotForParkingReciept(parkingReciept);
        return parkingLot.unPark(parkingReciept);
    }

    public void updateParkingLotStrategy(boolean todayIsFestival) {
        if(todayIsFestival) {
            this.parkingLotStrategy = new FestiveSeasonParkingLotStrategy();
            return;
        }
        this.parkingLotStrategy = new NonFestiveSeasonParkingStrategy();
    }
    
}
