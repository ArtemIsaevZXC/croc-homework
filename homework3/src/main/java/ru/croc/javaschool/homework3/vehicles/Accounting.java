package ru.croc.javaschool.homework3.vehicles;

import ru.croc.javaschool.homework3.vehicles.vehicle.Vehicle;
import ru.croc.javaschool.homework3.vehicles.vehicle.VehicleCategories;
import ru.croc.javaschool.homework3.vehicles.air.BusinessJet;
import ru.croc.javaschool.homework3.vehicles.air.Helicopter;
import ru.croc.javaschool.homework3.vehicles.automobiles.PassengerCar;
import ru.croc.javaschool.homework3.vehicles.automobiles.Truck;
import ru.croc.javaschool.homework3.vehicles.individual.Scooter;
import ru.croc.javaschool.homework3.vehicles.individual.Unicycle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Учёт транспортных средств.
 *
 * @author Artem Isaev
 */
public class Accounting {
    /**
     * Уникальный идентификатор ТС.
     */
    private int id = 1;
    /**
     * Список всех ТС, которыми располагает компания.
     */
    private List<Vehicle> vehicles = new ArrayList<>();
    /**
     * Список свободных ТС, который будет обновляться в соответствии с заданной датой.
     */
    private List<Vehicle> freeVehicles;
    /**
     * Список арендованных ТС, который будет обновляться в соответствии с заданной датой.
     */
    private List<Vehicle> rentedVehicles;

    /**
     * Создает {@link Accounting}.
     * Замечание: для разнообразия и простоты проведения демо-сценария заполняет список всех ТС компании
     * случайными типами транспортных средств.
     *
     * @param amount количество ТС, которыми располагает компания.
     */
    public Accounting(int amount) {
        for (int i = 0; i < amount; i++) {
            Random rnd = new Random();
            int num = rnd.nextInt(7 - 1) + 1;
            switch (num) {
                case 1:
                    PassengerCar car = new PassengerCar(id);
                    addNew(car);
                    break;
                case 2:
                    Truck truck = new Truck(id);
                    addNew(truck);
                    break;
                case 3:
                    Scooter scooter = new Scooter(id);
                    addNew(scooter);
                    break;
                case 4:
                    Unicycle uni = new Unicycle(id);
                    addNew(uni);
                    break;
                case 5:
                    BusinessJet bj = new BusinessJet(id);
                    addNew(bj);
                    break;
                case 6:
                    Helicopter heli = new Helicopter(id);
                    addNew(heli);
                    break;
            }
        }
    }

    /**
     * Добавление нового ТС в список всех ТС компании.
     * Замечание: здесь "родной" id ТС меняется на уникальный id для учёта.
     *
     * @param newVcl новое транспортное средство.
     */
    public void addNew(Vehicle newVcl) {
        newVcl.setId(id);
        id++;
        vehicles.add(newVcl);
    }

    /**
     * Вспомогательный метод удаления неисправного ТС из списка всех ТС компании.
     *
     * @param vcl неисправное ТС.
     * @return true если удаление прошло успешно, false иначе.
     */
    private boolean removeBroken(Vehicle vcl) {
        if (vehicles.contains(vcl)) {
            if (!vcl.isServiceable()) {
                vehicles.remove(vcl);
                return true;
            }
        }
        return false;
    }

    /**
     * Указание, что ТС стало неисправным и удаление его из общего списка ТС.
     *
     * @param vcl неисправное ТС.
     */
    public void setBrokenAndRemove(Vehicle vcl) {
        vcl.notServiceable();
        removeBroken(vcl);
    }


    /**
     * Вспомогательный метод, который находит арендованные ТС на заданные даты, обновляет список арендованных ТС.
     *
     * @param startDate начальная заданная дата.
     * @param endDate конечная заданная дата.
     */
    private void findRented(LocalDate startDate, LocalDate endDate) {
        findFree(startDate, endDate);
        List<Vehicle> rented = new ArrayList<>(vehicles);
        for (Vehicle free : freeVehicles) {
            rented.remove(free);
        }
        this.rentedVehicles = rented;
    }

