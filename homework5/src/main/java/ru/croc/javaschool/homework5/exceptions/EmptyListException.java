package ru.croc.javaschool.homework5.exceptions;

/**
 * Исключение пустого списка задач.
 *
 * @author Artem Isaev
 */
public class EmptyListException extends Exception {
    /**
     * Создает {@link EmptyListException}.
     */
    public EmptyListException() {
        super("Список задач пуст.");
    }
}
