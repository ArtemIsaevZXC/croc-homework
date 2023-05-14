package ru.croc.javaschool.homework7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Тест {@link Application}.
 */
public class ApplicationTest {
    /**
     * Тест {@link Application#start(Path, Path)}.
     */
    @Test
    public void startTest() {
        Path busXML = Paths.get("src/test/resources/buses.xml");
        Path trolleybusXML = Paths.get("src/test/resources/trolleys.xml");
        Assertions.assertTrue(Application.start(busXML, trolleybusXML));
    }
}
