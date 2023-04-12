package ru.croc.javaschool.homework5;

import java.io.Serializable;
import java.util.Random;

/**
 * Модель задачи.
 *
 * @author Artem Isaev
 */
public class Task implements Serializable {
    /**
     * Номер версии для класса.
     */
    private static final long serialVersionUID = 1234L;
    /**
     * Код задачи (у каждой задачи уникальный рандомный код).
     */
    private int code;
    /**
     * Наименование задачи.
     */
    private String name;
    /**
     * Описание задачи.
     */
    private String description;
    /**
     * Исполнитель.
     */
    private String executor;
    /**
     * Статус.
     * Задача будет иметь 3 статуса: нужно сделать (default), в работе, выполнено.
     */
    private String status;
    /**
     * Переменная рандома, необходимая для генерации произвольного кода задачи.
     */
    private Random rnd = new Random();

    /**
     * Создает {@link Task}.
     *
     * @param name        наименование задачи.
     * @param description описание задачи.
     * @param executor    исполнитель.
     */
    public Task(String name, String description, String executor) {
        this.code = rnd.nextInt(100000) + 1;
        this.name = name;
        this.description = description;
        this.executor = executor;
        this.status = "НУЖНО СДЕЛАТЬ";
    }

    /**
     * Получить номер задачи.
     *
     * @return номер задачи.
     */
    public int getCode() {
        return code;
    }

    /**
     * Установить наименование задачи.
     *
     * @param name наименование задачи.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Установить описание задачи.
     *
     * @param description описание задачи.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Установить исполнителя задачи.
     *
     * @param executor исполнитель задачи.
     */
    public void setExecutor(String executor) {
        this.executor = executor;
    }

    /**
     * Установить статус задачи.
     *
     * @param status статус задачи.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Переопределение метода toString для возможности вывода информации о задаче на экран.
     */
    @Override
    public String toString() {
        return "[ЗАДАЧА] [Код: " + code
                + ", Наименование: " + name
                + ", Описание: " + description
                + ", Исполнитель: " + executor
                + ", Статус: " + status + "]";
    }
}
