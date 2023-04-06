package ru.croc.javaschool.homework4.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework4.task1.Employee;

/**
 * Тест для {@link Employee}.
 *
 * @author Artem Isaev
 */
public class EmployeeTest {
    /**
     * Сотрудники.
     */
    private Employee emp1, emp2, emp3, emp4;

    /**
     * Начальная инициализация объектов для проведения дальнейших манипуляций.
     */
    @BeforeEach
    public void init() {
        emp1 = new Employee(1, "Bob");
        emp2 = new Employee(2, "Jack");
        emp3 = new Employee(3, "Jack");
        emp4 = new Employee(4, "Arthur");
    }

    /**
     * Тест метода, сравнивающего сотрудников по именам. Если имена одинаковые, сравниваются уникальные
     * идентификаторы.
     */
    @Test
    public void compareToTest() {
        int result1 = emp1.compareTo(emp2);
        int result2 = emp2.compareTo(emp3);
        int result3 = emp4.compareTo(emp1);
        Assertions.assertEquals(1, result1);
        Assertions.assertEquals(-1, result2);
        Assertions.assertEquals(1, result3);
    }
}
