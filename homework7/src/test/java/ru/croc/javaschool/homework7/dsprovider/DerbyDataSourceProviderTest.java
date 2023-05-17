package ru.croc.javaschool.homework7.dsprovider;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.croc.javaschool.homework7.property.PropertyContainer;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * Тест {@link DerbyDataSourceProvider}.
 */
public class DerbyDataSourceProviderTest {

    /**
     * Метод, который загружает настройки из файла.
     */
    @BeforeAll
    static void loadProperties() {
        PropertyContainer.loadProperties();
    }

    /**
     * Тест {@link DerbyDataSourceProvider#getDataSource()}.
     * Проверка, что источник данных для Derby создаётся успешно.
     * Также проверяется, что успешно открывается connection.
     */
    @Test
    public void getDataSourceTest() throws SQLException {
        DataSource dataSource = new DerbyDataSourceProvider().getDataSource();
        Assertions.assertNotNull(dataSource);
        Assertions.assertNotNull(dataSource.getConnection());
    }
}
