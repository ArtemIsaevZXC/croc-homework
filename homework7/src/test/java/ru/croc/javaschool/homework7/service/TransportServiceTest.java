package ru.croc.javaschool.homework7.service;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework7.model.Bus;
import ru.croc.javaschool.homework7.model.Trolleybus;
import ru.croc.javaschool.homework7.repository.TransportRepository;
import ru.croc.javaschool.homework7.repository.implementation.DerbyTransportRepository;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;

/**
 * Тест {@link TransportService}.
 */
public class TransportServiceTest {
    /**
     * Репозиторий общественного транспорта.
     */
    private static TransportRepository transportRepository;
    /**
     * Экземпляр сервисного класса для работы с транспортом.
     */
    private static TransportService transportService;
    /**
     * Источник данных.
     */
    private static DataSource dataSource;

    /**
     * Создается тестовый источник данных и репозиторий.
     */
    @BeforeEach
    void setUp() {
        dataSource = createTestDataSource();
        transportRepository = new DerbyTransportRepository(dataSource);
        transportService = new TransportService(transportRepository);
    }

    /**
     * Создает источник данных для тестирования.
     *
     * @return источник данных для тестирования.
     */
    private static DataSource createTestDataSource() {
        String dbName = "transport_db_test";
        return new EmbeddedDataSource() {{
            setDatabaseName(dbName);
            setCreateDatabase("create");
        }};
    }

    /**
     * Тест {@link TransportService#addTransportToDB(Path, Path)}, который проверяет,
     * что транспорт добавлен в базу данных.
     *
     * @throws IOException если произошла ошибка ввода-вывода
     */
    @Test
    public void addTransportToDBTest() throws IOException {
        Path busXML = Paths.get("src/test/resources/buses.xml");
        Path trolleybusXML = Paths.get("src/test/resources/trolleys.xml");
        transportService.addTransportToDB(busXML, trolleybusXML);
        Assertions.assertFalse(transportRepository.findAll().isEmpty());
    }

    /**
     * Тест {@link TransportService#findAll(String)}, который проверяет, что возвращаемый список транспорта не пустой.
     */
    @Test
    public void findAllTest() {
        Bus bus = new Bus(10, 1050.0022, 1051.0022, 50, LocalDateTime.now());
        Trolleybus trolleybus = new Trolleybus(14, 1024, 1024, 60, LocalDateTime.now());

        transportRepository.create(bus);
        transportRepository.create(trolleybus);

        Assertions.assertTrue(transportService.findAll("TRANSPORT"));

        Assertions.assertTrue(transportService.findAll("BUS"));

        Assertions.assertTrue(transportService.findAll("TROLLEYBUS"));

        Assertions.assertFalse(transportService.findAll("TRAIN"));
    }

    /**
     * Тест {@link TransportService#findByTime(LocalDateTime)}, который проверяет,
     * что возвращаемый список транспорта соответствует времени поиска.
     */
    @Test
    public void findByTimeTest() {
        final int ROUTE_NUMBER_1 = 50;
        final double LATITUDE_1 = 160.1111;
        final double LONGITUDE_1 = 120.1111;
        final int SPEED_1 = 50;
        var bus1 = new Bus(
                ROUTE_NUMBER_1,
                LATITUDE_1,
                LONGITUDE_1,
                SPEED_1,
                LocalDateTime.of(2023, 5, 13, 15, 45, 00)
        );
        transportRepository.create(bus1);

        final int ROUTE_NUMBER_2 = 51;
        final double LATITUDE_2 = 61.1111;
        final double LONGITUDE_2 = 121.1111;
        final int SPEED_2 = 51;
        var bus2 = new Bus(
                ROUTE_NUMBER_2,
                LATITUDE_2,
                LONGITUDE_2,
                SPEED_2,
                LocalDateTime.of(2023, 5, 14, 10, 30, 00)
        );
        transportRepository.create(bus2);

        final int ROUTE_NUMBER_3 = 52;
        final double LATITUDE_3 = 62.1111;
        final double LONGITUDE_3 = 122.1111;
        final int SPEED_3 = 52;
        var trolleybus = new Trolleybus(
                ROUTE_NUMBER_3,
                LATITUDE_3,
                LONGITUDE_3,
                SPEED_3,
                LocalDateTime.of(2023, 5, 13, 16, 0, 0)
        );
        transportRepository.create(trolleybus);

        LocalDateTime searchTime = LocalDateTime.of(2023, 5, 13, 15, 45, 00);

        Assertions.assertTrue(transportService.findByTime(searchTime));
    }

    /**
     * Тест {@link TransportService#findByRoute(int)}, который проверяет,
     * что возвращаемый список транспорта соответствует указанному маршруту.
     */
    @Test
    public void findByRouteTest() {
        final int ROUTE_NUMBER_1 = 50;
        final double LATITUDE_1 = 160.1111;
        final double LONGITUDE_1 = 120.1111;
        final int SPEED_1 = 50;
        var bus = new Bus(
                ROUTE_NUMBER_1,
                LATITUDE_1,
                LONGITUDE_1,
                SPEED_1,
                LocalDateTime.of(2023, 5, 13, 15, 45, 00)
        );
        transportRepository.create(bus);

        final int ROUTE_NUMBER_2 = 51;
        final double LATITUDE_2 = 61.1111;
        final double LONGITUDE_2 = 121.1111;
        final int SPEED_2 = 51;
        var trolleybus = new Trolleybus(
                ROUTE_NUMBER_2,
                LATITUDE_2,
                LONGITUDE_2,
                SPEED_2,
                LocalDateTime.of(2023, 5, 13, 16, 45, 00)
        );
        transportRepository.create(trolleybus);

        Assertions.assertTrue(transportService.findByRoute(ROUTE_NUMBER_1));

        Assertions.assertTrue(transportService.findByRoute(ROUTE_NUMBER_2));

        Assertions.assertFalse(transportService.findByRoute(999));
    }

}
