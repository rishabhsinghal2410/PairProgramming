import java.util.*;

/**
 * Created by welcome on 21-04-2015.
 */
public class ParkingLot extends Observable{

    private final int parkingLotSize ;
    private int parkingId;
    private int currentParkingOccupied;
    private ParkingLotOwner parkingLotOwner;
    private Map<Integer, Car> carParked = new HashMap<Integer, Car>();
    private List<Observer> parkingLotObservers = new ArrayList<>();
    private boolean notifiedObservers = false;

    public ParkingLot( int parkingLotSize ,ParkingLotOwner parkingLotOwner)
    {
        this.parkingLotSize=parkingLotSize;
        this.parkingLotOwner = parkingLotOwner;
        attach(parkingLotOwner);
    }

    private void attach(Observer observer){
        addObserver(observer);
    }

    public int park( Car car) throws Exception
    {
           if(isParkingLotSpaceAvailable()){
               currentParkingOccupied = currentParkingOccupied +1;
               carParked.put(++parkingId, car);
               check80percentFullAndNotify();
               return parkingId;
           }
        setChanged();
        notifyObservers(true);
        throw new Exception("Parking not done");
    }

    private void check80percentFullAndNotify() {
        double percentOccupied = ((double)currentParkingOccupied)/((double)parkingLotSize);
        if(percentOccupied >= 0.8) {
            notifiedObservers = true;
            notifyParkingLotObservers(Boolean.TRUE);
        }
    }

    private void checkLessThan80percentFullAndNotify(){
        double percentOccupied = ((double)currentParkingOccupied)/((double)parkingLotSize);
        if(percentOccupied < 0.8 && notifiedObservers) {
            notifiedObservers = false;
            notifyParkingLotObservers(Boolean.FALSE);
        }
    }

    private boolean isParkingLotSpaceAvailable()
    {
        if(currentParkingOccupied < parkingLotSize)
            return true;

        return false;
    }

    public Car unPark(int parkingId){
        currentParkingOccupied--;
        checkLessThan80percentFullAndNotify();
        setChanged();
        notifyObservers(false);
        return carParked.get(parkingId);
    }

    public void addParkingLotObservers(Observer observer){
        parkingLotObservers.add(observer);
    }

    public void notifyParkingLotObservers(Object valueToSend){
        for(Observer observers : parkingLotObservers){
            observers.update(this, valueToSend);
        }
    }
}
