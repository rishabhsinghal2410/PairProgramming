import java.util.*;

public class ParkingLot extends Observable implements Comparable {

    private final int parkingLotSize;
    private int parkingLotId;
    private int currentParkingOccupied;
    private ParkingLotOwner parkingLotOwner;
    private Map<Integer, Car> carParked = new HashMap<Integer, Car>();
    private List<Observer> parkingLotObservers = new ArrayList<Observer>();
    private List<Observer> missingCarObservers = new ArrayList<Observer>();
    private boolean notifiedObservers = false;
    public ParkingLot(int parkingLotId, int parkingLotSize, ParkingLotOwner parkingLotOwner) {
        this.parkingLotId = parkingLotId;
        this.parkingLotSize = parkingLotSize;
        this.parkingLotOwner = parkingLotOwner;
        addParkingLotToParkingOwnerList();
        attach(parkingLotOwner);
    }

    public int getParkingLotSize() {
        return parkingLotSize;
    }

    public int getCurrentParkingOccupied() {
        return currentParkingOccupied;
    }

    private void attach(Observer observer) {
        addObserver(observer);
    }

    private void addParkingLotToParkingOwnerList() {
        this.parkingLotOwner.addParkingLot(this);
    }

    public ParkingReciept park(Car car) throws Exception {
        if (isParkingLotSpaceAvailable()) {
            currentParkingOccupied = currentParkingOccupied + 1;
            carParked.put(car.getCarId(), car);
            check80percentFullAndNotify();
            return new ParkingReciept(parkingLotId, car.getCarId());
        }
        setChanged();
        notifyObservers(true);
        throw new Exception("Parking not done");
    }

    private void check80percentFullAndNotify() {
        double percentOccupied = ((double) currentParkingOccupied) / ((double) parkingLotSize);
        if (percentOccupied >= 0.8) {
            notifiedObservers = true;
            notifyParkingLotObservers(Boolean.TRUE, parkingLotObservers);
        }
    }

    private void checkLessThan80percentFullAndNotify() {
        double percentOccupied = ((double) currentParkingOccupied) / ((double) parkingLotSize);
        if (percentOccupied < 0.8 && notifiedObservers) {
            notifiedObservers = false;
            notifyParkingLotObservers(Boolean.FALSE,parkingLotObservers);
        }
    }

    private boolean isParkingLotSpaceAvailable() {
        if (currentParkingOccupied < parkingLotSize)
            return true;

        return false;
    }

    public Car unPark(ParkingReciept parkingReciept) {
        if (parkingReciept == null)
            return null;
        currentParkingOccupied--;
        checkLessThan80percentFullAndNotify();
        setChanged();
        notifyObservers(false);
        Car car = carParked.get(parkingReciept.getVehicleId());
        informPoliceDepartmentOfMissingCar(parkingReciept, car);
        return  car;
    }

    public void addParkingLotObservers(Observer observer) {
        parkingLotObservers.add(observer);
    }

    public void addMissingCarObservers(Observer observer){
        missingCarObservers.add(observer);
    }

    public void notifyParkingLotObservers(Object valueToSend, List<Observer> parkingLotObservers){
        for(Observer observers : parkingLotObservers){
            observers.update(this, valueToSend);
        }
    }

    public boolean isSpaceAvailable() {
        int spaceAvailable = this.parkingLotSize - this.currentParkingOccupied;
        if (spaceAvailable > 0)
            return true;
        return false;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    private void informPoliceDepartmentOfMissingCar( ParkingReciept parkingReciept, Car car){
        if(car == null)
            notifyParkingLotObservers(parkingReciept, missingCarObservers);
    }

    @Override
    public int compareTo(Object parkingLotObject) {
        ParkingLot parkingLot = (ParkingLot) parkingLotObject;
        return parkingLot.parkingLotSize - this.parkingLotSize;
    }
}
