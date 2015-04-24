import java.util.Observable;
import java.util.Observer;

/**
 * Created by welcome on 23-04-2015.
 */
public class FBIAgent implements Observer , MissingVehicleObserver {

    String parkingLotStatusMessage;
    int missingVehicleID;

    @Override
    public void update(Observable parkingLot, Object parkingLotStatus) {
        boolean is80PercentFull=false;
        if(parkingLotStatus instanceof Boolean){
             is80PercentFull=(Boolean)parkingLotStatus;
        }
        setParkingLotStatusMessage(is80PercentFull);
    }

    private void setParkingLotStatusMessage(boolean is80PercentFull) {
        if(is80PercentFull){
            parkingLotStatusMessage="80% Full";
            return;
        }
        parkingLotStatusMessage="Less Than 80%";
    }

    public String getParkingLotStatusMessage() {
        return  parkingLotStatusMessage;
    }

    @Override
    public void missingVehicleUpdate(Observable parkingLot, Object missingVehicleTicket) {
        if(missingVehicleTicket instanceof ParkingReciept){
            missingVehicleID = ((ParkingReciept) missingVehicleTicket).getVehicleId();
            System.out.println("Perfroming paperwork for missing vehicle with ID: "+ missingVehicleID);
        }
    }

    public int getMissingVehicleID() {
        return missingVehicleID;
    }
}
