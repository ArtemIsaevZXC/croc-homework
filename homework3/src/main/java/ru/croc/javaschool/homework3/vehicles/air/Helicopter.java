package ru.croc.javaschool.homework3.vehicles.air;

/**
 * Вертолет.
 * <p>
 * Замечание: содержит косметические поля и методы,
 * которые никак не участвуют в функционале программы.
 *
 * @author Artem Isaev
 */
public class Helicopter extends AirTransport {
    /**
     * Количество лопастей.
     */
    private int blades;

    /**
     * Создает {@link Helicopter}.
     *
     * @param id идентификатор ТС.
     */
    public Helicopter(int id) {
        super(id);
    }

    /**
     * Количество лопастей.
     *
     * @return количество лопастей.
     */
    public int getBlades() {
        return blades;
    }

    /**
     * Устанавливает количество лопастей
     *
     * @param blades количество лопастей.
     */
    public void setBlades(int blades) {
        if (blades == 4)
            this.blades = blades;
        if (blades == 2)
            this.blades = blades;
    }
}
