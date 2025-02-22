package ru.nsu.parser.data;

import ru.nsu.parser.io.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Dictionary {
    private String outputFile;
    private Map<String, Integer> map = new LinkedHashMap<>();
    private long totalWords = 0;

    public Dictionary(CustomFileReader inputFile, String outputFilename) {
        this.outputFile = outputFilename;
        fromFile(inputFile.getFileReader());
        counter();
    }

    // Сортировка словаря по убыванию частоты
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

    // Обработка одного слова
    private void processWord(String word) {
        if (!word.isEmpty()) {
            map.merge(word, 1, Integer::sum); // Увеличиваем счётчик или добавляем новое слово
        }
    }

    // Чтение данных из файла
    public void fromFile(BufferedReader file) {
        System.out.println("The process of completing the dictionary has begun");

        try {
            String inputStr;
            while ((inputStr = file.readLine()) != null) {
                // Разделяем строку на слова и обрабатываем каждое
                for (String word : inputStr.split("[^a-zA-Z0-9]+")) {
                    processWord(word); // Обрабатываем слово
                }
            }
        } catch (IOException e) {
            System.err.println("File read error: " + e.getMessage());
        }

        System.out.println("The dictionary has been created and populated");
        sortDictionary(); // Сортировка после завершения чтения файла
    }

    private void counter() {
        for (Map.Entry<String, Integer> entry : getMap().entrySet()) {
            incrementTotalWords(entry.getValue());
        }
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