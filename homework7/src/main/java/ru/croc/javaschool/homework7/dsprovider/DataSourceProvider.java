package ru.croc.javaschool.homework7.dsprovider;

import javax.sql.DataSource;

/**
 * Интерфейс провайдера источника данных.
 *
 * @author Vladislav Khlybov
 */
public interface DataSourceProvider {
    /**
     * Получение источника данных.
     *
     * @return источник данных.
     */
    DataSource getDataSource();
}
