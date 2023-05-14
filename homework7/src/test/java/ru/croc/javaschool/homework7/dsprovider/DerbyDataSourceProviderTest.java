package ru.croc.javaschool.homework7.dsprovider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.sql.DataSource;


/**
 * Тест {@link DerbyDataSourceProvider}.
 */
public class DerbyDataSourceProviderTest {

    /**
     * Тест {@link DerbyDataSourceProvider#getDataSource()}.
     * Проверка, что источник данных для Derby создаётся успешно.
     */
    @Test
    public void getDataSourceTest() {
        final DataSource dataSource = new DerbyDataSourceProvider().getDataSource();
        Assertions.assertNotNull(dataSource);
    }
}
