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
import java.sql.SQLException;
import java.time.LocalDateTime;


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
     * Т.к. тесты заполняют таблицу в базе данных, они изменяют ее состояние.
     * Поэтому создаётся метод, очищающий таблицу от других данных, чтобы избежать
     * конфликта между тестами.
     *
     * @throws SQLException если возникает ошибка при выполнении запроса.
     */
    private void clearTable() throws SQLException {
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute("DELETE FROM " + Transport.TABLE_NAME);
        }
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
     * Тест {@link DerbyTransportRepository#initTable()}.
     *
     * @throws SQLException если возникает ошибка при выполнении запроса.
     */
    @Test
    public void initTableTest() throws SQLException {
        try (var connection = dataSource.getConnection()) {
            var metaData = connection.getMetaData();
            var resultSet = metaData.getTables(
                    null,
                    null,
                    Transport.TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});
            Assertions.assertTrue(resultSet.next());
        }
    }

    /**
     * Тест {@link DerbyTransportRepository#create(Transport)}.
     * Создается новый объект Bus, который добавляется в базу данных при помощи метода create() репозитория.
     * Затем выполняется SQL-запрос на подсчет количества записей в таблице, в которой хранятся данные транспортных средств.
     * Проверяется, что в таблице находится одна запись (которую мы и записали).
     *
     * @throws SQLException если возникает ошибка при выполнении запроса.
     */
    @Test
    public void createTest() throws SQLException {
        clearTable();
        final int ROUTE_NUMBER = 50;
        final double LATITUDE = 60.1111;
        final double LONGITUDE = 120.1111;
        final int SPEED = 50;
        var bus = new Bus(
                ROUTE_NUMBER,
                LATITUDE,
                LONGITUDE,
                SPEED,
                LocalDateTime.of(2023, 5, 13, 15, 45, 00)
        );
        transportRepository.create(bus);
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM " + Transport.TABLE_NAME);
             var resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            Assertions.assertEquals(1, resultSet.getInt(1));
        }
    }

    /**
     * Тест {@link DerbyTransportRepository#findAll()}.
     *
     * @throws SQLException если возникает ошибка при выполнении запроса.
     */
    @Test
    public void findAllTest() throws SQLException {
        clearTable();
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

        var allTransports = transportRepository.findAll();

        Assertions.assertEquals(2, allTransports.size());
        Assertions.assertTrue(allTransports.contains(bus));
        Assertions.assertTrue(allTransports.contains(trolleybus));
    }

    /**
     * Тест {@link DerbyTransportRepository#findAllBuses()}.
     *
     * @throws SQLException если возникает ошибка при выполнении запроса.
     */
    @Test
    public void findAllBusesTest() throws SQLException {
        clearTable();
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
                LocalDateTime.of(2023, 5, 13, 16, 45, 00)
        );
        transportRepository.create(bus2);

        final int ROUTE_NUMBER_3 = 51;
        final double LATITUDE_3 = 1001.1111;
        final double LONGITUDE_3 = 1001.1111;
        final int SPEED_3 = 51;
        var trolleybus = new Trolleybus(
                ROUTE_NUMBER_3,
                LATITUDE_3,
                LONGITUDE_3,
                SPEED_3,
                LocalDateTime.of(2023, 5, 13, 16, 45, 00)
        );
        transportRepository.create(trolleybus);

        var allBuses = transportRepository.findAllBuses();

        Assertions.assertEquals(2, allBuses.size());
        Assertions.assertTrue(allBuses.contains(bus1));
        Assertions.assertTrue(allBuses.contains(bus2));
    }

    /**
     * Тест {@link DerbyTransportRepository#findAllTrolleys()}.
     *
     * @throws SQLException если возникает ошибка при выполнении запроса.
     */
    @Test
    public void findAllTrolleysTest() throws SQLException {
        clearTable();
        final int ROUTE_NUMBER_1 = 50;
        final double LATITUDE_1 = 160.1111;
        final double LONGITUDE_1 = 120.1111;
        final int SPEED_1 = 50;
        var trolleybus1 = new Trolleybus(
                ROUTE_NUMBER_1,
                LATITUDE_1,
                LONGITUDE_1,
                SPEED_1,
                LocalDateTime.of(2023, 5, 13, 15, 45, 00)
        );
        transportRepository.create(trolleybus1);

        final int ROUTE_NUMBER_2 = 51;
        final double LATITUDE_2 = 61.1111;
        final double LONGITUDE_2 = 121.1111;
        final int SPEED_2 = 51;
        var trolleybus2 = new Trolleybus(
                ROUTE_NUMBER_2,
                LATITUDE_2,
                LONGITUDE_2,
                SPEED_2,
                LocalDateTime.of(2023, 5, 13, 16, 45, 00)
        );
        transportRepository.create(trolleybus2);

        final int ROUTE_NUMBER_3 = 51;
        final double LATITUDE_3 = 1001.1111;
        final double LONGITUDE_3 = 1001.1111;
        final int SPEED_3 = 51;
        var bus = new Bus(
                ROUTE_NUMBER_3,
                LATITUDE_3,
                LONGITUDE_3,
                SPEED_3,
                LocalDateTime.of(2023, 5, 13, 16, 45, 00)
        );
        transportRepository.create(bus);

        var allTrolleybuses = transportRepository.findAllTrolleys();

        Assertions.assertEquals(2, allTrolleybuses.size());
        Assertions.assertTrue(allTrolleybuses.contains(trolleybus1));
        Assertions.assertTrue(allTrolleybuses.contains(trolleybus2));
    }

    /**
     * Тест {@link DerbyTransportRepository#findByRoute(int)}.
     *
     * @throws SQLException если возникает ошибка при выполнении запроса.
     */
    @Test
    public void findByRouteTest() throws SQLException {
        clearTable();
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

        final int ROUTE_NUMBER_2 = 50;
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

        final int ROUTE_NUMBER_3 = 150;
        final double LATITUDE_3 = 260.1111;
        final double LONGITUDE_3 = 220.1111;
        final int SPEED_3 = 50;
        var bus2 = new Bus(
                ROUTE_NUMBER_3,
                LATITUDE_3,
                LONGITUDE_3,
                SPEED_3,
                LocalDateTime.of(2023, 5, 13, 15, 45, 00)
        );
        transportRepository.create(bus2);
        final int ROUTE_NUMBER_TO_FIND = 50;
        var transports = transportRepository.findByRoute(ROUTE_NUMBER_TO_FIND);

        Assertions.assertEquals(2, transports.size());
        Assertions.assertTrue(transports.contains(bus1));
        Assertions.assertTrue(transports.contains(trolleybus));
    }

    /**
     * Тест {@link DerbyTransportRepository#findByTime(LocalDateTime)}.
     *
     * @throws SQLException если возникает ошибка при выполнении запроса.
     */
    @Test
    public void findByTimeTest() throws SQLException {
        clearTable();
        final int ROUTE_NUMBER_1 = 50;
        final double LATITUDE_1 = 160.1111;
        final double LONGITUDE_1 = 120.1111;
        final int SPEED_1 = 50;
        var bus = new Bus(
                ROUTE_NUMBER_1,
                LATITUDE_1,
                LONGITUDE_1,
                SPEED_1,
                LocalDateTime.of(2023, 5, 13, 15, 0, 0)
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
                LocalDateTime.of(2023, 5, 13, 16, 0, 0)
        );
        transportRepository.create(trolleybus);

        var transports = transportRepository.findByTime(LocalDateTime.of(
                2023, 5, 13, 15, 0, 0));

        Assertions.assertEquals(1, transports.size());
        Assertions.assertEquals(bus, transports.get(0));
    }

}

