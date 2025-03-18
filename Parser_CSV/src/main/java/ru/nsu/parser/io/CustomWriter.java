package ru.nsu.parser.io;

import ru.nsu.parser.data.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Класс {@code CustomWriter} предоставляет методы для записи данных из {@code Dictionary}
 * в CSV-файл.
 */
public class CustomWriter {
    static final int KPercentages = 100;

    /**
     * Записывает словарь в CSV-файл.
     * Этот метод создает файл, записывает в него заголовки и данные словаря,
     * а затем сохраняет результат.
     *
     * @param dict объект {@link Dictionary}, содержащий данные для записи.
     */
    public static void writeCSV(Dictionary dict) {
        try (BufferedWriter outputFile = new BufferedWriter(new FileWriter(dict.getOutputFile()))) {
            System.out.println("Writing dictionary to CSV file");
            outputFile.write("Word,Number,Frequency(%)\n");

            // Запись данных в CSV
            record(outputFile, dict);

            System.out.println("End of dictionary writing to CSV file");
        } catch (IOException e) {
            System.err.println("Error writing to a file: " + e.getMessage());
        }
    }

    /**
     * Записывает данные словаря в CSV-файл.
     * Метод сортирует словарь по убыванию частоты появления слов и записывает каждое слово,
     * его количество и частоту в файл.
     *
     * @param outputFile {@link BufferedWriter} для записи в файл.
     * @param dict {@link Dictionary} с данными для записи.
     * @throws IOException если произошла ошибка при записи в файл.
     */
    private static void record(BufferedWriter outputFile, Dictionary dict) throws IOException {
        // Сортировка словаря по убыванию частоты
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(dict.getMap().entrySet());
        sortedList.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        // Запись в файл
        for (Map.Entry<String, Integer> entry : sortedList) {
            int count = entry.getValue();
            double frequency = (double) count / dict.getTotalWords() * KPercentages;
            outputFile.write(entry.getKey() + "," + count + "," + frequency + "\n");
        }
    }
}
