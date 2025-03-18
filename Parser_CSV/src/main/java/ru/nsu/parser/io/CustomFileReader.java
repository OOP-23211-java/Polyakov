package ru.nsu.parser.io;

import java.io.*;

/**
 * Класс {@code CustomFileReader} предоставляет функциональность для чтения файла
 * с использованием {@link BufferedReader}. Этот класс реализует интерфейсы {@link Closeable} и {@link ICustomFileReader}.
 * Он управляет открытием, чтением и закрытием файла.
 */
public class CustomFileReader implements Closeable, ICustomFileReader {
    private final String filename;
    private BufferedReader fileReader;

    /**
     * Возвращает объект {@link BufferedReader} для чтения файла.
     *
     * @return объект {@link BufferedReader} для чтения файла.
     */
    public BufferedReader getFileReader() {
        return fileReader;
    }

    /**
     * Создает новый объект {@code CustomFileReader}, который открывает файл для чтения.
     *
     * @param filename имя файла, который нужно открыть.
     * @throws FileNotFoundException если файл не найден.
     */
    public CustomFileReader(String filename) throws FileNotFoundException {
        this.filename = filename;
        try {
            fileReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found: " + filename);
        }
    }

    /**
     * Закрывает {@link BufferedReader} и освобождает все ресурсы.
     * Этот метод автоматически вызывается при использовании конструкции {@code try-with-resources}.
     */
    @Override
    public void close() {
        if (fileReader != null) {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.err.println("Error when closing a file: " + filename);
            }
        }
    }
}