    /**
     * Вспомогательный метод, который находит свободные ТС на заданные даты, обновляет список свободных ТС.
     *
     * @param startDate начальная заданная дата.
     * @param endDate конечная заданная дата.
     */
    private void findFree(LocalDate startDate, LocalDate endDate) {
        List<Vehicle> free = new ArrayList<>();
        for (Vehicle vcl : vehicles) {
            if (vcl.getStarts().isEmpty()) {               // ТС вообще не арендовано
                free.add(vcl);
            } else {                                        // иначе проверяем с прошлыми записями
                boolean isFree = true;
                for (int i = 0; i < vcl.getStarts().size(); i++) {
                    if (!((vcl.getStarts().get(i).isAfter(startDate) && vcl.getStarts().get(i).isAfter(endDate))
                            || (vcl.getEnds().get(i).isBefore(startDate) && vcl.getEnds().get(i).isBefore(endDate)))) {
                        isFree = false;
                        break;
                    }
                }
                if (isFree) {
                    free.add(vcl);
                }
            }
        }
        this.freeVehicles = free;
    }

    /**
     * Вспомогательный метод, который распределяет список всех
     * арендованных/свободных автомобилей по видам транспорта.
     *
     * @param vehicles список автомобилей.
     * @return список списков, которые представляют из себя виды ТС.
     */
    private List<List<Vehicle>> toCategories(List<Vehicle> vehicles) {
        List<List<Vehicle>> variousCategories = new ArrayList<>();
        List<Vehicle> passengerCars = new ArrayList<>();
        List<Vehicle> trucks = new ArrayList<>();
        List<Vehicle> scooters = new ArrayList<>();
        List<Vehicle> unicycles = new ArrayList<>();
        List<Vehicle> businessJets = new ArrayList<>();
        List<Vehicle> helicopters = new ArrayList<>();
        for (Vehicle vcl : vehicles) {
            if (vcl instanceof PassengerCar) {
                passengerCars.add(vcl);
            }
            if (vcl instanceof Truck) {
                trucks.add(vcl);
            }
            if (vcl instanceof Scooter) {
                scooters.add(vcl);
            }
            if (vcl instanceof Unicycle) {
                unicycles.add(vcl);
            }
            if (vcl instanceof BusinessJet) {
                businessJets.add(vcl);
            }
            if (vcl instanceof Helicopter) {
                helicopters.add(vcl);
            }
        }
        variousCategories.add(passengerCars);
        variousCategories.add(trucks);
        variousCategories.add(scooters);
        variousCategories.add(unicycles);
        variousCategories.add(businessJets);
        variousCategories.add(helicopters);
        return variousCategories;
    }

    /**
     * Аренда транспорта на заданные даты.
     *
     * @param vcl ТС которое нужно арендовать.
     * @param startDate дата начала аренды.
     * @param endDate дата конца аренды.
     */
    public void rentVehicle(Vehicle vcl, LocalDate startDate, LocalDate endDate) {
        findFree(startDate, endDate);
        findRented(startDate, endDate);
        List<Vehicle> freeVehicles = new ArrayList<>(this.freeVehicles);
        List<Vehicle> rentedVehicles = new ArrayList<>(this.rentedVehicles);
        Vehicle change = null;
        //поиск свободной машины на заданную дату
        for (Vehicle vehicle : freeVehicles) {
            if (vehicle.getId() == vcl.getId()) {
                vehicle.setRented(startDate, endDate);
                change = vehicle;
                break;
            }
        }
        // если нашлась
        if (change != null) {
            freeVehicles.remove(change);
            rentedVehicles.add(change);
            this.freeVehicles = freeVehicles;
            this.rentedVehicles = rentedVehicles;
            System.out.println("ТС " + change.getId() + " успешно арендовано!");
        }
        // если не нашлась
        else {
            for (Vehicle vehicle : rentedVehicles) {
                if (vehicle.getId() == vcl.getId()) {
                    System.out.println("ТС " + vcl.getId() + " уже арендовано на эту дату.");
                    break;
                }
            }
        }
    }

