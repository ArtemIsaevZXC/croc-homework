package ru.croc.javaschool.homework7.xmltools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * Тест для класса {@link LocalDateTimeAdapter}.
 *
 * @author Artem Isaev
 */
public class LocalDateTimeAdapterTest {
    /**
     * Тестовый объект LocalDateTime.
     */
    private final LocalDateTime time = LocalDateTime.of(2023, 5, 13, 15, 30, 0);
    /**
     * Экземпляр LocalDateTimeAdapter.
     */
    LocalDateTimeAdapter adapter = new LocalDateTimeAdapter();

    /**
     * Тест {@link LocalDateTimeAdapter#marshal(LocalDateTime)}.
     * @throws Exception если произошла ошибка преобразования.
     */
    @Test
    public void marshalTest() throws Exception {
        String expected = "2023-05-13T15:30:00";
        String actual = adapter.marshal(time);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Тест {@link LocalDateTimeAdapter#unmarshal(String)}.
     * @throws Exception если произошла ошибка преобразования.
     */
    @Test
    public void unmarshalTest() throws Exception {
        LocalDateTime actual = adapter.unmarshal("2023-05-13T15:30:00");
        Assertions.assertEquals(time, actual);
    }
}
