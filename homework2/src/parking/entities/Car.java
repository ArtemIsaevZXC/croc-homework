package parking.entities;

/**
 * Машина.
 *
 * @author Artem Isaev
 */
public class Car {
    /**
     * Номер автомобиля.
     */
    private String carNumber;

    /**
     * Создаёт {@link Car}.
     *
     * @param carNumber Номер автомобиля.
     */
    public Car(String carNumber) {
        this.carNumber = carNumber;
    }

    /**
     * Номер автомобиля.
     *
     * @return номер автомобиля.
     */
    public String getCarNumber() {
        return carNumber;
    }
}
