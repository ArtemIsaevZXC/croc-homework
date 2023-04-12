package ru.croc.javaschool.homework5;

import ru.croc.javaschool.homework5.exceptions.*;

import java.io.*;
import java.util.*;

/**
 * Система ведения задач.
 *
 * @author Artem Isaev
 */
public class TaskManagementSystem {
    /**
     * Путь до файла сохранения.
     */
    private static String SAVE_FILE_PATH;
    /**
     * Главное меню.
     */
    private final static String MAIN_MENU_MESSAGE = "Главное меню:\n" +
            "1. Меню управления задачами.\n" +
            "2. Сохранить данные.\n" +
            "3. Загрузить данные.\n" +
            "0. Выйти из программы.\n";
    /**
     * Меню управления задачами.
     */
    private final static String TMS_MENU_MESSAGE = "Меню управления задачами:\n" +
            "1. Добавить новую задачу.\n" +
            "2. Редактировать задачу.\n" +
            "3. Удалить задачу.\n" +
            "4. Показать список задач.\n" +
            "0. Вернуться в главное меню.\n";
    /**
     * Меню характеристик задачи.
     */
    private final static String ITEM_TO_CHANGE_MESSAGE = "Введите цифру, соответствующую" +
            " характеристике, которую необходимо изменить.\n" +
            "1. Наименование.\n" +
            "2. Описание.\n" +
            "3. Исполнитель.\n" +
            "4. Статус.\n";
    /**
     * Список задач.
     */
    private List<Task> tasksList = new ArrayList<>();
    /**
     * Множество кодов задач.
     */
    private Set<Integer> tasksCodes = new HashSet<>();

