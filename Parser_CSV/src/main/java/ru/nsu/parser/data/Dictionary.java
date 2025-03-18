package ru.nsu.parser.data;

import ru.nsu.parser.io.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс {@code Dictionary} представляет собой словарь,
 * который анализирует текстовый файл, считает частоту слов и записывает результат.
 */
public class Dictionary implements IDictionary {
    private String outputFile;
    private Map<String, Integer> map = new LinkedHashMap<>();
    private long totalWords = 0;

    /**
     * Создает новый объект {@code Dictionary}, анализируя входной файл и считая слова.
     *
     * @param inputFile     объект {@link CustomFileReader}, содержащий входной файл.
     * @param outputFilename путь к выходному файлу, куда будет записан результат.
     */
    public Dictionary(CustomFileReader inputFile, String outputFilename) {
        this.outputFile = outputFilename;
        fromFile(inputFile.getFileReader());
        counter();
    }

    /**
     * Сортирует слова в словаре по убыванию частоты встречаемости.
     */
    private void sortDictionary() {
        System.out.println("Start sorting the dictionary");
        map = map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new // Сохраняем порядок вставки
                ));
        System.out.println("End of dictionary sorting");
    }

    /**
     * Обрабатывает одно слово: добавляет его в словарь или увеличивает счетчик.
     *
     * @param word слово, которое нужно обработать.
     */
    private void processWord(String word) {
        if (!word.isEmpty()) {
            map.merge(word, 1, Integer::sum); // Увеличиваем счётчик или добавляем новое слово
        }
    }

    /**
     * Читает текст из файла, анализирует слова и заполняет словарь.
     *
     * @param file {@link BufferedReader} для чтения входного файла.
     */
    public void fromFile(BufferedReader file) {
        System.out.println("The process of completing the dictionary has begun");

        try {
            String inputStr;
            while ((inputStr = file.readLine()) != null) {
                // Разделяем строку на слова и обрабатываем каждое
                for (String word : inputStr.split("[^a-zA-Z0-9]+")) {
                    processWord(word);
                }
            }
        } catch (IOException e) {
            System.err.println("File read error: " + e.getMessage());
        }

        System.out.println("The dictionary has been created and populated");
        sortDictionary(); // Сортировка после завершения чтения файла
    }

    /**
     * Подсчитывает общее количество слов в словаре.
     */
    private void counter() {
        for (Map.Entry<String, Integer> entry : getMap().entrySet()) {
            incrementTotalWords(entry.getValue());
        }
    }

    /**
     * Возвращает отсортированную карту слов с их частотой.
     *
     * @return {@link Map} со словами и их количеством в тексте.
     */
    public Map<String, Integer> getMap() {
        return map;
    }

    /**
     * Возвращает путь к выходному файлу.
     *
     * @return строка с путем к выходному файлу.
     */
    public String getOutputFile() {
        return outputFile;
    }

    /**
     * Возвращает общее количество слов в тексте.
     *
     * @return количество слов.
     */
    public long getTotalWords() {
        return totalWords;
    }

    /**
     * Увеличивает общее количество слов.
     *
     * @param count количество слов, на которое нужно увеличить счетчик.
     */
    public void incrementTotalWords(int count) {
        totalWords += count;
    }
}