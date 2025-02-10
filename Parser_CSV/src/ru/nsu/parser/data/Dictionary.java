package ru.nsu.parser.data;

import ru.nsu.parser.io.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Dictionary {
    private String outputFile;
    private Map<String, Integer> map = new LinkedHashMap<>();
    private long totalWords = 0;

    public Dictionary(MyFileReader inputFile, String outputFilename) {
        this.outputFile = outputFilename;
        fromFile(inputFile.getFileReader());
    }

    // Сортировка словаря по убыванию частоты
    public void sortDictionary() {
        System.out.println("Начало сортировки словаря");
        map = map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new // Сохраняем порядок вставки
                ));
        System.out.println("Окончание сортировки словаря");
    }

    // Обработка одного слова
    private void processWord(String word) {
        if (!word.isEmpty()) {
            map.merge(word, 1, Integer::sum); // Увеличиваем счётчик или добавляем новое слово
        }
    }

    // Чтение данных из файла
    public void fromFile(BufferedReader file) {
        System.out.println("Начат процесс заполнения словаря");

        try {
            String inputStr;
            while ((inputStr = file.readLine()) != null) {
                // Разделяем строку на слова и обрабатываем каждое
                for (String word : inputStr.split("[^a-zA-Z0-9]+")) {
                    processWord(word); // Обрабатываем слово
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }

        System.out.println("Словарь создан и заполнен");
        sortDictionary(); // Сортировка после завершения чтения файла
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public long getTotalWords() {
        return totalWords;
    }

    public void incrementTotalWords(int count) {
        totalWords += count;
    }
}