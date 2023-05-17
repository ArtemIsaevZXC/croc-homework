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
     */
    @Test
    public void marshalTest() {
        String expected = "2023-05-13T15:30:00";
        String actual = adapter.marshal(time);
        Assertions.assertEquals(expected, actual);

        Assertions.assertThrows(RuntimeException.class, () -> adapter.marshal(null));
    }

    /**
     * Тест {@link LocalDateTimeAdapter#unmarshal(String)}.
     */
    @Test
    public void unmarshalTest() {
        LocalDateTime actual = adapter.unmarshal("2023-05-13T15:30:00");
        Assertions.assertEquals(time, actual);

        String invalidInput = "2023-05-13 15:30:00";
        Assertions.assertThrows(RuntimeException.class, () -> adapter.unmarshal(invalidInput));
        Assertions.assertThrows(RuntimeException.class, () -> adapter.unmarshal(null));
    }
}
