package ru.croc.javaschool.homework4.task2;

import java.util.*;

/**
 * Рейтинг авторов.
 *
 * @author Artem Isaev
 */
public class AuthorRating {
    /**
     * Входящий список строк.
     */
    private List<String> articles;

    /**
     * Создает {@link AuthorRating}.
     *
     * @param articles входящий список строк.
     */
    public AuthorRating(List<String> articles) {
        this.articles = articles;
    }

    /**
     * Сортирует список авторов и их частот использования слов по убыванию.
     *
     * @return отсортированный список авторов и их частот использования слов.
     */
    public Map<String, Double> getSortedMap() {
        List<Map.Entry<String, Double>> entries = new ArrayList<>(estimate().entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> author1, Map.Entry<String, Double> author2) {
                return author2.getValue().compareTo(author1.getValue());
            }
        });
        Map<String, Double> sortedHashMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : entries) {
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    /**
     * Разбивает строки из списка строк на 3 отдельные части, где 1-я часть - название текста, 2-я - автор текста,
     * 3-я - текст. Затем считает количество вхождений каждого слова из названия текста в текст, и находит
     * требуемое соотношение. После создается и возвращается неотсортированный список авторов с их частотами
     * использования слов.
     *
     * @return неотсортированный список авторов и их частот.
     */
    private Map<String, Double> estimate() {
        Map<String, ArrayList<Double>> authorFrequencies = new HashMap<>();
        for (String article : articles) {
            String[] articleParts = article.split(";");
            if (articleParts.length == 3) {
                List<String> wordsInTitle = new ArrayList<>(
                        List.of(articleParts[0]
                                .replaceAll("[^\\da-zA-Zа-яёА-ЯЁ ]", "")
                                .toLowerCase()
                                .strip()
                                .split("\\s+")));
                List<String> wordsInArticle = new ArrayList<>(
                        List.of(articleParts[2]
                                .replaceAll("[^\\da-zA-Zа-яёА-ЯЁ ]", "")
                                .toLowerCase()
                                .strip()
                                .split("\\s+")));
                // подсчитывает у каждого слова из текста количество вхождений в текст
                Map<String, Integer> articleMap = mapWithCountedWords(wordsInArticle);
                Map<String, Integer> wordsStats = new HashMap<>();
                // прохожу по списку слов в названии
                for (String word : wordsInTitle) {
                    // если слово из названия есть в тексте
                    if (articleMap.containsKey(word)) {
                        // записываю сколько раз слово из названия встречалось в тексте
                        wordsStats.put(word, countTitleWordOccurrencesInArticle(word, articleMap));
                    }
                }
                // общая частота слов из названия
                double totalFrequency = 0.0;
                for (Map.Entry<String, Integer> pair : wordsStats.entrySet()) {
                    // считаю частоту каждого слова из названия
                    double wordFrequency = ((double) pair.getValue()) / ((double) wordsInArticle.size());
                    // прибавляю к общей частоте
                    totalFrequency += wordFrequency;
                }
                // складываю частоту к каждому автору
                ArrayList<Double> frequency = authorFrequencies.get(articleParts[1]);
                if (frequency == null) {
                    frequency = new ArrayList<>();
                }
                frequency.add(totalFrequency);
                authorFrequencies.put(articleParts[1], frequency);
            } else {
                continue;
            }
        }
        return createRating(authorFrequencies);
    }


    /**
     * Создает неотсортированный рейтинг авторов с их средними частотами использования слов.
     *
     * @param authorFrequencies авторы и список их частот (именно список, т.к. у одного автора может быть несколько статей)
     * @return неотсортированный рейтинг авторов с их средними частотами использования слов.
     */
    private Map<String, Double> createRating(Map<String, ArrayList<Double>> authorFrequencies) {
        Map<String, Double> rating = new HashMap<>();
        for (Map.Entry<String, ArrayList<Double>> pair : authorFrequencies.entrySet()) {
            double allFrequencies = 0.0;
            for (Double num : pair.getValue()) {
                allFrequencies += num;
            }
            if (pair.getValue().size() != 0) {
                double averageFrequency = (allFrequencies / (double) pair.getValue().size()) * 100;
                rating.put(pair.getKey(), averageFrequency);
            } else {
                rating.put(pair.getKey(), 0.0);
            }
        }
        return rating;
    }

    /**
     * Считает у каждого слова из текста количество его вхождений в текст.
     *
     * @param words список всех слов из текста.
     * @return словарь, где ключ - слово, значение - количество его вхождений в текст.
     */
    private Map<String, Integer> mapWithCountedWords(List<String> words) {
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            Integer integer = wordsMap.get(word);
            if (integer == null) {
                wordsMap.put(word, 1);
            } else {
                wordsMap.put(word, integer + 1);
            }
        }
        return wordsMap;
    }

    /**
     * Считает, сколько раз слово из названия текста встречалось в самом тексте.
     *
     * @param word       слово из названия текста.
     * @param articleMap словарь слов из текста, в котором ключ - слово, значение - количество его вхождений в текст.
     * @return количество вхождений в текст слова из названия текста.
     */
    private int countTitleWordOccurrencesInArticle(String word, Map<String, Integer> articleMap) {
        Integer result = articleMap.get(word);
        if (result == null) {
            return 0;
        }
        return result;
    }
}
