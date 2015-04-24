import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ParkingLotOwner implements Observer {

    private final List<ParkingLot> parkingLots = new ArrayList<>();
    private ParkingLotAttendant parkingLotAttendant;

    String message;
    @Override
    public void update(Observable parkingLot, Object parkingLotStatus) {
        if(parkingLotStatus instanceof Boolean){
            message = setParkingLotStatusMessage((Boolean) parkingLotStatus);
        }
    }

    public String setParkingLotStatusMessage(Boolean flag){
        if(flag == Boolean.FALSE)
            return "parking space is available";
        return "parking lot is full";
    }

    public String getMessage(){
        return message;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public List getParkingLotsToBeDelegated(){
        return parkingLots;
    }

    public void assignParkingLotAttendant(ParkingLotAttendant parkingLotAttendant){
        this.parkingLotAttendant = parkingLotAttendant;
    }

    public void informParkingAttendantAboutTodaysFestival(){
        boolean todayIsFestival = true;
        parkingLotAttendant.updateParkingLotStrategy(todayIsFestival);
    }
}
