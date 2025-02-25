package ru.nsu.parser;

import org.junit.jupiter.api.*;
import ru.nsu.parser.data.Dictionary;
import ru.nsu.parser.io.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CustomWriterTest {
    private File tempFile;
    private Dictionary dictionary;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("output", ".csv");

        dictionary = new Dictionary(new CustomFileReader(createTestInputFile()), tempFile.getAbsolutePath());
    }

    @AfterEach
    void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testWriteCSV() throws IOException {
        CustomWriter.writeCSV(dictionary);

        assertTrue(tempFile.exists(), "CSV file must exist");

        String content = Files.readString(Paths.get(tempFile.getAbsolutePath()));

        assertTrue(content.startsWith("Word,Number,Frequency(%)"), "The file should start with the headings");

        assertTrue(content.contains("hello,3"), "The file must contain the word ‘hello’ at a frequency of 3");
        assertTrue(content.contains("world,1"), "The file must contain the word ‘world’ at a frequency of 1");
    }

    private String createTestInputFile() throws IOException {
        File inputFile = File.createTempFile("test", ".txt");
        try (FileWriter writer = new FileWriter(inputFile)) {
            writer.write("hello world hello\nhello test");
        }
        return inputFile.getAbsolutePath();
    }
}