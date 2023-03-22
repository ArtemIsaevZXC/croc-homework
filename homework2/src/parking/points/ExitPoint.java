package parking.points;

import parking.entities.Car;

/**
 * Пункт выезда.
 *
 * @author Artem Isaev
 */
public class ExitPoint {
    /**
     * Номер пункта выезда.
     */
    private int number;
    /**
     * Описание пункта выезда.
     */
    private String description;
    /**
     * Список автомобилей, проехавших через данный выезд.
     */
    private Car[] carsList = new Car[1];
    /**
     * Индекс, необходимый для заполнения carsList.
     */
    private int carsListIndex = 0;

    /**
     * Создает {@link ExitPoint}.
     *
     * @param number      Номер пункта выезда.
     * @param description Описание пункта выезда.
     */
    public ExitPoint(int number, String description) {
        this.number = number;
        this.description = description;
    }

    /**
     * Заполнение списка, хранящего автомобили, которые проехали через этот пункт выезда.
     *
     * @param car автомобиль.
     */
    public void addToCarsList(Car car) {
        if (carsListIndex == (carsList.length) - 1) {
            Car[] newCarsList = new Car[(int) (carsList.length + 1)];
            System.arraycopy(carsList, 0, newCarsList, 0, carsList.length);
            this.carsList = newCarsList;
        }
        carsList[carsListIndex] = car;
        carsListIndex++;
    }

    /**
     * Номер пункта выезда.
     *
     * @return номер пункта выезда.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Вывод информации об автомобилях, проехавших через определенный пункт въезда.
     */
    public void printCarsList() {
        for (int i = 0; i < carsList.length - 1; i++) {
            System.out.println("Автомобиль с номером " + carsList[i].getCarNumber() + " выезжал через " + description);
        }
    }
}
