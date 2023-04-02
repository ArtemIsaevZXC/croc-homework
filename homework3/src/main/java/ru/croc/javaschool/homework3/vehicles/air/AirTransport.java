package ru.croc.javaschool.homework3.vehicles.air;

import ru.croc.javaschool.homework3.vehicles.vehicle.Vehicle;

/**
 * Летательное ТС.
 * <p>
 * Замечание: содержит косметические поля и методы,
 * которые никак не участвуют в функционале программы.
 *
 * @author Artem Isaev
 */
public abstract class AirTransport extends Vehicle {
    /**
     * Максимальная высота полёта.
     */
    private int maxHeight;

    /**
     * Конструктор, необходимый для наследуемых классов.
     *
     * @param id идентификатор ТС.
     */
    public AirTransport(int id) {
        super(id);
    }

    /**
     * Максимальная высота полёта.
     *
     * @return максимальная высота полёта.
     */
    public int getMaxHeight() {
        return maxHeight;
    }

    /**
     * Устанавливает максимальную высоту полёта.
     *
     * @param maxHeight максимальная высота полёта.
     */
    public void setMaxHeight(int maxHeight) {
        if (maxHeight <= 0) {
            this.maxHeight = 500;
        } else {
            this.maxHeight = maxHeight;
        }
    }
}
