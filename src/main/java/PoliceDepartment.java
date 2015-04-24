import java.util.Observable;
import java.util.Observer;

/**
 * Created by welcome on 24-04-2015.
 */
public class PoliceDepartment implements MissingVehicleObserver{

    public String getReportMessage() {
        return reportMessage;
    }

    private String reportMessage;

    @Override
    public void missingVehicleUpdate(Observable parkingLot, Object carStolenReciept) {
        if(carStolenReciept instanceof ParkingReciept){
            sendOutAPB((ParkingReciept)carStolenReciept);
        }
    }

    public String sendOutAPB(ParkingReciept parkingReciept){
        reportMessage = "Car missing with car id :" + parkingReciept.getVehicleId();
        return reportMessage;
    }
}