    /**
     * Основной метод-обработчик, осуществляющий взаимодействие пользователя с программой.
     */
    public void launch() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nВведите путь, где необходимо создать сохраняемый файл.\n" +
                "ex. C:\\IDEAProjects\\Croc\\homework5\\save.txt");
        String input = sc.nextLine();
        while ( !input.matches("([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?")){
            System.out.println("Введите корректный путь.");
            input = sc.nextLine();
        }
        SAVE_FILE_PATH = input;
        while (!input.equals("0")) {
            switch (input) {
                // меню управления задачами
                case "1": {
                    System.out.println(TMS_MENU_MESSAGE);
                    input = sc.nextLine();
                    while (!input.equals("0")) {
                        switch (input) {
                            //добавление новой задачи
                            case "1": {
                                Scanner scCreate = new Scanner(System.in);
                                System.out.println("Введите наименование задачи:");
                                String name = scCreate.nextLine();
                                System.out.println("Введите описание задачи:");
                                String description = scCreate.nextLine();
                                System.out.println("Введите исполнителя задачи:");
                                String executor = scCreate.nextLine();
                                createTask(name, description, executor);
                                System.out.println("Задача создана и добавлена в список задач.");
                                break;
                            }
                            // редактирование задачи
                            case "2": {
                                System.out.println("Список задач:");
                                try {
                                    showTasksList();
                                    Scanner scEdit = new Scanner(System.in);
                                    System.out.println("\nВведите номер задачи, которую необходимо отредактировать.");
                                    String code = scEdit.nextLine();
                                    while (!code.matches("\\d+")) {
                                        System.out.println("Введите целое число.");
                                        code = scEdit.nextLine();
                                    }
                                    Task taskToEdit = chooseTask(Integer.parseInt(code));
                                    System.out.println(ITEM_TO_CHANGE_MESSAGE);
                                    String item = scEdit.nextLine();
                                    while (!item.matches("[1-4]")) {
                                        System.out.println("Введите корректное значение.");
                                        item = scEdit.nextLine();
                                    }
                                    System.out.println("Введите новое значение изменяемой характеристики.");
                                    String newValue = scEdit.nextLine();
                                    editTask(taskToEdit, Integer.parseInt(item), newValue);
                                    System.out.println("Задача отредактирована.");
                                } catch (EmptyListException | NoSuchTaskException | InvalidStatusException |
                                         InvalidItemException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                            // удаление задачи
                            case "3": {
                                System.out.println("Список задач:");
                                try {
                                    showTasksList();
                                    Scanner scDelete = new Scanner(System.in);
                                    System.out.println("\nВведите номер задачи, которую необходимо удалить.");
                                    String code = scDelete.nextLine();
                                    while (!code.matches("\\d+")) {
                                        System.out.println("Введите целое число.");
                                        code = scDelete.nextLine();
                                    }
                                    deleteTask(Integer.parseInt(code));
                                    System.out.println("Задача удалена.");
                                } catch (EmptyListException | NoSuchTaskException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                            // показать список задач
                            case "4": {
                                System.out.println("Список задач:");
                                try {
                                    showTasksList();
                                } catch (EmptyListException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                        }
                        System.out.println("\n" + TMS_MENU_MESSAGE);
                        input = sc.nextLine();
                    }
                    break;
                }
                // сохранить данные в файл
                case "2": {
                    try {
                        saveToFile();
                        System.out.println("Данные сохранены.");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                // загрузить данные из файла
                case "3": {
                    try {
                        loadFromFile();
                        System.out.println("Данные загружены из файла сохранения.");
                    } catch (NoSuchFileException | EmptyLoadFileException e) {
                        System.out.println("Данные загрузить не удалось. " + e.getMessage());
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
            }
            System.out.println(MAIN_MENU_MESSAGE);
            input = sc.nextLine();
        }
        System.out.println("Завершение программы...");
    }

    /**
     * Создает новую задачу. Т.к. необходимо добиться уникальности кодов задач,
     * у новосозданной задачи происходит проверка на уникальность кода в множестве tasksCodes.
     * Если код такой задачи уже есть в множестве, производится попытка заново создать задачу.
     * В случае успеха добавляет задачу в список задач.
     *
     * @param name        наименование.
     * @param description описание.
     * @param executor    исполнитель.
     */
    private void createTask(String name, String description, String executor) {
        Task task = new Task(name, description, executor);
        while (tasksCodes.contains(task.getCode())) {
            task = new Task(name, description, executor);
        }
        tasksList.add(task);
        tasksCodes.add(task.getCode());
    }

    /**
     * Выводит задачи из списка задач.
     *
     * @throws EmptyListException список задач пуст.
     */
    private void showTasksList() throws EmptyListException {
        if (tasksList.size() != 0) {
            for (Task task : tasksList) {
                System.out.println(task);
            }
        } else {
            throw new EmptyListException();
        }
    }

    /**
     * Позволяет выбрать задачу по её коду.
     *
     * @param inputCode код задачи.
     * @return задача.
     * @throws NoSuchTaskException задачи с указанным кодом не существует.
     */
    private Task chooseTask(int inputCode) throws NoSuchTaskException {
        if (tasksCodes.contains(inputCode)) {
            for (Task task : tasksList) {
                if (task.getCode() == inputCode)
                    return task;
            }
        }
        throw new NoSuchTaskException();
    }

    /**
     * Удаляет выбранную задачу из списка задач по её коду.
     *
     * @param inputCode код задачи.
     * @throws NoSuchTaskException задачи с указанным кодом не существует.
     */
    private void deleteTask(int inputCode) throws NoSuchTaskException {
        if (tasksCodes.contains(inputCode)) {
            Iterator<Task> taskIterator = tasksList.iterator();
            while (taskIterator.hasNext()) {
                Task nextTask = taskIterator.next();
                if (nextTask.getCode() == inputCode) {
                    taskIterator.remove();
                    tasksCodes.remove(inputCode);
                }
            }
        } else {
            throw new NoSuchTaskException();
        }
    }

    /**
     * Изменяет характеристики переданной задачи.
     * Номера характеристик:
     * 1 - наименование
     * 2 - описание
     * 3 - исполнитель
     * 4 - статус
     *
     * @param task     изменяемая задача.
     * @param item     номер изменяемой характеристики.
     * @param newValue новое значение изменяемой характеристики.
     * @throws InvalidStatusException неверно указан изменяемый статус
     * @throws InvalidItemException   неверно указан номер изменяемой характеристики
     */
    private void editTask(Task task, int item, String newValue)
            throws InvalidStatusException, InvalidItemException {
        if (item > 0 && item < 5) {
            switch (item) {
                case 1: {
                    task.setName(newValue);
                    break;
                }
                case 2: {
                    task.setDescription(newValue);
                    break;
                }
                case 3: {
                    task.setExecutor(newValue);
                    break;
                }
                case 4: {
                    if (newValue.equalsIgnoreCase("НУЖНО СДЕЛАТЬ")
                            || newValue.equalsIgnoreCase("В РАБОТЕ")
                            || newValue.equalsIgnoreCase("ВЫПОЛНЕНО")) {
                        task.setStatus(newValue.toUpperCase());
                    } else {
                        throw new InvalidStatusException();
                    }
                    break;
                }
            }
        } else {
            throw new InvalidItemException();
        }
    }

    /**
     * Создает файл сохранения по указанному пути. Позволяет сохранить данные из tasksList в файл.
     *
     * @throws IOException - что-то пошло не так с файлом сохранения.
     */
    private void saveToFile() throws IOException {
        File saveFile = new File(SAVE_FILE_PATH);
        var fos = new FileOutputStream(saveFile);
        var bos = new BufferedOutputStream(fos);
        var oos = new ObjectOutputStream(bos);
        if (!saveFile.exists()) {
            saveFile.createNewFile();
        }
        oos.writeObject(tasksList);
        oos.close();
        bos.close();
        fos.close();
    }

    /**
     * Позволяет загрузить данные из файла.
     *
     * @throws NoSuchFileException    файла по такому пути не существует (т.е. если пользователь пытается
     *                                совершить загрузку раньше, чем было произведено сохранение).
     * @throws IOException            что-то пошло не так с файлом сохранения.
     * @throws ClassNotFoundException Class of a serialized object cannot be found.
     * @throws EmptyLoadFileException файл сохранения существует, но он абсолютно пуст.
     */
    private void loadFromFile() throws NoSuchFileException,
            IOException, ClassNotFoundException, EmptyLoadFileException {
        File loadFile = new File(SAVE_FILE_PATH);
        if (!loadFile.exists()) {
            throw new NoSuchFileException();
        }
        if (loadFile.length() == 0) {
            throw new EmptyLoadFileException();
        }
        var fis = new FileInputStream(loadFile);
        var bis = new BufferedInputStream(fis);
        var ois = new ObjectInputStream(bis);
        if (loadFile.length() == 0) {
            throw new EmptyLoadFileException();
        }
        List<Task> loadTaskList = (List<Task>) ois.readObject();
        Set<Integer> loadCodes = new HashSet<>();
        for (Task task : loadTaskList) {
            loadCodes.add(task.getCode());
        }
        this.tasksList = loadTaskList;
        this.tasksCodes = loadCodes;
        ois.close();
        bis.close();
        fis.close();
    }
}
