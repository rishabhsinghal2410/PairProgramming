/**
 * Created by welcome on 21-04-2015.
 */
public class Traveller {

    private ParkingReciept parkingReciept;
    private Car car;

    Traveller(Car car){
        this.car = car;
    }

    public ParkingReciept parkMyCar(ParkingLotAttendant parkingLotAttendant) throws Exception{
        this.parkingReciept = parkingLotAttendant.parkTravellersCar(car);
        car = null;
        return parkingReciept;
    }

    public Car unParkMyCar(ParkingLotAttendant parkingLotAttendant) throws Exception{
        Car car = parkingLotAttendant.unParkTravellersCar(this.parkingReciept);
        this.car = car;
        return car;
    }

}
