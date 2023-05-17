package ru.croc.javaschool.homework7.xmltools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework7.model.Bus;
import ru.croc.javaschool.homework7.model.unmarshalling.Buses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Тест для класса {@link XmlConverter}.
 *
 * @author Artem Isaev
 */
public class XmlConverterTest {
    /**
     * Необходим для парсинга из XML файла.
     */
    private final XmlConverter xmlConverter = new XmlConverter();

    /**
     * Тест {@link XmlConverter#fromXml}.
     *
     * @throws IOException
     */
    @Test
    public void fromXmlTest() throws IOException {
        Path path = Paths.get("src/test/resources", "fromXmlTest.xml");
        Buses expected = new Buses(
                new ArrayList<>(List.of(
                        new Bus(
                                101,
                                55.7558,
                                37.6173,
                                40,
                                LocalDateTime.of(2023, 5, 13, 15, 45, 0)
                        ))));
        Buses actual = xmlConverter.fromXml(Files.readString(path), Buses.class);
        Assertions.assertEquals(expected, actual);

        Path invalidPath = Paths.get("src/test/resources", "invalidXmlTest.xml");
        Assertions.assertThrows(RuntimeException.class, () -> {
            xmlConverter.fromXml(Files.readString(invalidPath), Buses.class);
        });
    }
}
