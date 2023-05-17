package ru.croc.javaschool.homework7;

import ru.croc.javaschool.homework7.dsprovider.DerbyDataSourceProvider;
import ru.croc.javaschool.homework7.model.Transport;
import ru.croc.javaschool.homework7.property.PropertyContainer;
import ru.croc.javaschool.homework7.repository.implementation.DerbyTransportRepository;
import ru.croc.javaschool.homework7.service.TransportService;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс для запуска приложения, работающего с базой данных транспорта.
 *
 * @author Artem Isaev
 */
public class Application {
    /**
     * Списки для получения результатов.
     */
    private List<Transport> allTransport, allBuses, allTrolleys, routeTransport, timeTransport,
            timeNotFoundTransport, routeNotFoundTransport, afterClearTable;
    /**
     * Запускает приложение.
     *
     * @param pathBus     путь к файлу с информацией об автобусах.
     * @param pathTrolley путь к файлу с информацией о троллейбусах.
     * @return true, если приложение было успешно запущено
     */
    public boolean start(Path pathBus, Path pathTrolley) {
        try {
            PropertyContainer.loadProperties();
            var dataSourceProvider = new DerbyDataSourceProvider();
            var transportRepository = new DerbyTransportRepository(
                    dataSourceProvider.getDataSource());
            var transportService = new TransportService(transportRepository);
            transportService.addTransportToDB(pathBus, pathTrolley);
            allTransport = transportService.findAllTransport();
            allBuses = transportService.findAllBuses();
            allTrolleys = transportService.findAllTrolleys();
            routeTransport = transportService.findByRoute(101);
            timeTransport = transportService.findByTime(
                    LocalDateTime.of(2023, 5, 13, 15, 46, 00));
            timeNotFoundTransport = transportService.findByTime(LocalDateTime.now());
            routeNotFoundTransport = transportService.findByRoute(999);
            transportService.clearTable();
            afterClearTable = transportService.findAllTransport();
            return true; // успех
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Весь транспорт.
     * @return весь транспорт.
     */
    public List<Transport> getAllTransport() {
        return allTransport;
    }

    /**
     * Все автобусы.
     * @return все автобусы.
     */
    public List<Transport> getAllBuses() {
        return allBuses;
    }

    /**
     * Все троллейбусы.
     * @return все троллейбусы.
     */
    public List<Transport> getAllTrolleys() {
        return allTrolleys;
    }

    /**
     * Транспорт по указанному маршруту.
     * @return транспорт по указанному маршруту.
     */
    public List<Transport> getRouteTransport() {
        return routeTransport;
    }

    /**
     * Транспорт по указанному времени.
     * @return транспорт по указанному времени.
     */
    public List<Transport> getTimeTransport() {
        return timeTransport;
    }

    /**
     * Транспорт по указанному времени (не найдено).
     * @return транспорт по указанному времени (не найдено).
     */
    public List<Transport> getTimeNotFoundTransport() {
        return timeNotFoundTransport;
    }

    /**
     * Транспорт по указанному маршруту (не найдено)
     * @return транспорт по указанному маршруту (не найдено).
     */
    public List<Transport> getRouteNotFoundTransport() {
        return routeNotFoundTransport;
    }

    /**
     * Результат очистки таблицы (должен быть пуст).
     * @return результат очистки таблицы.
     */
    public List<Transport> getAfterClearTable() {
        return afterClearTable;
    }
}
