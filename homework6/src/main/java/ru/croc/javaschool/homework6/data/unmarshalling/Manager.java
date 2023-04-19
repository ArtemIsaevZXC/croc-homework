package ru.croc.javaschool.homework6.data.unmarshalling;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.Objects;

/**
 * Менеджер.
 *
 * @author Artem Isaev.
 */
public class Manager {
    /**
     * Имя.
     */
    @XmlAttribute(name = "name")
    private String name;
    /**
     * Список специалистов.
     */
    @XmlElementWrapper(name = "specialists")
    @XmlElement(name = "specialist")
    private List<Specialist> specialists;

    /**
     * Конструктор, необходимый для JAXB.
     */
    public Manager() {
    }

    /**
     * Создает {@link Manager}.
     *
     * @param name        имя.
     * @param specialists список специалистов.
     */
    public Manager(String name, List<Specialist> specialists) {
        this.name = name;
        this.specialists = specialists;
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
     * Список специалистов.
     *
     * @return список специалистов.
     */
    public List<Specialist> getSpecialists() {
        return specialists;
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
        if (!(o instanceof Manager)) {
            return false;
        }
        Manager manager = (Manager) o;
        return Objects.equals(getName(), manager.getName()) &&
                Objects.equals(getSpecialists(), manager.getSpecialists());
    }
}
