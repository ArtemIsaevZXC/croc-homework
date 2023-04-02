package ru.croc.javaschool.homework3.vehicles;

import ru.croc.javaschool.homework3.vehicles.automobiles.PassengerCar;
import ru.croc.javaschool.homework3.vehicles.individual.Scooter;
import ru.croc.javaschool.homework3.vehicles.individual.Unicycle;

import java.time.LocalDate;

/**
 * Тестовый класс для демонстрации сценария.
 *
 * @author Artem Isaev
 */
public class AccountingTest {
    public static void main(String[] args) {
        // Создаю свои транспортные средства.
        PassengerCar car1 = new PassengerCar(1);
        PassengerCar car2 = new PassengerCar(2);
        PassengerCar car3 = new PassengerCar(3);
        Scooter scooter1 = new Scooter(4);
        Unicycle unicycle1 = new Unicycle(5);

        // Создаю учёт с 10ю рандомными ТС.
        Accounting accounting;
        accounting = new Accounting(10);

        // Добавляю свои ТС в учёт
        accounting.addNew(car1); // теперь id = 11 в учете
        accounting.addNew(car2); // теперь id = 12 в учете
        accounting.addNew(car3);
        accounting.addNew(scooter1);
        accounting.addNew(unicycle1);

        // Создаю даты аренды
        LocalDate date1 = LocalDate.of(2023, 4, 1); // 01.04.2023
        LocalDate date2 = date1.plusDays(100); // 10.07.2023

        // Арендую
        accounting.rentVehicle(car1, date1, date2); // 01.04.2023 - 10.07.2023 (удачно)

        // Создаю другие даты.
        LocalDate date3 = date2.plusDays(10);  // 20.07.2023
        LocalDate date4 = date3.plusDays(120); // 17.11.2023

        // Пробую арендовать ту же машину
        accounting.rentVehicle(car1, date3, date4); // 20.07.2023 - 17.11.2023 (удачно)
        accounting.rentVehicle(car1, date1, date4); // 01.04.2023 - 17.11.2023 (неудачно)

        // Теперь другую
        accounting.rentVehicle(car2, date1, date4); // 01.04.2023 - 17.11.2023 (удачно)

        // Электросамокат сломался :(
        accounting.setBrokenAndRemove(scooter1);

        // Вывожу отчет на 12.07.2023 (car1 свободна, т.к. ее первая аренда заканчивается 10.07.2023, а вторая
        // начинается 20.07.2023 => будет арендована только car2 (01.04.2023 - 17.23.2023)
        accounting.createReport(date2.plusDays(2));

        // Вывожу свободные автомобили на 12.07.2023 - 14.07.2023
        // car1 с id 11 будет свободна, а car2 с id 12 будет арендована в это время.
        accounting.findFreeWithCategories(date2.plusDays(2), date2.plusDays(4));
    }
}
