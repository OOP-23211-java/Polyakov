package ru.nsu.parser;

import ru.nsu.parser.data.*;
import ru.nsu.parser.io.*;
import ru.nsu.parser.exceptions.*;

import java.io.*;
import java.util.Locale;

public class Main {
    private static final int REQUIRED_ARGUMENTS = 2;

    public static void main(String[] args) {
        Locale.setDefault(new Locale("ru"));
        try {
            if (args.length != REQUIRED_ARGUMENTS) {
                throw new InvalidCountArgsException("Неверное количество аргументов: " + args.length);
            }
            String inputFile = args[0];
            String outputFile = args[1];

            MyFileReader inputReader = new MyFileReader(inputFile);
            Dictionary dictionary = new Dictionary(inputReader, outputFile);
            WriterCSV.writeCSV(dictionary);

            System.out.println("Программа выполнена успешно!");
        } catch (InvalidCountArgsException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
    }
}