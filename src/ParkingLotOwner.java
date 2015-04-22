import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by welcome on 22-04-2015.
 */
public class ParkingLotOwner implements Observer{

    String message;
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Boolean){
            message = getMessage((Boolean)arg);
        }
    }

    public String getMessage(Boolean flag){
        if(flag == Boolean.FALSE)
            return "parking space is available";
        return "parking lot is full";
    }

    public String getMessage(){
        return message;
    }
}
