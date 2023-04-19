package ru.croc.javaschool.homework6.data.marshalling;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Человек.
 *
 * @author Artem Isaev.
 */
public class Person {
    /**
     * Имя.
     */
    @XmlElement(name = "name")
    private String name;
    /**
     * Список проектов, в которых участвовал человек.
     */
    @XmlElementWrapper(name = "projects")
    @XmlElement(name = "project")
    private List<DestProject> projects = new ArrayList<>();

    /**
     * Конструктор, необходимый для JAXB.
     */
    public Person() {
    }

    /**
     * Создает {@link Person}.
     *
     * @param name имя.
     */
    public Person(String name) {
        this.name = name;
    }

    /**
     * Добавляет проект к списку проектов.
     *
     * @param project проект.
     * @return true, если проект добавлен успешно, false иначе.
     */
    public boolean addProject(DestProject project) {
        return projects.add(project);
    }

    /**
     * Имя.
     *
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Список проектов, в которых участвовал человек.
     *
     * @return список проектов, в которых участвовал человек.
     */

    public List<DestProject> getProjects() {
        return projects;
    }
}
