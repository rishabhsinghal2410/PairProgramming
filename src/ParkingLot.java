import java.util.HashMap;
import java.util.Map;

/**
 * Created by welcome on 21-04-2015.
 */
public class ParkingLot {

    private final int parkingLotSize = 3;
    private int currentParkingOccupied = 0;
    private static ParkingLot parkingLot = null;
    private Map<Integer, Car> carParked = new HashMap<Integer, Car>();
    public static ParkingLot getParkingLotInstance()
    {
        if(parkingLot == null)
        {
            parkingLot = new ParkingLot();
        }
        return parkingLot;
    }

    private ParkingLot()
    {
    }

    public int park( Car car)
    {
           if(isParkingLotSpaceAvailable()){
               currentParkingOccupied = currentParkingOccupied +1;
               carParked.put(currentParkingOccupied, car);
               return currentParkingOccupied;
           }
        return 0;
    }

    private boolean isParkingLotSpaceAvailable()
    {
        if(currentParkingOccupied < parkingLotSize)
            return true;
        return false;
    }

    public Car unPark(int parkingId){
        currentParkingOccupied--;
        return carParked.get(parkingId);
    }

}
