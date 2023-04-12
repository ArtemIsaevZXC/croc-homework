package ru.croc.javaschool.homework5.exceptions;

/**
 * Исключение отсутствия файла с сохраненными данными.
 *
 * @author Artem Isaev
 */
public class NoSuchFileException extends Exception{
    /**
     * Создает {@link NoSuchFileException}.
     */
    public NoSuchFileException() {
        super("Файла с сохраненными данными не существует.");
    }
}
