package ru.croc.javaschool.homework4.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Тест для {@link Department}.
 *
 * @author Artem Isaev
 */
public class DepartmentTest {
    /**
     * departmentForSort - экземпляр класса {@link Department}, предназначенный для тестирования сортировки списка
     * руководителей по количеству подчиненных и имени.
     */
    private Department departmentForSort;
    /**
     * Один из руководителей в departmentForSort.
     */
    private Employee john = new Employee(1, "John");
    /**
     * Один из руководителей в departmentForSort.
     */
    private Employee saul = new Employee(7, "Saul");
    /**
     * Один из руководителей в departmentForSort.
     */
    private Employee aab = new Employee(3, "AAb");
    /**
     * Один из руководителей в departmentForSort.
     */
    private Employee aac = new Employee(8, "AAc");


    /**
     * departmentForSplit - экземпляр класса {@link Department}, предназначенный для тестирования разделения
     * сотрудников по организациям.
     */
    private Department departmentForSplit;
    /**
     * Руководитель в departmentForSplit.
     */
    private Employee chief1 = new Employee(100, "Cheif1");
    /**
     * Руководитель в departmentForSplit.
     */
    private Employee chief2 = new Employee(101, "Cheif2");
    /**
     * Руководитель в departmentForSplit.
     */
    private Employee chief3 = new Employee(102, "Chief3");
    /**
     * Сотрудник в departmentForSplit.
     */
    private Employee worker1 = new Employee(103, "Worker1");
    /**
     * Сотрудник в departmentForSplit.
     */
    private Employee worker2 = new Employee(104, "Worker2");
    /**
     * Список сотрудников, который подается на вход программе.
     */
    private List<Employee> employeesForSort, employeesForSplit;

    /**
     * Начальная инициализация объектов для проведения дальнейших манипуляций.
     */
    @BeforeEach
    public void init() {
        Employee emp2 = new Employee(2, "Mike");
        Employee emp4 = new Employee(4, "Colt");
        Employee emp5 = new Employee(5, "Solar");
        Employee emp6 = new Employee(6, "Bob");
        Employee emp9 = new Employee(9, "Kim");
        Employee emp10 = new Employee(10, "Howard");
        Employee emp11 = new Employee(11, "Maria");

        emp2.setManager(john);
        aab.setManager(john);
        emp4.setManager(aab);
        emp5.setManager(aab);
        emp6.setManager(aab);
        aac.setManager(saul);
        emp9.setManager(aac);
        emp10.setManager(aac);
        emp11.setManager(aac);

        employeesForSort = new ArrayList<>(List.of(
                aab, emp2, emp9, john, emp10, saul, aac, emp11, emp4, emp5, emp6));


        departmentForSort = new Department(employeesForSort);
        //--------------------------------------------------

        worker1.setManager(chief1);
        worker2.setManager(chief2);

        employeesForSplit = new ArrayList<>(List.of(
                chief1, chief2, chief3, worker1, worker2));

        departmentForSplit = new Department(employeesForSplit);
    }

    /**
     * Тест сортировки руководителей по подчиненным и имени.
     */
    @Test
    public void getSortedMapTest() {
        Map<Employee, Integer> expectedMap = new LinkedHashMap<>();
        expectedMap.put(john, 5);
        expectedMap.put(saul, 4);
        expectedMap.put(aab, 3);
        expectedMap.put(aac, 3);
        Assertions.assertEquals(expectedMap, departmentForSort.getSortedMap());
    }

    /**
     * Тест разделения сотрудников по организациям.
     */
    @Test
    public void splitEmployeesTest() {
        List<List<Employee>> testOrganizationsList = departmentForSplit.getOrganizationsList();
        List<Employee> org1 = new ArrayList<>();
        org1.add(chief1);
        org1.add(worker1);
        List<Employee> org2 = new ArrayList<>();
        org2.add(chief2);
        org2.add(worker2);
        List<Employee> org3 = new ArrayList<>();
        org3.add(chief3);
        List<List<Employee>> expectedOrgs = new ArrayList<>();
        expectedOrgs.add(org1);
        expectedOrgs.add(org2);
        expectedOrgs.add(org3);
        Assertions.assertEquals(expectedOrgs, testOrganizationsList);
    }

}
