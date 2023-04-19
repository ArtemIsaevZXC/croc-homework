package ru.croc.javaschool.homework6.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework6.data.marshalling.DestProject;
import ru.croc.javaschool.homework6.data.marshalling.People;
import ru.croc.javaschool.homework6.data.marshalling.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Тест преобразователя xml данных.
 *
 * @author Artem Isaev.
 */
public class ProjectsDataConverterTest {
    /**
     * Тест {@link ru.croc.javaschool.homework6.tools.ProjectsDataConverter#convert(String) convert}.
     * @throws IOException
     */
    @Test
    public void convertTest() throws IOException {
        final XmlConverter xmlConverter = new XmlConverter();

        final Person person1 = new Person("Человек 1");
        final DestProject project1 = new DestProject("Проект 1", "Менеджер", "");
        person1.addProject(project1);

        final Person person2 = new Person("Человек 2");
        person2.addProject(project1); // такой же как у 1

        final Person person3 = new Person("Человек 3");
        final DestProject project31 = new DestProject("Проект 1", "Специалист", "Человек 1");
        final DestProject project32 = new DestProject("Проект 2", "Менеджер", "");
        person3.addProject(project31);
        person3.addProject(project32);

        final Person person4 = new Person("Человек 4");
        person4.addProject(project31); // такой же как у 3
        person4.addProject(project32); // такой же как у 3

        final Person person5 = new Person("Человек 5");
        final DestProject project51 = new DestProject("Проект 1", "Специалист", "Человек 2");
        final DestProject project52 = new DestProject("Проект 2", "Специалист", "Человек 3");
        person5.addProject(project51);
        person5.addProject(project52);

        final Person person6 = new Person("Человек 6");
        person6.addProject(project51); // такой же как у 5
        person6.addProject(project52); // такой же как у 5

        final Person person7 = new Person("Человек 7");
        final DestProject project7 = new DestProject("Проект 2", "Специалист", "Человек 4");
        person7.addProject(project7);

        List<Person> personList = List.of(
                person1,
                person2,
                person3,
                person4,
                person5,
                person6,
                person7
        );
        People people = new People(personList);
        final String expected = xmlConverter.toXml(people);

        Path path = Paths.get("src/test/resources", "srcTest.xml");
        String actual = ProjectsDataConverter.convert(Files.readString(path));

        Assertions.assertEquals(expected, actual);
    }
}
