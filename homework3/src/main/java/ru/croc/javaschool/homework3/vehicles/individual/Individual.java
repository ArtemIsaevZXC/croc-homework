package ru.croc.javaschool.homework3.vehicles.individual;

import ru.croc.javaschool.homework3.vehicles.vehicle.Vehicle;

/**
 * Средство индивидуальной мобильности.
 * <p>
 * Замечание: содержит косметические поля и методы,
 * которые никак не участвуют в функционале программы.
 *
 * @author Artem Isaev
 */
public abstract class Individual extends Vehicle {
    /**
     * Цвет.
     */
    private String color;

    /**
     * Конструктор, необходимый для наследуемых классов.
     *
     * @param id идентификатор ТС.
     */
    public Individual(int id) {
        super(id);
    }

    /**
     * Цвет.
     *
     * @return цвет.
     */
    public String getColor() {
        return color;
    }

    /**
     * Устанавливает цвет.
     *
     * @param color цвет.
     */
    public void setColor(String color) {
        this.color = color;
    }
}
