package ru.croc.javaschool.homework7.service;

import ru.croc.javaschool.homework7.model.Transport;
import ru.croc.javaschool.homework7.model.unmarshalling.Buses;
import ru.croc.javaschool.homework7.model.unmarshalling.Trolleybuses;
import ru.croc.javaschool.homework7.repository.TransportRepository;
import ru.croc.javaschool.homework7.xmltools.XmlConverter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Сервисный класс для работы с транспортом.
 *
 * @author Artem Isaev
 */
public class TransportService {
    /**
     * Репозиторий для работы с транспортом.
     */
    private final TransportRepository transportRepository;

    /**
     * Создает {@link TransportService}.
     *
     * @param transportRepository репозиторий для работы с транспортом.
     */
    public TransportService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    /**
     * Добавляет транспорт в базу данных.
     *
     * @param busXML        путь к XML-файлу с автобусами.
     * @param trolleybusXML путь к XML-файлу с троллейбусами.
     * @return список транспорта, объединяющий данные из обоих файлов.
     */
    public List<Transport> addTransportToDB(Path busXML, Path trolleybusXML) {
        try {
            XmlConverter xmlConverter = new XmlConverter();
            Buses buses = xmlConverter.fromXml(Files.readString(busXML), Buses.class);
            Trolleybuses trolleybuses = xmlConverter.fromXml(Files.readString(trolleybusXML), Trolleybuses.class);
            List<Transport> allTransport = Stream.concat(
                            buses.getBuses().stream(),
                            trolleybuses.getTrolleybuses().stream())
                    .collect(Collectors.toList());
            allTransport.forEach(transportRepository::create);
            return allTransport;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Поиск всех типов транспорта в базе данных.
     *
     * @return список найденного транспорта
     */
    public List<Transport> findAllTransport() {
        return transportRepository.findAll("TRANSPORT");
    }

    /**
     * Поиск всех автобусов в базе данных.
     *
     * @return список найденных автобусов
     */
    public List<Transport> findAllBuses() {
        return transportRepository.findAll("BUS");
    }

    /**
     * Поиск всех троллейбусов в базе данных.
     *
     * @return список найденных троллейбусов
     */
    public List<Transport> findAllTrolleys() {
        return transportRepository.findAll("TROLLEYBUS");
    }

    /**
     * Поиск транспорта в базе данных по времени.
     *
     * @param time время для поиска транспорта.
     * @return список найденного транспорта
     */
    public List<Transport> findByTime(LocalDateTime time) {
        return transportRepository.findByTime(time);
    }

    /**
     * Поиск транспорта в базе данных по маршруту.
     *
     * @param route маршрут для поиска транспорта.
     * @return список найденного транспорта
     */
    public List<Transport> findByRoute(int route) {
        return transportRepository.findByRoute(route);
    }

    /**
     * Метод удаления всех записей из таблицы.
     *
     * @return список удаленного транспорта.
     */
    public List<Transport> clearTable() {
        return transportRepository.clearTable();
    }
}
