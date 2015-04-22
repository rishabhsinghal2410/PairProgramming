import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by welcome on 21-04-2015.
 */
public class ParkingLot extends Observable{

    private final int parkingLotSize ;
    private int parkingId;
    private int currentParkingOccupied;
    private ParkingLotOwner parkingLotOwner;
    private Map<Integer, Car> carParked = new HashMap<Integer, Car>();

    public ParkingLot( int parkingLotSize ,ParkingLotOwner parkingLotOwner)
    {
        this.parkingLotSize=parkingLotSize;
        this.parkingLotOwner = parkingLotOwner;
        attach(parkingLotOwner);
    }

    private void attach(Observer observer){
        addObserver(observer);
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
        setChanged();
        notifyObservers(false);
        throw new Exception("Parking not done");
    }

    private boolean isParkingLotSpaceAvailable()
    {
        if(currentParkingOccupied < parkingLotSize)
            return true;

        return false;
    }

    public Car unPark(int parkingId){
        currentParkingOccupied--;
        setChanged();
        notifyObservers(true);
        return carParked.get(parkingId);
    }

}
