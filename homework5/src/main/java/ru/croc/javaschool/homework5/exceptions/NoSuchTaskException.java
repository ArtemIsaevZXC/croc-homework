package ru.croc.javaschool.homework5.exceptions;

/**
 *  Исключение отсутствия задачи с определенным номером в списке задач.
 *
 * @author Artem Isaev
 */
public class NoSuchTaskException extends Exception{
    /**
     * Создает {@link NoSuchTaskException}.
     */
    public NoSuchTaskException() {
        super("Задачи с таким номером не существует.");
    }
}
