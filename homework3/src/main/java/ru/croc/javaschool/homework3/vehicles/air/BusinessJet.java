package ru.croc.javaschool.homework3.vehicles.air;

/**
 * Бизнес-джет.
 * <p>
 * Замечание: содержит косметические поля и методы,
 * которые никак не участвуют в функционале программы.
 *
 * @author Artem Isaev
 */
public class BusinessJet extends AirTransport {
    /**
     * Количество пассажирских мест.
     */
    private int passengerCapacity;

    /**
     * Создает {@link BusinessJet}.
     *
     * @param id идентификатор ТС.
     */
    public BusinessJet(int id) {
        super(id);
    }

    /**
     * Количество пассажирских мест.
     *
     * @return количество пассажирских мест.
     */
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    /**
     * Устанавилвает количество пассажирских мест.
     *
     * @param passengerCapacity количество пассажирских мест.
     */
    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}
