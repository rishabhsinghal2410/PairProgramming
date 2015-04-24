import java.util.Observable;
import java.util.Observer;

public class FBIAgent implements Observer {
   String parkingLotStatusMessage;
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
}
