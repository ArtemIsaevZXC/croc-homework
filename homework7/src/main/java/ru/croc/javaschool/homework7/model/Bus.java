package ru.croc.javaschool.homework7.model;

import java.time.LocalDateTime;

/**
 * Автобус.
 *
 * @author Artem Isaev
 */
public class Bus extends Transport {
    /**
     * Необходим для JAXB. (?)
     */
    public Bus(){
        super();
    }

    /**
     * Создает {@link Bus}.
     * @param route маршрут.
     * @param latitude ширина.
     * @param longitude долгота.
     * @param speed скорость.
     * @param time время.
     */
    public Bus(int route, double latitude, double longitude, int speed, LocalDateTime time) {
        super(route, latitude, longitude, speed, time);
    }
}
