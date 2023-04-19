package ru.croc.javaschool.homework6.data.marshalling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Тест для класса {@link Person}.
 *
 * @author Artem Isaev
 */
public class PersonTest {
    /**
     * Тест {@link ru.croc.javaschool.homework6.data.marshalling.Person#addProject(DestProject) addProject}.
     */
    @Test
    public void addProjectTest() {
        final Person person = new Person("John");
        List<DestProject> projects = new ArrayList<>(List.of(
                new DestProject("Title1", "Role1", "Manager1"),
                new DestProject("Title2", "Role2", "Manager2")
        ));
        person.addProject(new DestProject("Title1", "Role1", "Manager1"));
        person.addProject(new DestProject("Title2", "Role2", "Manager2"));
        Assertions.assertEquals(projects.get(0), person.getProjects().get(0));
        Assertions.assertEquals(projects.get(1), person.getProjects().get(1));
    }
}
