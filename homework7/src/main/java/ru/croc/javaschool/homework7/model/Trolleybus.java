package ru.croc.javaschool.homework7.model;

import java.time.LocalDateTime;

/**
 * Троллейбус.
 *
 * @author Artem Isaev
 */
public class Trolleybus extends Transport {
    /**
     * Необходим для JAXB. (?)
     */
    public Trolleybus(){
        super();
    }

    /**
     * Создает {@link Trolleybus}.
     * @param route маршрут.
     * @param latitude ширина.
     * @param longitude долгота.
     * @param speed скорость.
     * @param time время.
     */
    public Trolleybus(int route, double latitude, double longitude, int speed, LocalDateTime time) {
        super(route, latitude, longitude, speed, time);
    }
}
