package ru.croc.javaschool.homework6.data.unmarshalling;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.Objects;

/**
 * Проект из исходного файла.
 *
 * @author Artem Isaev
 */
public class SourceProject {
    /**
     * Название.
     */
    @XmlElement(name = "title")
    private String title;
    /**
     * Описание.
     */
    @XmlElement(name = "description")
    private String description;
    /**
     * Список менеджеров.
     */
    @XmlElementWrapper(name = "managers")
    @XmlElement(name = "manager")
    private List<Manager> managers;

    /**
     * Конструктор, необходимый для JAXB.
     */
    public SourceProject() {
    }

    /**
     * Создает {@link SourceProject}.
     *
     * @param title       название.
     * @param description описание.
     * @param managers    список менеджеров.
     */
    public SourceProject(String title, String description, List<Manager> managers) {
        this.title = title;
        this.description = description;
        this.managers = managers;
    }

    /**
     * Название.
     *
     * @return название.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Описание.
     *
     * @return описание.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Список менеджеров.
     *
     * @return список менеджеров.
     */
    public List<Manager> getManagers() {
        return managers;
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
        if (!(o instanceof SourceProject)) {
            return false;
        }
        SourceProject sourceProject = (SourceProject) o;
        return Objects.equals(getTitle(), sourceProject.getTitle()) &&
                Objects.equals(getDescription(), sourceProject.getDescription()) &&
                Objects.equals(getManagers(), sourceProject.getManagers());
    }
}
