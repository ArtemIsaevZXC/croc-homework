package ru.croc.javaschool.homework5.exceptions;

/**
 * Исключение загрузки пустого файла.
 *
 * @author Artem Isaev
 */
public class EmptyLoadFileException extends Exception{
    /**
     * Создает {@link EmptyLoadFileException}.
     */
    public EmptyLoadFileException() {
        super("Файл пуст.");
    }
}
