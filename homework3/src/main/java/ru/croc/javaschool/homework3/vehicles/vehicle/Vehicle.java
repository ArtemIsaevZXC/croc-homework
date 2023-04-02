package ru.croc.javaschool.homework3.vehicles.vehicle;


import ru.croc.javaschool.homework3.vehicles.interfaces.Rentable;
import ru.croc.javaschool.homework3.vehicles.interfaces.Serviceable;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Транспортное средство.
 *
 * @author Artem Isaev
 */
public abstract class Vehicle implements Serviceable, Rentable {
    /**
     * Идентификатор транспортного средства.
     *
     * Замечание: новое ТС изначально имеет свой id ( при создании ), но когда ТС добавляется
     * в общий список транспортных средств из учёта, ему автоматически присваивается новый id в
     * соответствии с другими транспортными средствами, уже находящимися на учёте.
     */
    private int id;
    /**
     * Пригодность транспортного средства к эксплуатации.
     * По умолчанию true, т.к. ТС новое.
     */
    private boolean serviceable = true;
    /**
     * Состояние "арендованности" ТС.
     * По умолчанию false, т.к. ТС изначально не арендовано.
     */
    private boolean rented = false;
    /**
     * Список дат начала аренды ТС.
     */
    private ArrayList<LocalDate> starts = new ArrayList<>();
    /**
     * Список дат конца аренды ТС.
     */
    private ArrayList<LocalDate> ends = new ArrayList<>();

    /**
     * Конструктор, необходимый для наследуемых классов.
     *
     * @param id идентификатор ТС.
     */
    public Vehicle(int id) {
        this.id = id;
    }

    /**
     * Пригодность транспортного средства к эксплуатации.
     *
     * @return пригодность ТС к эксплуатации.
     */
    public boolean isServiceable() {
        return serviceable;
    }

    /**
     * Делает транспортное средство неисправным.
     */
    public void notServiceable() {
        this.serviceable = false;
    }

    /**
     * Делает ТС арендованным.
     *
     * @param start дата начала аренды.
     * @param end   дата окончания аренды.
     */
    public void setRented(LocalDate start, LocalDate end) {
        rented = true;
        starts.add(start);
        ends.add(end);
    }
    /**
     * Идентификатор транспортного средства.
     *
     * @return идентификатор ТС.
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор ТС.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Список с датами начал аренд.
     * @return список дат начала аренды ТС.
     */
    public ArrayList<LocalDate> getStarts() {
        return starts;
    }

    /**
     * Список с датами концов аренд.
     * @return список дат конца аренды ТС.
     */
    public ArrayList<LocalDate> getEnds() {
        return ends;
    }
}
