package ru.croc.javaschool.homework3.vehicles.automobiles;

import ru.croc.javaschool.homework3.vehicles.vehicle.Vehicle;

/**
 * Автомобиль.
 * <p>
 * Замечание: содержит косметические поля и методы,
 * которые никак не участвуют в функционале программы.
 *
 * @author Artem Isaev
 */
public abstract class Automobile extends Vehicle {
    /**
     * Лошадиные силы.
     */
    private int horsePower;
    /**
     * Цвет (placeholder).
     */
    private String color;

    /**
     * Конструктор, необходимый для наследуемых классов.
     *
     * @param id идентификатор ТС.
     */
    protected Automobile(int id) {
        super(id);
    }

    /**
     * Устанавливает значение лошадиной силы.
     *
     * @param horsePower лошадиная сила.
     */
    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    /**
     * Устанавливает цвет.
     *
     * @param color цвет.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Лошадиная сила.
     *
     * @return лошадиная сила.
     */
    public int getHorsePower() {
        return horsePower;
    }

    /**
     * Цвет.
     *
     * @return цвет.
     */
    public String getColor() {
        return color;
    }
}
