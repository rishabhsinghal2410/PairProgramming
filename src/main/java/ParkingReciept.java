public class ParkingReciept {
    private int parkingLotId;
    private int vehicleId;

    public ParkingReciept(int parkingLotId, int vehicleId) {
        this.parkingLotId = parkingLotId;
        this.vehicleId = vehicleId;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public int getVehicleId() {
        return vehicleId;
    }
}
