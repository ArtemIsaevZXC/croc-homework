package ru.croc.javaschool.homework6.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework6.data.marshalling.DestProject;
import ru.croc.javaschool.homework6.data.marshalling.People;
import ru.croc.javaschool.homework6.data.marshalling.Person;
import ru.croc.javaschool.homework6.data.unmarshalling.Manager;
import ru.croc.javaschool.homework6.data.unmarshalling.Projects;
import ru.croc.javaschool.homework6.data.unmarshalling.SourceProject;
import ru.croc.javaschool.homework6.data.unmarshalling.Specialist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Тест для класса {@link XmlConverter}.
 *
 * @author Artem Isaev
 */
public class XmlConverterTest {
    /**
     * Необходим для сериализации/десериализации.
     */
    private final XmlConverter xmlConverter = new XmlConverter();

    /**
     * Тест {@link ru.croc.javaschool.homework6.tools.XmlConverter#fromXml fromXml}.
     *
     * @throws IOException
     */
    @Test
    public void fromXmlTest() throws IOException {
        Path path = Paths.get("src/test/resources", "fromXmlTest.xml");
        SourceProject sourceProject = new SourceProject(
                "Проект 1",
                "Описание 1",
                new ArrayList<>(List.of(
                        new Manager("Человек 1", new ArrayList<>(List.of(
                                new Specialist("Человек 3")
                        )))
                )));
        Projects expected = new Projects(List.of(sourceProject));
        Projects actual = xmlConverter.fromXml(Files.readString(path), Projects.class);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Тест {@link ru.croc.javaschool.homework6.tools.XmlConverter#toXml toXml}.
     *
     * @throws IOException
     */
    @Test
    public void toXmlTest() throws IOException {
        Path path = Paths.get("src/test/resources", "toXmlTest.xml");
        final Person person1 = new Person("Человек 1");
        final DestProject project1 = new DestProject("Проект 1", "Менеджер", "");
        person1.addProject(project1);
        People people = new People(List.of(person1));
        final String expected = xmlConverter.toXml(people);
        final String actual = Files.readString(path);
        Assertions.assertEquals(expected, actual);
    }
}
