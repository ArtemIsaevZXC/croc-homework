package ru.croc.javaschool.homework4.task1;

import java.util.*;

/**
 * Отдел.
 *
 * @author Artem Isaev
 */
public class Department {

    /**
     * Список всех сотрудников.
     */
    private List<Employee> employees;
    /**
     * Список разных организации на основе главных руководителей.
     */
    private List<List<Employee>> organizationsList = new ArrayList<>();

    /**
     * Создает {@link Department}.
     *
     * @param employees список сотрудников.
     */
    public Department(List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Находит разные организации на основе главных руководителей в списке всех сотрудников.
     */
    private void findOrganizations() {
        for (Employee emp : employees) {
            if (emp.getManager() == null) {            // если нет руководителя ( представитель ОТДЕЛЬНОЙ организации )
                List<Employee> organization = new ArrayList<>();
                organization.add(emp);
                organizationsList.add(organization);
            }
        }
    }

    /**
     * Разделяет сотрудников по отдельным организациям на основе их руководителей.
     */
    private void splitEmployees() {
        findOrganizations();
        for (Employee emp : employees) {
            if (emp.getManager() != null) {                           // если есть руководитель
                Employee tmp = emp;                                    // делаю копию, чтобы добраться до руководителя
                while (tmp.getManager() != null) {                     // добираюсь до руководителя
                    tmp = tmp.getManager();
                }
                for (List<Employee> organization : organizationsList) { // сравниваю найденное руководство с уже записанным
                    if (tmp.getId() == organization.get(0).getId()) {   // если руководство совпадает
                        organization.add(emp);                          // записываю
                    }
                }
            }
        }
    }

    /**
     * Возвращает список организаций, с находящимися в них сотрудниками.
     *
     * @return список разных организаций с сотрудниками.
     */
    public List<List<Employee>> getOrganizationsList() {
        splitEmployees();
        return organizationsList;
    }

    /**
     * На основе списка сотрудников создает словарь, в котором ключ - сотрудник, значение - количество подчиненных.
     *
     * @param employees список всех сотрудников.
     * @return словарь всех сотрудников.
     */
    private Map<Employee, Integer> toMap(List<Employee> employees) {
        Map<Employee, Integer> employeeMap = new HashMap<>();
        for (Employee emp : employees) {
            employeeMap.put(emp, 0);
        }
        return employeeMap;
    }

    /**
     * Определяет количество подчиненных у каждого сотрудника.
     *
     * @return словарь вида Map<{@link Employee}, {@link Integer}>.
     */
    private Map<Employee, Integer> countSubs() {
        Map<Employee, Integer> employeesMap = toMap(employees);
        for (Map.Entry<Employee, Integer> current : employeesMap.entrySet()) {
            // сперва вычисляю количество подчиненных у всех, кроме самых главных руководителей
            if (current.getKey().getManager() != null) {
                current.setValue(subordinates(employeesMap, current.getKey()));
            }
        }
        for (Map.Entry<Employee, Integer> current : employeesMap.entrySet()) {
            // у самых главных руководителей
            if (current.getKey().getManager() == null) {
                current.setValue(subordinates(employeesMap, current.getKey()));
            }
        }
        return employeesMap;
    }

    /**
     * Вычисляет количество подчиненных у определенного сотрудника.
     *
     * @param map словарь сотрудников.
     * @param emp сотрудник, у которого необходимо посчитать количество подчиненных.
     * @return количество подчиненных.
     */
    private int subordinates(Map<Employee, Integer> map, Employee emp) {
        int amountSelf = 0; // непосредственные подчиненные
        int amountOthers = 0; // подчиненные подчиненных
        for (Map.Entry<Employee, Integer> other : map.entrySet()) {
            if (other.getKey().getManager() != null
                    && other.getKey().getManager().getId() == emp.getId()) {
                amountSelf++;
                amountOthers += other.getValue();
            }
        }
        return amountOthers + amountSelf;
    }

    /**
     * Возвращает отсортированный список руководителей.
     *
     * @return отсортированный список руководителей.
     */
    public Map<Employee, Integer> getSortedMap() {
        List<Employee> removeKeys = new ArrayList<>();
        Map<Employee, Integer> employees = countSubs();
        for (Map.Entry<Employee, Integer> pair : employees.entrySet()) {
            if (pair.getValue() == 0) {
                removeKeys.add(pair.getKey());
            }
        }
        employees.keySet().removeAll(removeKeys);                           // удаляю из map всех, у кого 0 подчиненных
        Map<Employee, Integer> almostSorted = new TreeMap<>(employees);     //по именам
        List<Map.Entry<Employee, Integer>> entries = new ArrayList<>(almostSorted.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Employee, Integer>>() {               //по значениям
            public int compare(Map.Entry<Employee, Integer> emp1, Map.Entry<Employee, Integer> emp2) {
                return emp2.getValue().compareTo(emp1.getValue());
            }
        });
        Map<Employee, Integer> sortedHashMap = new LinkedHashMap<>();
        for (Map.Entry<Employee, Integer> entry : entries) {
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

}
