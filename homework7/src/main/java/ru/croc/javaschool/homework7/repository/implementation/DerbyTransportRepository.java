package ru.croc.javaschool.homework7.repository.implementation;

import ru.croc.javaschool.homework7.model.Bus;
import ru.croc.javaschool.homework7.model.Transport;
import ru.croc.javaschool.homework7.model.Trolleybus;
import ru.croc.javaschool.homework7.repository.TransportRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация репозитория задач для Derby.
 *
 * @author Artem Isaev
 */
public class DerbyTransportRepository implements TransportRepository {
    /**
     * Источник данных.
     */
    private final DataSource dataSource;

    /**
     * Создает {@link DerbyTransportRepository}.
     * @param dataSource источник данных.
     */
    public DerbyTransportRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }
    /**
     * Метод инициализации таблицы.
     */
    @Override
    public void initTable() {
        System.out.println("Инициализация таблицы: " + Transport.TABLE_NAME);
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            var databaseMetadata = connection.getMetaData();
            var resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    Transport.TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});
            if (resultSet.next()) {
                System.out.println("Таблица уже существует");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + Transport.TABLE_NAME
                                + " ("
                                + "type VARCHAR(15) NOT NULL, "
                                + "route INTEGER NOT NULL, "
                                + "latitude DOUBLE NOT NULL, "
                                + "longitude DOUBLE NOT NULL, "
                                + "speed INTEGER NOT NULL, "
                                + "time TIMESTAMP NOT NULL, "
                                + "PRIMARY KEY (latitude, longitude, time)"
                                + ")");
                System.out.println("Таблица успешно создана");
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка при создании таблицы: " + e.getMessage());
        } finally {
            System.out.println("=======================================================");
        }
    }
    /**
     * Метод создания новой записи об общественном транспорте.
     * @param transport общественный транспорт
     */
    @Override
    public void create(Transport transport) {
        var query = "INSERT INTO " + Transport.TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?)";
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query)) {
            statement.setString(
                    1,
                    transport.getClass().getSimpleName().toUpperCase());
            statement.setInt(
                    2,
                    transport.getRoute());
            statement.setDouble(
                    3,
                    transport.getLatitude());
            statement.setDouble(
                    4,
                    transport.getLongitude());
            statement.setInt(
                    5,
                    transport.getSpeed());
            statement.setTimestamp(
                    6,
                    Timestamp.valueOf(transport.getTime()));
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (создание): " + e.getMessage());
        }
    }
    /**
     * Метод получения всех записей из таблицы.
     * @return список всех записей в таблице.
     */
    @Override
    public List<Transport> findAll() {
        List<Transport> transports = new ArrayList<>();
        var query = "SELECT * FROM " + Transport.TABLE_NAME;
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int route = resultSet.getInt("route");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                int speed = resultSet.getInt("speed");
                Timestamp timestamp = resultSet.getTimestamp("time");
                LocalDateTime time = timestamp.toLocalDateTime();
                if (type.equals(Bus.class.getSimpleName().toUpperCase())) {
                    transports.add(new Bus(route, latitude, longitude, speed, time));
                } else {
                    transports.add(new Trolleybus(route, latitude, longitude, speed, time));
                }
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (поиск всех): " + e.getMessage());
        }
        return transports;
    }

    /**
     * Метод получения всех автобусов из таблицы.
     * @return список всех автобусов из таблицы.
     */
    @Override
    public List<Bus> findAllBuses() {
        List<Bus> busList = new ArrayList<>();
        var query = "SELECT * FROM " + Transport.TABLE_NAME + " WHERE type = 'BUS'";
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int route = resultSet.getInt("route");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                int speed = resultSet.getInt("speed");
                Timestamp timestamp = resultSet.getTimestamp("time");
                LocalDateTime time = timestamp.toLocalDateTime();
                busList.add(new Bus(route, latitude, longitude, speed, time));
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (поиск автобусов): " + e.getMessage());
        }
        return busList;
    }

    /**
     * Метод получения всех троллейбусов из таблицы.
     * @return список всех троллейбусов из таблицы.
     */
    @Override
    public List<Trolleybus> findAllTrolleys() {
        List<Trolleybus> trolleybusList = new ArrayList<>();
        var query = "SELECT * FROM " + Transport.TABLE_NAME + " WHERE type = 'TROLLEYBUS'";
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int route = resultSet.getInt("route");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                int speed = resultSet.getInt("speed");
                Timestamp timestamp = resultSet.getTimestamp("time");
                LocalDateTime time = timestamp.toLocalDateTime();
                trolleybusList.add(new Trolleybus(route, latitude, longitude, speed, time));
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (поиск троллейбусов): " + e.getMessage());
        }
        return trolleybusList;
    }

    /**
     * Возвращает список общественного транспорта по указанному маршруту.
     * @param routeToFindWith указанный маршрут.
     * @return список общественного транспорта.
     */
    @Override
    public List<Transport> findByRoute(int routeToFindWith) {
        List<Transport> transports = new ArrayList<>();
        var query = "SELECT * FROM " + Transport.TABLE_NAME + " WHERE route = " + routeToFindWith;
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int route = resultSet.getInt("route");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                int speed = resultSet.getInt("speed");
                Timestamp timestamp = resultSet.getTimestamp("time");
                LocalDateTime time = timestamp.toLocalDateTime();
                if (type.equals(Bus.class.getSimpleName().toUpperCase())) {
                    transports.add(new Bus(route, latitude, longitude, speed, time));
                } else {
                    transports.add(new Trolleybus(route, latitude, longitude, speed, time));
                }
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (поиск по указанному маршруту): " + e.getMessage());
        }
        return transports;
    }

    /**
     * Возвращает список общественного транспорта по указанному времени.
     * @param timeToFindWith указанное время.
     * @return список общественного транспорта.
     */
    @Override
    public List<Transport> findByTime(LocalDateTime timeToFindWith) {
        List<Transport> transports = new ArrayList<>();
        var query = "SELECT * FROM " + Transport.TABLE_NAME
                + " WHERE time = '" + Timestamp.valueOf(timeToFindWith) + "'";
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int route = resultSet.getInt("route");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                int speed = resultSet.getInt("speed");
                Timestamp timestamp = resultSet.getTimestamp("time");
                LocalDateTime time = timestamp.toLocalDateTime();
                if (type.equals(Bus.class.getSimpleName().toUpperCase())) {
                    transports.add(new Bus(route, latitude, longitude, speed, time));
                } else {
                    transports.add(new Trolleybus(route, latitude, longitude, speed, time));
                }
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (поиск по указанному времени): " + e.getMessage());
        }
        return transports;
    }


}
