package ru.nsu.parser.io;

import ru.nsu.parser.exceptions.OpenFileException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomFileReader {
    private final String filename;
    private BufferedReader fileReader;

    public BufferedReader getFileReader() {
        return fileReader;
    }

    public CustomFileReader(String filename) throws OpenFileException {
        this.filename = filename;
        try {
            fileReader = new BufferedReader(new java.io.FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new OpenFileException("Не удалось открыть файл: " + filename, filename);
        }
    }

    public void close() {
        if (fileReader != null) {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии файла: " + filename);
            }
        }
    }
}
