import java.util.HashMap;
import java.util.Map;

/**
 * Created by welcome on 21-04-2015.
 */
public class ParkingLot {

    private final int parkingLotSize ;
    private int parkingId;
    private int currentParkingOccupied;
    private Map<Integer, Car> carParked = new HashMap<Integer, Car>();

    public ParkingLot(int parkingLotSize)
    {
        this.parkingLotSize=parkingLotSize;
    }

    public int park( Car car)
    {
           if(isParkingLotSpaceAvailable()){
               currentParkingOccupied = currentParkingOccupied +1;
               carParked.put(++parkingId, car);
               return parkingId;
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
