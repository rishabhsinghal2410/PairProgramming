import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by welcome on 22-04-2015.
 */
public class ParkingLotOwner implements Observer{

    

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Boolean) {
            Boolean isParkingFull = (Boolean) arg;
            notifyParkingLotFull(isParkingFull);
            notifyParkingSpaceAvailable(isParkingFull);
        }

    }

    public void informParkingLotFull() {
    }

    private void notifyParkingLotFull(Boolean isParkingFull) {
        if(isParkingFull)
        System.out.println("Parking Lot is Full");
    }

    private void notifyParkingSpaceAvailable(Boolean isParkingFull) {
        if(!isParkingFull)
        System.out.println("Parking Lot available");
    }
}
