import java.util.Observable;

/**
 * Created by welcome on 24-04-2015.
 */
public interface MissingVehicleObserver {

    public void missingVehicleUpdate(Observable parkingLot, Object missingVehicleTicket);
}
