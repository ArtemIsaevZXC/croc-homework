package parking.action;

import parking.entities.Car;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Попытка въезда.
 *
 * @author Artem Isaev
 */
public class EntryAttempt {
    /**
     * Время, когда автомобиль пытался попасть на парковку.
     */
    private LocalDateTime time;
    /**
     * Номер автомобиля, который пытался попасть на парковку.
     */
    private String carNumber;
    /**
     * Успешность попытки.
     */
    private boolean success;

    /**
     * Создает {@link EntryAttempt}.
     *
     * @param car     Автомобиль, который пытался попасть на парковку.
     * @param success Успешность попытки.
     */
    public EntryAttempt(Car car, boolean success) {
        carNumber = car.getCarNumber();
        time = LocalDateTime.now();
        this.success = success;
    }

    /**
     * Успешность попытки.
     *
     * @return успешность попытки.
     */
    public boolean isSuccessful() {
        return success;
    }

    /**
     * Выводит информацию о времени и автомобиле, который не смог заехать на парковку.
     */
    public void printInfo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy в HH:mm:ss");
        System.out.println("Автомобиль с номером " + carNumber
                + " не смог заехать на парковку " + dtf.format(time));
    }

}
