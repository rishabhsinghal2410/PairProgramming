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

    // if parking space is available will park a car
    // if parking space is not available it will say not available by returning 0
    public int park( Car car) throws Exception
    {
           if(isParkingLotSpaceAvailable()){
               currentParkingOccupied = currentParkingOccupied +1;
               carParked.put(++parkingId, car);
               return parkingId;
           }
        throw new Exception("Parking not done");
    }

    public boolean isParkingLotSpaceAvailable()
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
