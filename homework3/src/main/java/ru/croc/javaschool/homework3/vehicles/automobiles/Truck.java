package ru.croc.javaschool.homework3.vehicles.automobiles;

/**
 * Грузовик.
 * <p>
 * Замечание: содержит косметические поля и методы,
 * которые никак не участвуют в функционале программы.
 *
 * @author Artem Isaev
 */
public class Truck extends Automobile {
    /**
     * Вместимость грузовика.
     */
    private int capacity;

    /**
     * Создает {@link Truck}.
     *
     * @param id идентификатор ТС.
     */
    public Truck(int id) {
        super(id);
    }

    /**
     * Вместимость грузовика.
     *
     * @return вместимость грузовика.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Устанавливает значение вместимости грузовика.
     *
     * @param capacity вместимость грузовика.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
