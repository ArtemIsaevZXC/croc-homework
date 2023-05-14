package ru.croc.javaschool.homework7.repository;

import ru.croc.javaschool.homework7.model.Bus;
import ru.croc.javaschool.homework7.model.Transport;
import ru.croc.javaschool.homework7.model.Trolleybus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс репозитория общественного транспорта.
 *
 * @author Artem Isaev
 */
public interface TransportRepository {
    /**
     * Метод инициализации таблицы.
     */
    void initTable();

    /**
     * Метод создания новой записи об общественном транспорте.
     * @param transport общественный транспорт
     */
    void create(Transport transport);

    /**
     * Метод получения всех записей из таблицы.
     * @return список всех записей в таблице.
     */
    List<Transport> findAll();

    /**
     * Метод получения всех автобусов из таблицы.
     * @return список всех автобусов из таблицы.
     */
    List<Bus> findAllBuses();

    /**
     * Метод получения всех троллейбусов из таблицы.
     * @return список всех троллейбусов из таблицы.
     */
    List<Trolleybus> findAllTrolleys();

    /**
     * Возвращает список общественного транспорта по указанному маршруту.
     * @param routeToFindWith указанный маршрут.
     * @return список общественного транспорта.
     */
    List<Transport> findByRoute(int routeToFindWith);

    /**
     * Возвращает список общественного транспорта по указанному времени.
     * @param timeToFindWith указанное время.
     * @return список общественного транспорта.
     */
    List<Transport> findByTime(LocalDateTime timeToFindWith);
}
