package ru.croc.javaschool.homework5.exceptions;

/**
 * Исключение неверного статуса задания.
 *
 * @author Artem Isaev
 */
public class InvalidStatusException extends Exception{
    /**
     * Создает {@link InvalidStatusException}.
     */
    public InvalidStatusException() {
        super("Введён неверный статус. Доступные статусы: НУЖНО СДЕЛАТЬ, В РАБОТЕ, ВЫПОЛНЕНО.");
    }
}
