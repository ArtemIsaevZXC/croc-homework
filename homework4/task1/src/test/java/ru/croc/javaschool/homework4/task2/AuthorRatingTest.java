package ru.croc.javaschool.homework4.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Тест для {@link AuthorRating}.
 *
 * @author Artem Isaev
 */
public class AuthorRatingTest {
    /**
     * Строки с названиями текстов, их авторами и самими текстами.
     */
    private String str1, str2, str3;
    /**
     * Список строк, который поступит на вход программе.
     */
    private List<String> articles;
    /**
     * Экземпляр класса {@link AuthorRating}, необходимый для тестирования.
     */
    private AuthorRating authorRating;
    /**
     * Начальная инициализация объектов для проведения дальнейших манипуляций.
     */
    @BeforeEach
    public void init(){
        str1 = "ТесТиРоВаНиЕ программы;Первый автор; тестирование прошло успешно. Программа прошла тестирование";
        str2 = "Кошка собака;Второй автор; Кошка кошка собака попугай";
        str3 = "Какое-то Слово;Третий автор;Тут должно быть 0";
        articles = new ArrayList<>(List.of(
                str1, str2, str3
        ));
        authorRating = new AuthorRating(articles);
    }
    /**
     * Тест сортировки рейтинга авторов по частоте использования слов.
     */
    @Test
    public void getSortedMapTest(){
        Map<String, Double> expectedMap = new LinkedHashMap<>();
        expectedMap.put("Второй автор", 75.0);
        expectedMap.put("Первый автор", ((double)1/3)*100);
        expectedMap.put("Третий автор", 0.0);
        Assertions.assertEquals(expectedMap, authorRating.getSortedMap());
    }

}
