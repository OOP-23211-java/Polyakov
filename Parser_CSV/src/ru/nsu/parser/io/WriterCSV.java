package ru.nsu.parser.io;

import ru.nsu.parser.data.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class WriterCSV {

    // Метод для записи словаря в CSV файл
    public static void writeCSV(Dictionary dict) {
        try (BufferedWriter outputFile = new BufferedWriter(new FileWriter(dict.getOutputFile()))) {
            System.out.println("Запись словаря в CSV файл");
            outputFile.write("Слово,Количество,Частота(%)\n");

            counter(dict);
            record(outputFile, dict);

            System.out.println("Окончание записи словаря в CSV файл");
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    // Подсчёт общего числа слов в словаре
    private static void counter(Dictionary dict) {
        for (Map.Entry<String, Integer> entry : dict.getMap().entrySet()) {
            dict.incrementTotalWords(entry.getValue());
        }
    }

    // Запись словаря в CSV файл
    private static void record(BufferedWriter outputFile, Dictionary dict) throws IOException {
        final int KPercentages = 100;
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(dict.getMap().entrySet());
        sortedList.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        for (Map.Entry<String, Integer> entry : sortedList) {
            int count = entry.getValue();
            double frequency = (double) count / dict.getTotalWords() * KPercentages;
            outputFile.write(entry.getKey() + "," + count + "," + frequency + "\n");
        }
    }
}