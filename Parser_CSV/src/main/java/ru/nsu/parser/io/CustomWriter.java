package ru.nsu.parser.io;

import ru.nsu.parser.data.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class CustomWriter {
    static final int KPercentages = 100;

    // Метод для записи словаря в CSV файл
    public static void writeCSV(Dictionary dict) {
        try (BufferedWriter outputFile = new BufferedWriter(new FileWriter(dict.getOutputFile()))) {
            System.out.println("Writing dictionary to CSV file");
            outputFile.write("Word,Number,Frequency(%)\n");

            record(outputFile, dict);

            System.out.println("End of dictionary writing to CSV file");
        } catch (IOException e) {
            System.err.println("Error writing to a file: " + e.getMessage());
        }
    }

    // Запись словаря в CSV файл
    private static void record(BufferedWriter outputFile, Dictionary dict) throws IOException {
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(dict.getMap().entrySet());
        sortedList.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        for (Map.Entry<String, Integer> entry : sortedList) {
            int count = entry.getValue();
            double frequency = (double) count / dict.getTotalWords() * KPercentages;
            outputFile.write(entry.getKey() + "," + count + "," + frequency + "\n");
        }
    }
}