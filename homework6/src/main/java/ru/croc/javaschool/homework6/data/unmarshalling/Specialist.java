package ru.croc.javaschool.homework6.data.unmarshalling;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

/**
 * Специалист.
 *
 * @author Artem Isaev
 */
public class Specialist {
    /**
     * Имя.
     */
    @XmlAttribute(name = "name")
    private String name;

    /**
     * Конструктор, необходимый для JAXB.
     */
    public Specialist() {
    }

    /**
     * Создаёт {@link Specialist}.
     *
     * @param name имя.
     */
    public Specialist(String name) {
        this.name = name;
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
        if (!(o instanceof Specialist)) {
            return false;
        }
        Specialist specialist = (Specialist) o;
        return Objects.equals(getName(), specialist.getName());
    }

}
