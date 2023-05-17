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
import java.util.Objects;

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
     *
     * @param dataSource источник данных.
     */
    public DerbyTransportRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Метод инициализации таблицы.
     *
     * Его тестом не покрываю.
     */
    @Override
    public void initTable() {
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            var databaseMetadata = connection.getMetaData();
            var resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    Transport.TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});
            if (!resultSet.next()) {
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод создания новой записи об общественном транспорте.
     *
     * @param transport общественный транспорт
     * @return добавленный транспорт
     */
    @Override
    public Transport create(Transport transport) {
        String query = "INSERT INTO " + Transport.TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?)";
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
            Transport createdTransport = findTransportByProperties(
                    transport.getClass().getSimpleName(),
                    transport.getRoute(),
                    transport.getLatitude(),
                    transport.getLongitude(),
                    transport.getSpeed(),
                    transport.getTime());
            if (Objects.isNull(createdTransport)) {
                throw new NullPointerException();
            } else {
                return createdTransport;
            }
        } catch (SQLException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод получения всех записей транспорта выбранного типа из таблицы.
     *
     * @return список всех записей в таблице.
     */
    @Override
    public List<Transport> findAll(String typeToSearch) {
        List<Transport> transports = new ArrayList<>();
        String query;
        if (typeToSearch.equals("TRANSPORT")) {
            query = "SELECT * FROM " + Transport.TABLE_NAME;
        } else {
            query = "SELECT * FROM " + Transport.TABLE_NAME + " WHERE TYPE = '" + typeToSearch.toUpperCase() + "'";
        }
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int route = resultSet.getInt("route");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                int speed = resultSet.getInt("speed");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                if (type.equals(Bus.class.getSimpleName().toUpperCase())) {
                    transports.add(new Bus(route, latitude, longitude, speed, time));
                } else {
                    transports.add(new Trolleybus(route, latitude, longitude, speed, time));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return transports;
    }

    /**
     * Возвращает список общественного транспорта по указанному маршруту.
     *
     * @param routeToFindWith указанный маршрут.
     * @return список общественного транспорта.
     */
    @Override
    public List<Transport> findByRoute(int routeToFindWith) {
        List<Transport> transports = new ArrayList<>();
        String query = "SELECT * FROM " + Transport.TABLE_NAME + " WHERE route = " + routeToFindWith;
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int route = resultSet.getInt("route");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                int speed = resultSet.getInt("speed");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                if (type.equals(Bus.class.getSimpleName().toUpperCase())) {
                    transports.add(new Bus(route, latitude, longitude, speed, time));
                } else {
                    transports.add(new Trolleybus(route, latitude, longitude, speed, time));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transports;
    }

    /**
     * Возвращает список общественного транспорта по указанному времени.
     *
     * @param timeToFindWith указанное время.
     * @return список общественного транспорта.
     */
    @Override
    public List<Transport> findByTime(LocalDateTime timeToFindWith) {
        List<Transport> transports = new ArrayList<>();
        String query = "SELECT * FROM " + Transport.TABLE_NAME
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
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                if (type.equals(Bus.class.getSimpleName().toUpperCase())) {
                    transports.add(new Bus(route, latitude, longitude, speed, time));
                } else {
                    transports.add(new Trolleybus(route, latitude, longitude, speed, time));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transports;
    }

    /**
     * Удаляет все записи из таблицы и возвращает список удаленного транспорта.
     *
     * @return список удаленного транспорта.
     */
    public List<Transport> clearTable() {
        List<Transport> deletedTransport = findAll("TRANSPORT");
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute("DELETE FROM " + Transport.TABLE_NAME);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deletedTransport;
    }

    /**
     * Метод, который возвращает транспорт на основе его свойств.
     * Этот метод был создан ИСКЛЮЧИТЕЛЬНО для того, чтобы метод create возвращал создаваемый в БД объект,
     * т.о. появляется возможность написать к нему тест.
     *
     * Мне показалось, что это самое простое решение проблемы. Но возможно я исхожу из ложных предпосылок.
     *
     * @param type      искомый тип
     * @param route     искомый маршрут
     * @param latitude  искомая широта
     * @param longitude искомая долгота
     * @param speed     искомая скорость
     * @param time      искомое время
     * @return транспорт на основе вышеперечисленных свойств
     */
    private Transport findTransportByProperties(
            String type,
            int route,
            double latitude,
            double longitude,
            int speed,
            LocalDateTime time) {
        String query = "SELECT * FROM " + Transport.TABLE_NAME
                + " WHERE TYPE = ?"
                + " AND ROUTE = ?"
                + " AND LATITUDE = ?"
                + " AND LONGITUDE = ?"
                + " AND SPEED = ?"
                + " AND TIME = ?";
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query)) {
            statement.setString(1, type.toUpperCase());
            statement.setInt(2, route);
            statement.setDouble(3, latitude);
            statement.setDouble(4, longitude);
            statement.setInt(5, speed);
            statement.setTimestamp(6, Timestamp.valueOf(time));
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int routeFound = resultSet.getInt("route");
                    double latitudeFound = resultSet.getDouble("latitude");
                    double longitudeFound = resultSet.getDouble("longitude");
                    int speedFound = resultSet.getInt("speed");
                    LocalDateTime timeFound = resultSet.getTimestamp("time").toLocalDateTime();
                    if (type.equals(Bus.class.getSimpleName().toUpperCase())) {
                        return new Bus(routeFound, latitudeFound, longitudeFound, speedFound, timeFound);
                    } else {
                        return new Trolleybus(routeFound, latitudeFound, longitudeFound, speedFound, timeFound);
                    }
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
