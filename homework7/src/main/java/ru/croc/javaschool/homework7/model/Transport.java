package ru.croc.javaschool.homework7.model;

import ru.croc.javaschool.homework7.xmltools.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Общественный транспорт.
 *
 * @author Artem Isaev
 */
public abstract class Transport {
    /**
     * Название таблицы для транспорта.
     */
    public static final String TABLE_NAME = "transport_locations";
    /**
     * Номер маршрута.
     */
    @XmlElement(name = "route")
    private int route;
    /**
     * Ширина.
     */
    @XmlElement(name = "latitude")
    private double latitude;
    /**
     * Долгота.
     */
    @XmlElement(name = "longitude")
    private double longitude;
    /**
     * Скорость.
     */
    @XmlElement(name = "speed")
    private int speed;
    /**
     * Время.
     */
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement(name = "datetime")
    private LocalDateTime time;

    /**
     * Конструктор, необходимый для JAXB.
     */
    public Transport(){
    }

    /**
     * Создаёт {@link Transport}.
     * @param route маршрут.
     * @param latitude ширина.
     * @param longitude долгота.
     * @param speed скорость.
     */
    public Transport(int route, double latitude, double longitude, int speed, LocalDateTime time) {
        this.route = route;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.time = time;
    }

    /**
     * Маршрут.
     * @return маршрут.
     */
    public int getRoute() {
        return route;
    }

    /**
     * Ширина.
     * @return ширина.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Долгота.
     * @return долгота.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Скорость.
     * @return скорость.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Время.
     * @return время.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Представление объекта в виде строки.
     * @return представление в виде строки.
     */
    @Override
    public String toString() {
        return String.format("%s{маршрут=%d, ширина=%.4f, долгота=%.4f, скорость=%d, дата-время=%s}",
                this.getClass().getSimpleName(),
                this.getRoute(),
                this.getLatitude(),
                this.getLongitude(),
                this.getSpeed(),
                this.getTime());
    }

    /**
     * Переопределение метода equals.
     *
     * @param o объект, с которым необходимо сравнивать.
     * @return результат сравнения.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transport)) {
            return false;
        }
        Transport transport = (Transport) o;
        return Objects.equals(getRoute(), transport.getRoute()) &&
                Objects.equals(getLatitude(), transport.getLatitude()) &&
                Objects.equals(getLongitude(), transport.getLongitude()) &&
                Objects.equals(getSpeed(), transport.getSpeed()) &&
                Objects.equals(getTime(), transport.getTime());
    }
}
