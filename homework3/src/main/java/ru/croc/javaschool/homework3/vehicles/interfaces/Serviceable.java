package ru.croc.javaschool.homework3.vehicles.interfaces;

/**
 * Пригодный к эксплуатации.
 *
 * @author Artem Isaev
 */
public interface Serviceable {
    /**
     * Метод, реализующий проверку ТС на пригодность к эксплуатации.
     */
    boolean isServiceable();
    /**
     * Делает транспортное средство неисправным.
     */
    void notServiceable();
}
