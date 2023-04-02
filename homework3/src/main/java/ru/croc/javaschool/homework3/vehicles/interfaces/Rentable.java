package ru.croc.javaschool.homework3.vehicles.interfaces;

import java.time.LocalDate;

/**
 * Арендуемый.
 *
 * @author Artem Isaev
 */
public interface Rentable {

    /**
     * Делает ТС арендованным.
     *
     * @param start дата начала аренды.
     * @param end   дата окончания аренды.
     */
    void setRented(LocalDate start, LocalDate end);

}
