package ru.croc.javaschool.homework7.service;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework7.model.Bus;
import ru.croc.javaschool.homework7.model.Transport;
import ru.croc.javaschool.homework7.model.Trolleybus;
import ru.croc.javaschool.homework7.repository.TransportRepository;
import ru.croc.javaschool.homework7.repository.implementation.DerbyTransportRepository;

import javax.sql.DataSource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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
     */
    @Test
    public void addTransportToDBTest() {
        Path busXML = Paths.get("src/test/resources/buses.xml");
        Path trolleybusXML = Paths.get("src/test/resources/trolleys.xml");
        List<Transport> result = transportService.addTransportToDB(busXML, trolleybusXML);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(result, transportService.findAllTransport());
        Assertions.assertThrows(RuntimeException.class, () -> {
            transportService.addTransportToDB(busXML, trolleybusXML);
        });
    }

    /**
     * Тест {@link TransportService#findAllTransport()}, который возвращает список всего транспорта.
     */
    @Test
    public void findAllTransportTest() {
        Bus bus1 = new Bus(1, 60.1111, 120.1111, 50, LocalDateTime.now());
        Bus bus2 = new Bus(2, 61.1111, 121.1111, 60, LocalDateTime.now());
        Trolleybus trolleybus1 = new Trolleybus(3, 62.1111, 122.1111, 70, LocalDateTime.now());
        Trolleybus trolleybus2 = new Trolleybus(4, 63.1111, 123.1111, 80, LocalDateTime.now());

        transportRepository.create(bus1);
        transportRepository.create(bus2);
        transportRepository.create(trolleybus1);
        transportRepository.create(trolleybus2);

        List<Transport> allTransport = transportService.findAllTransport();
        Assertions.assertEquals(List.of(bus1, bus2, trolleybus1, trolleybus2), allTransport);

        transportService.clearTable();
        allTransport = transportService.findAllTransport();
        Assertions.assertTrue(allTransport.isEmpty());
    }

    /**
     * Тест {@link TransportService#findAllBuses()}, который возвращает список всех автобусов.
     */
    @Test
    public void findAllBusesTest() {
        Bus bus1 = new Bus(1, 60.1111, 120.1111, 50, LocalDateTime.now());
        Bus bus2 = new Bus(2, 61.1111, 121.1111, 60, LocalDateTime.now());
        Trolleybus trolleybus1 = new Trolleybus(3, 62.1111, 122.1111, 70, LocalDateTime.now());
        Trolleybus trolleybus2 = new Trolleybus(4, 63.1111, 123.1111, 80, LocalDateTime.now());

        transportRepository.create(bus1);
        transportRepository.create(bus2);
        transportRepository.create(trolleybus1);
        transportRepository.create(trolleybus2);

        List<Transport> allBuses = transportService.findAllBuses();
        Assertions.assertEquals(List.of(bus1, bus2), allBuses);
        Assertions.assertNotEquals(List.of(bus1, bus2, trolleybus1), allBuses);

        transportService.clearTable();
        allBuses = transportService.findAllBuses();
        Assertions.assertTrue(allBuses.isEmpty());
    }

    /**
     * Тест {@link TransportService#findAllTrolleys()}, который возвращает список всех троллейбусов.
     */
    @Test
    public void findAllTrolleysTest() {
        Bus bus1 = new Bus(1, 60.1111, 120.1111, 50, LocalDateTime.now());
        Bus bus2 = new Bus(2, 61.1111, 121.1111, 60, LocalDateTime.now());
        Trolleybus trolleybus1 = new Trolleybus(3, 62.1111, 122.1111, 70, LocalDateTime.now());
        Trolleybus trolleybus2 = new Trolleybus(4, 63.1111, 123.1111, 80, LocalDateTime.now());

        transportRepository.create(bus1);
        transportRepository.create(bus2);
        transportRepository.create(trolleybus1);
        transportRepository.create(trolleybus2);

        List<Transport> allTrolleys = transportService.findAllTrolleys();
        Assertions.assertEquals(List.of(trolleybus1, trolleybus2), allTrolleys);
        Assertions.assertNotEquals(List.of(trolleybus1, trolleybus2, bus1), allTrolleys);

        transportService.clearTable();
        allTrolleys = transportService.findAllTrolleys();
        Assertions.assertTrue(allTrolleys.isEmpty());
    }

    /**
     * Тест {@link TransportService#findByTime(LocalDateTime)}, который проверяет,
     * что возвращаемый список транспорта соответствует времени поиска.
     */
    @Test
    public void findByTimeTest() {
        Bus bus1 = new Bus(50, 160.1111, 120.1111, 50,
                LocalDateTime.of(2023, 5, 13, 15, 45, 00)
        );
        transportRepository.create(bus1);

        Bus bus2 = new Bus(51, 61.1111, 121.1111, 51,
                LocalDateTime.of(2023, 5, 14, 10, 30, 00)
        );
        transportRepository.create(bus2);

        Trolleybus trolleybus = new Trolleybus(52, 62.1111, 122.1111, 52,
                LocalDateTime.of(2023, 5, 13, 16, 0, 0)
        );
        transportRepository.create(trolleybus);

        LocalDateTime searchTime = LocalDateTime.of(2023, 5, 13, 15, 45, 00);
        Assertions.assertEquals(transportService.findByTime(searchTime), List.of(bus1));

        LocalDateTime searchInvalidTime = LocalDateTime.of(3030, 10, 10, 10, 10, 10);
        Assertions.assertEquals(transportService.findByTime(searchInvalidTime), Collections.emptyList());
    }

    /**
     * Тест {@link TransportService#findByRoute(int)}, который проверяет,
     * что возвращаемый список транспорта соответствует указанному маршруту.
     */
    @Test
    public void findByRouteTest() {
        Bus bus = new Bus(50, 160.1111, 120.1111, 50,
                LocalDateTime.of(2023, 5, 13, 15, 45, 00)
        );
        transportRepository.create(bus);

        Trolleybus trolleybus = new Trolleybus(51, 61.1111, 121.1111, 51,
                LocalDateTime.of(2023, 5, 13, 16, 45, 00)
        );
        transportRepository.create(trolleybus);

        Assertions.assertEquals(transportService.findByRoute(50), List.of(bus));

        Assertions.assertEquals(transportService.findByRoute(123456789), Collections.emptyList());
    }

    /**
     * Тест {@link TransportService#clearTable()}.
     * Также очищает таблицу перед каждым тестом, чтобы не возникали конфликты между тестовыми данными.
     */
    @BeforeEach
    @Test
    public void clearTableTest() {
        List<Transport> allTransport = transportService.findAllTransport();
        List<Transport> deletedTransport = transportService.clearTable();
        Assertions.assertNotNull(deletedTransport);
        Assertions.assertEquals(deletedTransport, allTransport);
        Assertions.assertDoesNotThrow(() -> {
            transportService.clearTable();
        });
    }

}
