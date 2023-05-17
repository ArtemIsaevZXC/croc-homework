package ru.croc.javaschool.homework7.xmltools;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Преобразователь строки формата yyyy-MM-dd'T'HH:mm:ss в {@link LocalDateTime} и наоборот.
 *
 * @author Artem Isaev
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    /**
     * Формат даты и времени для преобразования в строку.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    /**
     * Преобразует строку в дату и время.
     *
     * @param s строковое представление даты и времени в формате yyyy-MM-dd'T'HH:mm:ss.
     * @return дата и время.
     */
    @Override
    public LocalDateTime unmarshal(String s) {
        try {
            return LocalDateTime.parse(s, formatter);
        } catch ( Exception e ) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Преобразует дату и время в строку.
     *
     * @param time дата и время.
     * @return строковое представление даты и времени в формате yyyy-MM-dd'T'HH:mm:ss.
     */
    @Override
    public String marshal(LocalDateTime time) {
        try {
            return time.format(formatter);
        } catch ( Exception e ) {
            throw new RuntimeException(e);
        }
    }
}
