package ru.croc.javaschool.homework3.vehicles.individual;

/**
 * Электросамокат.
 * <p>
 * Замечание: содержит косметические поля и методы,
 * которые никак не участвуют в функционале программы.
 *
 * @author Artem Isaev
 */
public class Scooter extends Individual {
    /**
     * Заряд.
     */
    private boolean charged;

    /**
     * Создает {@link Scooter}.
     *
     * @param id идентификатор ТС.
     */
    public Scooter(int id) {
        super(id);
    }

    /**
     * Проверяет, заряжен ли электросамокат.
     *
     * @return заряд.
     */
    public boolean isCharged() {
        return charged;
    }

    /**
     * Делает электросамокат заряженным.
     */
    public void setCharged() {
        this.charged = true;
    }

    /**
     * Делает электросамокат разряженным.
     */
    public void setNoCharge() {
        this.charged = false;
    }
}
