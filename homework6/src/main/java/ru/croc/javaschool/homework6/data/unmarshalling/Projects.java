package ru.croc.javaschool.homework6.data.unmarshalling;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * Проекты.
 *
 * @author Artem Isaev
 */
@XmlRootElement(name = "projects")
public class Projects {
    /**
     * Список проектов.
     */
    @XmlElement(name = "project")
    private List<SourceProject> projects;

    /**
     * Конструктор, необходимый для JAXB.
     */
    public Projects() {
    }

    /**
     * Создает {@link Projects}.
     *
     * @param projectList список проектов.
     */
    public Projects(List<SourceProject> projectList) {
        this.projects = projectList;
    }

    /**
     * Список проектов.
     *
     * @return список проектов.
     */
    public List<SourceProject> getProjects() {
        return projects;
    }

    /**
     * Переопределение метода equals.
     *
     * @param o объект, с которым необходимо сравнивать.
     * @return результат сравнения.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Projects)) {
            return false;
        }
        Projects projectsToCompare = (Projects) o;
        return Objects.equals(getProjects(), projectsToCompare.getProjects());
    }
}
