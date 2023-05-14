package ru.croc.javaschool.homework7.dsprovider;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.javaschool.homework7.property.PropertyContainer;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Провайдер источника данных для работы с Derby.
 *
 * @author Vladislav Khlybov
 */
public class DerbyDataSourceProvider implements DataSourceProvider {
    /**
     * Объект для доступа к встроенной Derby.
     */
    private EmbeddedDataSource dataSource;

    /**
     * Получение источника данных для Derby.
     *
     * @return источник данных для Derby
     */
    @Override
    public DataSource getDataSource() {
        if (Objects.isNull(dataSource)) {
            dataSource = new EmbeddedDataSource();
            dataSource.setDatabaseName(PropertyContainer.getProperty("database.name"));
            var username = PropertyContainer.getProperty("database.username");
            var password = PropertyContainer.getProperty("database.password");
            if (!username.isEmpty() && !password.isEmpty()) {
                dataSource.setUser(username);
                dataSource.setPassword(password);
            }
            dataSource.setCreateDatabase("create");
        }
        return dataSource;
    }
}
