package parking.entities;

import parking.action.EntryAttempt;
import parking.points.EntryPoint;
import parking.points.ExitPoint;

/**
 * Автомобильная парковка.
 *
 * @author Artem Isaev
 */
public class ParkingArea {
    /**
     * Список пунктов въезда.
     */
    private EntryPoint[] entryPoints;
    /**
     * Список пунктов выезда.
     */
    private ExitPoint[] exitPoints;
    /**
     * Общее количество парковочных мест.
     */
    private int parkingLots;
    /**
     * Количество свободных мест на парковке.
     */
    private int freeParkingLots;
    /**
     * Список попыток, когда машина пыталась совершить въезд на парковку.
     */
    private EntryAttempt[] entryAttempts = new EntryAttempt[1];
    /**
     * Индекс, необходимый для заполнения entryAttempts.
     */
    private int attemptIndex = 0;

    /**
     * Создаёт {@link ParkingArea}. В свою очередь {@link ParkingArea} заполняет массивы
     * entryPoints и exitPoints объектами {@link EntryPoint} и {@link ExitPoint} соответственно.
     *
     * @param entryAmount Количество пунктов въезда.
     * @param exitAmount  Количество пунктов выезда.
     * @param parkingLots Общее количество парковочных мест.
     */
    public ParkingArea(int entryAmount, int exitAmount, int parkingLots) {
        freeParkingLots = parkingLots;
        entryPoints = new EntryPoint[entryAmount];
        for (int i = 0; i < entryAmount; i++) {
            EntryPoint point = new EntryPoint(i + 1, i % 2 == 0 ?
                    (i + 1) + "-й северный въезд." : (i + 1) + "-й южный въезд");
            entryPoints[i] = point;
        }
        exitPoints = new ExitPoint[exitAmount];
        for (int i = 0; i < exitAmount; i++) {
            ExitPoint point = new ExitPoint(i + 1, i % 2 == 0 ?
                    (i + 1) + "-й северный выезд." : (i + 1) + "-й южный выезд");
            exitPoints[i] = point;
        }
    }

    /**
     * Въезд автомобиля с номером XXX через определенный въезд.
     * Создается объект класса {@link EntryAttempt}, необходимый для хранения
     * списка попыток автомобилей въехать на парковку.
     *
     * @param car   автомобиль.
     * @param point определенный въезд.
     */
    public void makeEntry(Car car, EntryPoint point) {
        if (attemptIndex == entryAttempts.length) {
            EntryAttempt[] newEntryAttempts = new EntryAttempt[(int) (entryAttempts.length + 1)];
            System.arraycopy(entryAttempts, 0, newEntryAttempts, 0, entryAttempts.length);
            this.entryAttempts = newEntryAttempts;
        }
        if (hasFreeParkingLots()) {
            entryAttempts[attemptIndex] = new EntryAttempt(car, true);
            attemptIndex++;
            point.addToCarsList(car);
            freeParkingLots--;
        } else {
            entryAttempts[attemptIndex] = new EntryAttempt(car, false);
            attemptIndex++;
        }
    }

    /**
     * Выезд автомобиля с номером XXX через определенный выезд.
     *
     * @param car   автомобиль.
     * @param point определенный выезд.
     */
    public void makeExit(Car car, ExitPoint point) {
        point.addToCarsList(car);
        freeParkingLots++;
    }

    /**
     * Узнать количество свободных мест на парковке.
     *
     * @return количество свободных мест на парковке.
     */
    public int getFreeParkingLots() {
        return freeParkingLots;
    }

    /**
     * Узнать сможет автомобиль заехать на парковку или нет.
     *
     * @return возможность въезда.
     */
    public boolean hasFreeParkingLots() {
        if (freeParkingLots > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Узнать список автомобилей, проехавших через указанный въезд.
     *
     * @param entryPoint указанный въезд.
     */
    public void getEntryCarsList(EntryPoint entryPoint) {
        for (EntryPoint point : entryPoints) {
            if (point.getNumber() == entryPoint.getNumber()) {
                point.printCarsList();
                break;
            }
        }
    }

    /**
     * Узнать список автомобилей, проехавших через указанный выезд.
     *
     * @param exitPoint указанный выезд.
     */
    public void getExitCarsList(ExitPoint exitPoint) {
        for (ExitPoint point : exitPoints) {
            if (point.getNumber() == exitPoint.getNumber()) {
                point.printCarsList();
                break;
            }
        }
    }

    /**
     * Узнать список попыток, с указанием номера машины и времени,
     * когда машина не смогла попасть на парковку.
     */
    public void getFailedEntryAttempts() {
        for (int i = 0; i < entryAttempts.length; i++) {
            if (entryAttempts[i].isSuccessful() == false) {
                entryAttempts[i].printInfo();
            }
        }
    }

    /**
     * Список пунктов въезда.
     *
     * @return список пунктов въезда.
     */
    public EntryPoint[] getEntryPoints() {
        return entryPoints;
    }

    /**
     * Список пунктов выезда.
     *
     * @return список пунктов выезда.
     */
    public ExitPoint[] getExitPoints() {
        return exitPoints;
    }
}
