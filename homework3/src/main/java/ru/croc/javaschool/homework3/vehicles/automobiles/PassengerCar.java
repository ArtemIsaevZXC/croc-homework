package ru.croc.javaschool.homework3.vehicles.automobiles;

/**
 * Легковая машина.
 * <p>
 * Замечание: содержит косметические поля и методы,
 * которые никак не участвуют в функционале программы.
 *
 * @author Artem Isaev
 */
public class PassengerCar extends Automobile {
    /**
     * Количество пассажирских мест.
     */
    private int passengerSeats;

    /**
     * Создает {@link PassengerCar}.
     *
     * @param id идентификатор ТС.
     */
    public PassengerCar(int id) {
        super(id);
    }

    /**
     * Количество пассажирских мест.
     *
     * @return количество пассажирских мест.
     */
    public int getPassengerSeats() {
        return passengerSeats;
    }

    /**
     * Устанавливает количество пассажирских мест.
     *
     * @param passengerSeats количество пассажирских мест.
     */
    public void setPassengerSeats(int passengerSeats) {
        this.passengerSeats = passengerSeats;
    }
}
