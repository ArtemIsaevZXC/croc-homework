package parking.points;


import parking.entities.Car;

/**
 * Пункт въезда.
 *
 * @author Artem Isaev
 */
public class EntryPoint {
    /**
     * Номер пункта въезда.
     */
    private int number;
    /**
     * Описание пункта въезда.
     */
    private String description;
    /**
     * Список автомобилей, проехавших через данный въезд.
     */
    private Car[] carsList = new Car[1];
    /**
     * Индекс, необходимый для заполнения carsList.
     */
    private int carsListIndex = 0;

    /**
     * Создает {@link EntryPoint}.
     *
     * @param number      Номер пункта въезда.
     * @param description Описание пункта въезда.
     */
    public EntryPoint(int number, String description) {
        this.number = number;
        this.description = description;
    }

    /**
     * Заполнение списка, хранящего автомобили, которые проехали через этот пункт въезда.
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
     * Номер пункта въезда.
     *
     * @return номер пункта въезда.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Вывод информации об автомобилях, проехавших через определенный пункт въезда.
     */
    public void printCarsList() {
        for (int i = 0; i < carsList.length - 1; i++) {
            System.out.println("Автомобиль с номером " + carsList[i].getCarNumber() + " въезжал через " + description);
        }
    }
}
