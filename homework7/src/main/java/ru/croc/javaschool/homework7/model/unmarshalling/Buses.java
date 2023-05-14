package ru.croc.javaschool.homework7.model.unmarshalling;

import ru.croc.javaschool.homework7.model.Bus;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * Автобусы.
 *
 * @author Artem Isaev
 */
@XmlRootElement(name = "buses")
public class Buses {
    /**
     * Список автобусов.
     */
    @XmlElement(name = "bus")
    private List<Bus> buses;

    /**
     * Конструктор, необходимый для JAXB.
     */
    public Buses() {
    }

    /**
     * Создаёт {@link Buses}.
     *
     * @param buses список автобусов.
     */
    public Buses(List<Bus> buses){
        this.buses = buses;
    }

    /**
     * Список автобусов.
     *
     * @return список автобусов.
     */
    public List<Bus> getBuses() {
        return buses;
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
        if (!(o instanceof Buses)) {
            return false;
        }
        Buses busesToCompare = (Buses) o;
        return Objects.equals(getBuses(), busesToCompare.getBuses());
    }
}
