package ru.nsu.parser.io;

import ru.nsu.parser.exceptions.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyFileReader {
    private String filename;
    private BufferedReader fileReader;

    public BufferedReader getFileReader() {
        return fileReader;
    }

    public MyFileReader(String filename) throws OpenFileException {
        this.filename = filename;
        try {
            fileReader = new BufferedReader(new java.io.FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new OpenFileException("Не удалось открыть файл: " + filename, filename);
        } catch (IOException e) {
            throw new OpenFileException("Ошибка ввода-вывода при открытии файла '" + filename + "': " + e.getMessage(), filename);
        } catch (Exception e) {
            throw new OpenFileException("Непредвиденная ошибка при открытии файла '" + filename + "': " + e.getMessage(), filename);
        }
    }
}