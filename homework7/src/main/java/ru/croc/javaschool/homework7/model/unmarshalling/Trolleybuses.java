package ru.croc.javaschool.homework7.model.unmarshalling;

import ru.croc.javaschool.homework7.model.Trolleybus;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Троллейбусы.
 *
 * @author Artem Isaev
 */
@XmlRootElement(name = "trolleybuses")
public class Trolleybuses{
    /**
     * Список троллейбусов.
     */
    @XmlElement(name = "trolleybus")
    private List<Trolleybus> trolleybuses;

    /**
     * Конструктор, необходимый для JAXB.
     */
    public Trolleybuses() {
    }

    /**
     * Создаёт {@link Trolleybuses}.
     *
     * @param trolleybuses список троллейбусов.
     */
    public Trolleybuses(List<Trolleybus> trolleybuses){
        this.trolleybuses = trolleybuses;
    }

    /**
     * Список троллейбусов.
     *
     * @return список троллейбусов.
     */
    public List<Trolleybus> getTrolleybuses() {
        return trolleybuses;
    }
}
