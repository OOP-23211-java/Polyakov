package ru.nsu.parser.io;

import java.io.*;

public class CustomFileReader implements Closeable {
    private final String filename;
    private BufferedReader fileReader;

    public BufferedReader getFileReader() {
        return fileReader;
    }

    public CustomFileReader(String filename) throws FileNotFoundException {
        this.filename = filename;
        try {
            fileReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found: " + filename);
        }
    }

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
