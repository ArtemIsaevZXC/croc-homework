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
        Application app = new Application();
        Assertions.assertTrue(app.start(busXML, trolleybusXML));
        System.out.println("ИНФОРМАЦИЯ О ВСЁМ ТРАНСПОРТЕ: ");
        app.getAllTransport().forEach(System.out::println);
        System.out.println("ИНФОРМАЦИЯ ОБ АВТОБУСАХ: ");
        app.getAllBuses().forEach(System.out::println);
        System.out.println("ИНФОРМАЦИЯ О ТРОЛЛЕЙБУСАХ");
        app.getAllTrolleys().forEach(System.out::println);
        System.out.println("ИНФОРМАЦИЯ О ТРАНСПОРТЕ ПО МАРШРУТУ 101: ");
        app.getRouteTransport().forEach(System.out::println);
        System.out.println("ИНФОРМАЦИЯ О ТРАНСПОРТЕ ПО ВРЕМЕНИ 13.05.23 15:46: ");
        app.getTimeTransport().forEach(System.out::println);
        System.out.println("ИНФОРМАЦИЯ О ТРАНСПОРТЕ ПО ТЕКУЩЕМУ ВРЕМЕНИ: ");
        app.getTimeNotFoundTransport().forEach(System.out::println);
        System.out.println("ИНФОРМАЦИЯ О ТРАНСПОРТЕ ПО МАРШРУТУ 999: ");
        app.getRouteNotFoundTransport().forEach(System.out::println);
        System.out.println("УДАЛЕНИЕ ДАННЫХ... ТЕКУЩИЕ ДАННЫЕ В ТАБЛИЦЕ: ");
        System.out.println(app.getAfterClearTable().size());
    }
}
