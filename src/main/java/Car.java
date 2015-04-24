
public class Car {
    private int carId;
    private CarType carType;

    public Car(int carId){
        this.carId = carId;
    }

    public CarType getCarType() {
        return carType;
    }

    public Car(int carId, CarType carType){
        this.carId = carId;
        this.carType = carType;
    }

    public int getCarId() {
        return carId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return getCarId() == car.getCarId();

    }

    @Override
    public int hashCode() {
        return getCarId();
    }
}
