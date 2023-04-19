package ru.croc.javaschool.homework6.data.marshalling;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Люди.
 *
 * @author Artem Isaev
 */
@XmlRootElement(name = "people")
public class People {
    /**
     * Список людей.
     */
    @XmlElement(name = "person")
    private List<Person> people;

    /**
     * Конструктор, необходимый для JAXB.
     */
    public People() {
    }

    /**
     * Создает {@link People}.
     *
     * @param people список людей.
     */
    public People(List<Person> people) {
        this.people = people;
    }

    /**
     * Список людей.
     *
     * @return список людей.
     */
    public List<Person> getPeople() {
        return people;
    }
}
