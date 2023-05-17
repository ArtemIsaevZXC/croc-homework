package ru.croc.javaschool.homework7.property;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *  Класс, предназначенный для загрузки настроек из файла config.properties и хранения этих настроек в виде
 *  пары ключ-значение в коллекции Map.
 *
 * @author Vladislav Khlybov
 */
public class PropertyContainer {
    /**
     * Коллекция, хранящая настройки в виде пары ключ-значение.
     */
    private static Map<String, String> properties = new HashMap<>();
    /**
     * Метод для загрузки настроек из файла config.properties и сохранения их в коллекцию properties.
     */
    public static void loadProperties() {
        var appProperties = new Properties();
        try {
            appProperties.load(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            for ( var entry: appProperties.entrySet() ) {
                properties.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Метод, возвращающий значение настройки по ключу.
     *
     * @param propertyKey ключ настройки.
     * @return значение настройки по указанному ключу, либо пустая строка, если такой ключ не найден.
     */
    public static String getProperty(String propertyKey) {
        return properties.getOrDefault(propertyKey, "");
    }
}
