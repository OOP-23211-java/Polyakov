package ru.nsu.parser;

import ru.nsu.parser.data.*;
import ru.nsu.parser.io.*;

import java.io.*;
import java.util.Locale;

public class Main {
    private static final int REQUIRED_ARGUMENTS = 2;

    public static void main(String[] args) {
        if (args.length != REQUIRED_ARGUMENTS) {
            throw new IllegalArgumentException("Wrong number of arguments: " + args.length);
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try(CustomFileReader inputReader = new CustomFileReader(inputFile);) {

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