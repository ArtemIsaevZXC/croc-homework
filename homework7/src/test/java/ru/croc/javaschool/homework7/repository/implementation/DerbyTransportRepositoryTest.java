package ru.croc.javaschool.homework7.repository.implementation;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import ru.croc.javaschool.homework7.model.Bus;
import ru.croc.javaschool.homework7.model.Transport;
import ru.croc.javaschool.homework7.model.Trolleybus;
import ru.croc.javaschool.homework7.repository.TransportRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Тесты для {@link DerbyTransportRepository}.
 */
public class DerbyTransportRepositoryTest {

    /**
     * Репозиторий общественного транспорта.
     */
    private static TransportRepository transportRepository;
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
     * Тест {@link DerbyTransportRepository#create(Transport)}.
     * Создается новый объект Bus, который добавляется в базу данных при помощи метода create() репозитория.
     * Добавленный объект сравнивается с исходным.
     */
    @Test
    public void createTest() {
        Bus bus = new Bus(50, 60.1111, 120.1111, 50,
                LocalDateTime.of(2023, 5, 13, 15, 45, 00)
        );
        Transport actual = transportRepository.create(bus);
        Assertions.assertEquals(bus, actual);
        Assertions.assertThrows(RuntimeException.class, () -> {
            transportRepository.create(null);
        });
    }

    /**
     * Тест {@link DerbyTransportRepository#findAll(String)}.
     */
    @Test
    public void findAllTest() {
        Bus bus = new Bus(50, 160.1111, 120.1111, 50,
                LocalDateTime.of(2023, 5, 13, 15, 45, 00)
        );
        transportRepository.create(bus);

        Trolleybus trolleybus = new Trolleybus(51, 61.1111, 121.1111, 51,
                LocalDateTime.of(2023, 5, 13, 16, 45, 00)
        );
        transportRepository.create(trolleybus);

        List<Transport> allTransports = transportRepository.findAll("TRANSPORT");
        Assertions.assertEquals(2, allTransports.size());
        Assertions.assertEquals(List.of(bus, trolleybus), allTransports);

        List<Transport> allBuses = transportRepository.findAll("BUS");
        Assertions.assertEquals(List.of(bus), allBuses);

        List<Transport> allTrolleys = transportRepository.findAll("TROLLEYBUS");
        Assertions.assertEquals(List.of(trolleybus), allTrolleys);

        List<Transport> invalidResult = transportRepository.findAll("INVALID_TYPE");
        Assertions.assertTrue(invalidResult.isEmpty());
    }


    /**
     * Тест {@link DerbyTransportRepository#findByRoute(int)}.
     */
    @Test
    public void findByRouteTest() {
        Bus bus1 = new Bus(50, 160.1111, 120.1111, 50,
                LocalDateTime.of(2023, 5, 13, 15, 45, 00)
        );
        transportRepository.create(bus1);

        Trolleybus trolleybus = new Trolleybus(50, 61.1111, 121.1111, 51,
                LocalDateTime.of(2023, 5, 13, 16, 45, 00)
        );
        transportRepository.create(trolleybus);

        Bus bus2 = new Bus(150, 260.1111, 220.1111, 50,
                LocalDateTime.of(2023, 5, 13, 15, 45, 00)
        );
        transportRepository.create(bus2);

        final int ROUTE_NUMBER_TO_FIND = 50;
        List<Transport> transports = transportRepository.findByRoute(ROUTE_NUMBER_TO_FIND);
        Assertions.assertEquals(2, transports.size());
        Assertions.assertTrue(transports.contains(bus1));
        Assertions.assertTrue(transports.contains(trolleybus));

        final int ANOTHER_ROUTE_NUMBER_TO_FIND = 555555;
        List<Transport> another_transports = transportRepository.findByRoute(ANOTHER_ROUTE_NUMBER_TO_FIND);
        Assertions.assertTrue(another_transports.isEmpty());
    }

    /**
     * Тест {@link DerbyTransportRepository#findByTime(LocalDateTime)}.
     */
    @Test
    public void findByTimeTest() {
        Bus bus = new Bus(50, 160.1111, 120.1111, 50,
                LocalDateTime.of(2023, 5, 13, 15, 0, 0)
        );
        transportRepository.create(bus);

        Trolleybus trolleybus = new Trolleybus(51, 61.1111, 121.1111, 51,
                LocalDateTime.of(2023, 5, 13, 16, 0, 0)
        );
        transportRepository.create(trolleybus);

        List<Transport> transports = transportRepository.findByTime(LocalDateTime.of(
                2023, 5, 13, 15, 0, 0));

        Assertions.assertEquals(1, transports.size());
        Assertions.assertEquals(bus, transports.get(0));

        List<Transport> another_transports = transportRepository.findByTime(LocalDateTime.of(
                3030, 10, 10, 10, 10, 10));
        Assertions.assertTrue(another_transports.isEmpty());
    }


    /**
     * Т.к. тесты заполняют таблицу в базе данных, они изменяют ее состояние.
     * Поэтому необходим метод, очищающий таблицу от других данных, чтобы избежать
     * конфликта между тестами.
     * <p>
     * Также является тестом {@link DerbyTransportRepository#clearTable()}.
     */
    @BeforeEach
    @Test
    public void clearTableTest() {
        Bus bus = new Bus(50, 160.1111, 120.1111, 50,
                LocalDateTime.of(2023, 5, 13, 15, 50, 00)
        );
        transportRepository.create(bus);

        Trolleybus trolleybus = new Trolleybus(51, 61.1111, 121.1111, 51,
                LocalDateTime.of(2023, 5, 13, 16, 50, 00)
        );
        transportRepository.create(trolleybus);

        List<Transport> allTransport = transportRepository.findAll("TRANSPORT");
        List<Transport> deletedTransport = transportRepository.clearTable();
        Assertions.assertEquals(allTransport, deletedTransport);
    }
}

