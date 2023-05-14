package ru.croc.javaschool.homework7.service;

import ru.croc.javaschool.homework7.model.Bus;
import ru.croc.javaschool.homework7.model.Transport;
import ru.croc.javaschool.homework7.model.Trolleybus;
import ru.croc.javaschool.homework7.model.unmarshalling.Buses;
import ru.croc.javaschool.homework7.model.unmarshalling.Trolleybuses;
import ru.croc.javaschool.homework7.repository.TransportRepository;
import ru.croc.javaschool.homework7.xmltools.XmlConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

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
     * @throws IOException если возникла ошибка чтения файлов.
     */
    public void addTransportToDB(Path busXML, Path trolleybusXML) throws IOException {
        XmlConverter xmlConverter = new XmlConverter();
        Buses buses = xmlConverter.fromXml(Files.readString(busXML), Buses.class);
        Trolleybuses trolleybuses = xmlConverter.fromXml(Files.readString(trolleybusXML), Trolleybuses.class);
        List<Bus> busList = buses.getBuses();
        List<Trolleybus> trolleybusList = trolleybuses.getTrolleybuses();
        busList.forEach(transportRepository::create);
        trolleybusList.forEach(transportRepository::create);
    }

    /**
     * Поиск транспорта в базе данных по типу.
     *
     * @param type тип транспорта (TRANSPORT, BUS, TROLLEYBUS).
     * @return true, если транспорт найден; false, если не найден или указан неверный тип.
     */
    public boolean findAll(String type) {
        switch (type) {
            case "TRANSPORT": {
                List<Transport> transportList = transportRepository.findAll();
                transportList.forEach(transport -> System.out.println(transport.toString()));
                return true;
            }
            case "BUS": {
                List<Bus> busList = transportRepository.findAllBuses();
                busList.forEach(bus -> System.out.println(bus.toString()));
                return true;
            }
            case "TROLLEYBUS": {
                List<Trolleybus> trolleybusList = transportRepository.findAllTrolleys();
                trolleybusList.forEach(trolleybus -> System.out.println(trolleybus.toString()));
                return true;
            }
            default: {
                System.out.println("Укажите верно тип транспорта (TRANSPORT, BUS, TROLLEYBUS)");
                return false;
            }
        }
    }

    /**
     * Поиск транспорта в базе данных по времени.
     *
     * @param time время для поиска транспорта.
     * @return true, если транспорт найден; false, если не найден.
     */
    public boolean findByTime(LocalDateTime time) {
        List<Transport> transportList = transportRepository.findByTime(time);
        if (transportList.isEmpty()) {
            System.out.println("Транспорт на указанное время не найден");
            return false;
        }
        transportList.forEach(transport -> System.out.println(transport.toString()));
        return true;
    }

    /**
     * Поиск транспорта в базе данных по маршруту.
     *
     * @param route маршрут для поиска транспорта.
     * @return true, если транспорт найден
     */
    public boolean findByRoute(int route) {
        List<Transport> transportList = transportRepository.findByRoute(route);
        if (transportList.isEmpty()) {
            System.out.println("Транспорт по указанному маршруту не найден");
            return false;
        }
        transportList.forEach(transport -> System.out.println(transport.toString()));
        return true;
    }
}