    /**
     * Поиск свободных транспортных средств на заданные даты в рамках категорий.
     *
     * @param startDate начальная заданная дата.
     * @param endDate конечная заданная дата.
     */
    public void findFreeWithCategories(LocalDate startDate, LocalDate endDate) {
        findFree(startDate, endDate);
        List<List<Vehicle>> categories = toCategories(freeVehicles);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String startDateText = dtf.format(startDate);
        String endDateText = dtf.format(endDate);
        String cat = null;
        for (int i = 0; i < 60; i++)
            System.out.print("=");
        System.out.println("\nСвободные ТС c " + startDateText +
                " до " + endDateText);
        for (int i = 0; i < categories.size(); i++) {
            if (!categories.get(i).isEmpty()) {
                switch (i) {
                    case 0:
                        cat = VehicleCategories.CAR.toString();
                        break;
                    case 1:
                        cat = VehicleCategories.TRUCK.toString();
                        break;
                    case 2:
                        cat = VehicleCategories.SCOOTER.toString();
                        break;
                    case 3:
                        cat = VehicleCategories.UNICYCLE.toString();
                        break;
                    case 4:
                        cat = VehicleCategories.BJ.toString();
                        break;
                    case 5:
                        cat = VehicleCategories.HELI.toString();
                        break;
                }
                System.out.println("\nКатегория: " + cat);
                for (Vehicle vcl : categories.get(i)) {
                    System.out.println("ТС " + vcl.getId());
                }
            }
        }
        for (int i = 0; i < 60; i++)
            System.out.print("=");
    }

    /**
     * Создание сводного отчёта на заданную дату.
     *
     * @param date заданная дата.
     */
    public void createReport(LocalDate date) {
        findFree(date, date);
        findRented(date, date);
        List<List<Vehicle>> freeCat = toCategories(freeVehicles);
        List<List<Vehicle>> rentedCat = toCategories(rentedVehicles);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String startDateText = dtf.format(date);
        for (int i = 0; i < 60; i++)
            System.out.print("=");
        System.out.println("\nОТЧЁТ " + startDateText + "\n");
        String bigCat, smallCat;
        for (int i = 0; i < freeCat.size(); i++) {
            switch (i) {
                case 0:
                    bigCat = VehicleCategories.AUTOMOBILES.toString();
                    smallCat = VehicleCategories.CAR.toString();
                    System.out.print(bigCat + "\n" + smallCat + ": Свободных: " + freeCat.get(i).size() +
                            " // Арендованных: " + rentedCat.get(i).size());
                    break;
                case 1:
                    smallCat = VehicleCategories.TRUCK.toString();
                    System.out.println(smallCat + ": Свободных: " + freeCat.get(i).size() +
                            " // Арендованных: " + rentedCat.get(i).size());
                    break;
                case 2:
                    bigCat = VehicleCategories.INDIVIDUAL.toString();
                    smallCat = VehicleCategories.SCOOTER.toString();
                    System.out.print(bigCat + "\n" + smallCat + ": Свободных: " + freeCat.get(i).size() +
                            " // Арендованных: " + rentedCat.get(i).size());
                    break;
                case 3:
                    smallCat = VehicleCategories.UNICYCLE.toString();
                    System.out.println(smallCat + ": Свободных: " + freeCat.get(i).size() +
                            " // Арендованных: " + rentedCat.get(i).size());
                    break;
                case 4:
                    bigCat = VehicleCategories.AIR.toString();
                    smallCat = VehicleCategories.BJ.toString();
                    System.out.print(bigCat + "\n" + smallCat + ": Свободных: " + freeCat.get(i).size() +
                            " // Арендованных: " + rentedCat.get(i).size());
                    break;
                case 5:
                    smallCat = VehicleCategories.HELI.toString();
                    System.out.println(smallCat + ": Свободных: " + freeCat.get(i).size() +
                            " // Арендованных: " + rentedCat.get(i).size());
                    break;
            }
            System.out.println();
        }
        for (int i = 0; i < 60; i++)
            System.out.print("=");
        System.out.println();
    }

}
