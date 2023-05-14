package ru.croc.javaschool.homework7;

import ru.croc.javaschool.homework7.dsprovider.DerbyDataSourceProvider;
import ru.croc.javaschool.homework7.property.PropertyContainer;
import ru.croc.javaschool.homework7.repository.implementation.DerbyTransportRepository;
import ru.croc.javaschool.homework7.service.TransportService;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

/**
 * Класс для запуска приложения, работающего с базой данных транспорта.
 *
 * @author Artem Isaev
 */
public class Application {
    /**
     * Запускает приложение.
     *
     * @param pathBus     путь к файлу с информацией об автобусах.
     * @param pathTrolley путь к файлу с информацией о троллейбусах.
     * @return true, если приложение было успешно запущено, иначе false.
     */
    public static boolean start(Path pathBus, Path pathTrolley) {
        try {
            PropertyContainer.loadProperties();
            var dataSourceProvider = new DerbyDataSourceProvider();
            var transportRepository = new DerbyTransportRepository(
                    dataSourceProvider.getDataSource());
            var transportService = new TransportService(transportRepository);
            transportService.addTransportToDB(pathBus, pathTrolley);
            System.out.println("ИНФОРМАЦИЯ ОБО ВСЕМ ТРАНСПОРТЕ");
            transportService.findAll("TRANSPORT");
            System.out.println("ИНФОРМАЦИЯ ОБ АВТОБУСАХ");
            transportService.findAll("BUS");
            System.out.println("ИНФОРМАЦИЯ О ТРОЛЛЕЙБУСАХ");
            transportService.findAll("TROLLEYBUS");
            System.out.println("ПОИСК ПО МАРШРУТУ");
            transportService.findByRoute(101);
            System.out.println("ПОИСК ПО ВРЕМЕНИ");
            transportService.findByTime(LocalDateTime.of(2023, 5, 13, 15, 46, 00));
            System.out.println("ПОИСК ПО ВРЕМЕНИ");
            transportService.findByTime(LocalDateTime.now());
            System.out.println("ПОИСК ПО МАРШРУТУ");
            transportService.findByRoute(999);
            return true; // успех
        } catch (IOException e) {
            return false; // неудача
        }
    }
}
