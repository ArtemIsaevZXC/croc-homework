package ru.croc.javaschool.homework7.property;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Тест {@link PropertyContainer}.
 *
 * @author Artem Isaev
 */
public class PropertyContainerTest {

    /**
     * Метод, который загружает настройки из файла.
     */
    @BeforeAll
    static void loadProperties() {
        PropertyContainer.loadProperties();
    }

    /**
     * Тест {@link PropertyContainer#getProperty(String)}.
     * Проверяет, что метод возвращает корректное значение настройки.
     */
    @Test
    public void getPropertyTest() {
        Assertions.assertEquals("transport_db", PropertyContainer.getProperty("database.name"));
        Assertions.assertEquals("admin", PropertyContainer.getProperty("database.username"));
        Assertions.assertEquals("secret", PropertyContainer.getProperty("database.password"));
        Assertions.assertEquals("", PropertyContainer.getProperty("ABC"));
    }

}
