package ru.croc.javaschool.homework5.exceptions;

/**
 * Исключение неверного номера характеристики изменяемой задачи.
 *
 * @author Artem Isaev
 */
public class InvalidItemException extends Exception{
    /**
     * Создает {@link InvalidItemException}.
     */
    public InvalidItemException() {
        super("Введён неверный номер изменяемой характеристики задачи.");
    }
}
