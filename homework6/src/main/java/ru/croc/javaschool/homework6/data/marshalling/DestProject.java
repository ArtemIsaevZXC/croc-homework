package ru.croc.javaschool.homework6.data.marshalling;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

/**
 * Проект для преобразования.
 *
 * @author Artem Isaev.
 */
public class DestProject {
    /**
     * Название.
     */
    @XmlAttribute(name = "title")
    private String title;
    /**
     * Должность человека.
     */
    @XmlElement(name = "role")
    private String role;
    /**
     * Руководитель.
     */
    @XmlElement(name = "manager")
    private String manager;

    /**
     * Конструктор, необходимый для JAXB.
     */
    public DestProject() {
    }

    /**
     * Создает {@link DestProject}.
     *
     * @param title   название.
     * @param role    должность человека.
     * @param manager руководитель.
     */
    public DestProject(String title, String role, String manager) {
        this.title = title;
        this.role = role;
        this.manager = manager;
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
     * Должность человека.
     *
     * @return должность человека.
     */
    public String getRole() {
        return role;
    }

    /**
     * Руководитель.
     *
     * @return руководитель.
     */
    public String getManager() {
        return manager;
    }

    /**
     * Переопределение метода equals.
     * @param o объект, с которым необходимо сравнивать.
     * @return результат сравнения.
     */
    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if (!(o instanceof DestProject)) {
            return false;
        }
        DestProject destProject = (DestProject) o;
        return Objects.equals(getTitle(), destProject.getTitle()) &&
                Objects.equals(getRole(), destProject.getRole()) &&
                Objects.equals(getManager(), destProject.getManager());
    }
}
