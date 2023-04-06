package ru.croc.javaschool.homework4.task1;

/**
 * Сотрудник.
 *
 * @author Artem Isaev
 */
public class Employee implements Comparable<Employee> {
    /**
     * Уникальный идентификатор.
     */
    private int id;
    /**
     * Имя.
     */
    private String name;
    /**
     * Руководитель.
     */
    private Employee manager;

    /**
     * Создает {@link Employee}.
     *
     * @param id   уникальный идентификатор.
     * @param name имя.
     */
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
        this.manager = null;
    }

    /**
     * Назначает руководителя этому работнику.
     *
     * @param manager руководитель.
     */
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    /**
     * Имя.
     *
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Узнать руководителя.
     *
     * @return руководитель.
     */
    public Employee getManager() {
        return manager;
    }

    /**
     * Идентификатор сотрудника.
     *
     * @return идентификатор.
     */
    public int getId() {
        return id;
    }

    /**
     * Реализация интерфейса Comparable. Необходим для сравнения сотрудников по имени. Если имена одинаковые,
     * сравнивает по id.
     *
     * @param emp сотрудник, с которым необходимо сравнивать.
     * @return результат сравнения двух сотрудников.
     */
    @Override
    public int compareTo(Employee emp) {
        int result = emp.getName().compareToIgnoreCase(this.getName());
        if (result > 0) {
            return 1;
        }
        if (result < 0) {
            return -1;
        } else {
            return Integer.compare(this.getId(), emp.getId());
        }
    }
}
