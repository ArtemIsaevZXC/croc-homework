package ru.croc.javaschool.homework6.tools;

import ru.croc.javaschool.homework6.data.marshalling.DestProject;
import ru.croc.javaschool.homework6.data.marshalling.People;
import ru.croc.javaschool.homework6.data.marshalling.Person;
import ru.croc.javaschool.homework6.data.unmarshalling.Manager;
import ru.croc.javaschool.homework6.data.unmarshalling.Projects;
import ru.croc.javaschool.homework6.data.unmarshalling.SourceProject;
import ru.croc.javaschool.homework6.data.unmarshalling.Specialist;

import java.io.IOException;
import java.util.*;

/**
 * Преобразователь информации по проектам.
 *
 * @author Artem Isaev.
 */
public class ProjectsDataConverter {
    /**
     * Преобразует входящие данные о проектах в требуемые данные о людях.
     * @param src входящая строка с данными о проектах.
     * @return строка с данными о людях в формате xml.
     * @throws IOException
     */
    public static String convert(String src) throws IOException {
        XmlConverter xmlConverter = new XmlConverter();
        Projects projects = xmlConverter.fromXml(src, Projects.class);
        Map<String, Person> peopleMap = new TreeMap<>();
        // обхожу проекты
        for (SourceProject sourceProject : projects.getProjects()) {
            // обхожу менеджеров
            for (Manager manager : sourceProject.getManagers()) {
                // обхожу специалистов
                for (Specialist specialist : manager.getSpecialists()) {
                    Person person;
                    // проверка на наличие человека с такими именем в Map
                    if (!peopleMap.containsKey(specialist.getName())) {
                        person = new Person(specialist.getName());      // создаю его
                    } else {
                        person = peopleMap.get(specialist.getName());      // достаю его из Map
                    }
                    DestProject destProject = new DestProject(          // фиксирую проект, в котором он участвует
                            sourceProject.getTitle(),
                            "Специалист",
                            manager.getName()
                    );
                    person.addProject(destProject);                     // добавляю в его список проектов
                    peopleMap.put(specialist.getName(), person);           // добавляю/обновляю данные человека в Map
                }
                // аналогичная обработка менеджера
                Person person;
                if (!peopleMap.containsKey(manager.getName())) {
                    person = new Person(manager.getName());
                } else {
                    person = peopleMap.get(manager.getName());
                }
                DestProject destProject = new DestProject(
                        sourceProject.getTitle(),
                        "Менеджер",
                        ""
                );
                person.addProject(destProject);
                peopleMap.put(manager.getName(), person);
            }
        }
        People result = new People(new ArrayList<>(peopleMap.values()));
        return xmlConverter.toXml(result);
    }

}
