package ru.croc.javaschool.homework3.vehicles.individual;

/**
 * Моноколесо.
 * <p>
 * Замечание: содержит косметические поля и методы,
 * которые никак не участвуют в функционале программы.
 *
 * @author Artem Isaev
 */
public class Unicycle extends Individual {
    /**
     * Максимальная скорость.
     */
    private int maxSpeed = 20;

    /**
     * Создает {@link Unicycle}.
     *
     * @param id идентификатор ТС.
     */
    public Unicycle(int id) {
        super(id);
    }

    /**
     * Устанавливает новое значение максимальной скорости.
     *
     * @param speed максимальная скорость.
     */
    public void setMaxSpeed(int speed) {
        if (speed < 61 && speed > 4) {
            this.maxSpeed = speed;
        } else {
            this.maxSpeed = 20;
        }
    }

    /**
     * Максимальная скорость.
     *
     * @return максимальная скорость.
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }
}
