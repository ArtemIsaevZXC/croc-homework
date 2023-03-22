import parking.entities.Car;
import parking.entities.ParkingArea;

import java.util.Random;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Случайная переменная нужна для распределения автомобилей по въездам/выездам.
        Random rand = new Random();

        /* Создается парковка с 5-ю въездами, 3-мя выездами.
        Общее количество парковочных места - 11. */
        ParkingArea parking = new ParkingArea(5, 3, 11);
        int carsAmount = 16;

        // Въезд 16-ти автомобилей.
        System.out.println("ПЕРВЫЙ ВЪЕЗД");
        for (int i = 1; i <= carsAmount; i++) {
            int randomNum = rand.nextInt((4 - 0) + 1);
            Car car = null;
            if (parking.hasFreeParkingLots()) {
                System.out.println("Автомобиль заехал на парковку. Количество свободных мест: " + parking.getFreeParkingLots());
            } else {
                System.out.println("Попытка въезда... Нет свободных мест!");
            }
            if (i >= 100) {
                car = new Car(String.valueOf(i));
            }
            if (i % 10 == i) {
                car = new Car(String.valueOf("00" + i));
            } else if (i % 100 == i) {
                car = new Car(String.valueOf("0" + i));
            }
            parking.makeEntry(car, parking.getEntryPoints()[randomNum]);
        }
        System.out.println();

        // Узнаем неудачные попытки въезда.
        parking.getFailedEntryAttempts();
        System.out.println();

        // Узнаем список машин, проехавших через указанные ВЪЕЗДЫ.
        for (int i = 0; i < 5; i++) {
            parking.getEntryCarsList(parking.getEntryPoints()[i]);
        }
        System.out.println();

        // Выезд половины машин
        System.out.println("ПЕРВЫЙ ВЫЕЗД.");
        for (int i = 1; i <= carsAmount / 2; i++) {
            int randomNum = rand.nextInt((2 - 0) + 1);
            Car car = null;
            if (i >= 100) {
                car = new Car(String.valueOf(i));
            }
            if (i % 10 == i) {
                car = new Car(String.valueOf("00" + i));
            } else if (i % 100 == i) {
                car = new Car(String.valueOf("0" + i));
            }
            parking.makeExit(car, parking.getExitPoints()[randomNum]);
            System.out.println("Автомобиль выехал с парковки. Количество свободных мест: " + parking.getFreeParkingLots());
        }
        System.out.println();

        // Узнаем список машин, проехавших через указанные ВЫЕЗДЫ.
        for (int i = 0; i < 3; i++) {
            parking.getExitCarsList(parking.getExitPoints()[i]);
        }

        // Задержка выполнения кода нужна для наглядности при вызове метода getFailedEntryAttempts().
        Thread.sleep(2000);
        System.out.println();

        // Новый въезд автомобилей с другими номерами.
        System.out.println("ВТОРОЙ ВЪЕЗД.");
        for (int i = 1 + 100; i <= carsAmount + 100; i++) {
            int randomNum = rand.nextInt((4 - 0) + 1);
            Car car = null;
            if (parking.hasFreeParkingLots()) {
                System.out.println("Количество свободных мест: " + parking.getFreeParkingLots());
            } else {
                System.out.println("Попытка въезда... Нет свободных мест!");
            }
            if (i >= 100) {
                car = new Car(String.valueOf(i));
            }
            if (i % 10 == i) {
                car = new Car(String.valueOf("00" + i));
            } else if (i % 100 == i) {
                car = new Car(String.valueOf("0" + i));
            }
            parking.makeEntry(car, parking.getEntryPoints()[randomNum]);
        }
        System.out.println();

        // Снова узнаем обновленные неудачные попытки выезда.
        parking.getFailedEntryAttempts();
    }
}
