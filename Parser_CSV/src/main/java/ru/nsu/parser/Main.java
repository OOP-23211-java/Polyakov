package ru.nsu.parser;

import ru.nsu.parser.data.*;
import ru.nsu.parser.io.*;

import java.io.*;

/**
 * Основной класс программы для обработки текстового файла, создания словаря
 * и записи результатов в CSV-файл.
 */
public class Main {
    private static final int REQUIRED_ARGUMENTS = 2;

    /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки:
     *             <ul>
     *                 <li>args[0] - путь к входному файлу</li>
     *                 <li>args[1] - путь к выходному CSV-файлу</li>
     *             </ul>
     * @throws IllegalArgumentException если количество аргументов неверное.
     */
    public static void main(String[] args) {
        if (args.length != REQUIRED_ARGUMENTS) {
            throw new IllegalArgumentException("Wrong number of arguments: " + args.length);
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try (CustomFileReader inputReader = new CustomFileReader(inputFile)) {

            // Создание словаря и запись его в CSV
            Dictionary dictionary = new Dictionary(inputReader, outputFile);
            CustomWriter.writeCSV(dictionary);

            System.out.println("The program has been successfully implemented!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("There was an error: " + e.getMessage());
        }
    }
}