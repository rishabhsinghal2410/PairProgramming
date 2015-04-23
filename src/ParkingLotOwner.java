import java.util.Observable;
import java.util.Observer;

/**
 * Created by welcome on 22-04-2015.
 */
public class ParkingLotOwner implements Observer{

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
}
