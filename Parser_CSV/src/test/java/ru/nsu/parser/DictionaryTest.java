package ru.nsu.parser;

import ru.nsu.parser.data.*;

import org.junit.jupiter.api.*;
import ru.nsu.parser.io.CustomFileReader;

import java.io.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {
    private File tempFile;
    private Dictionary dictionary;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("test", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("hello world hello\nhello test");
        }

        CustomFileReader fileReader = new CustomFileReader(tempFile.getAbsolutePath());
        dictionary = new Dictionary(fileReader, "output.txt");

        fileReader.close();
    }

    @AfterEach
    void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testDictionaryCreation() {
        Map<String, Integer> wordCounts = dictionary.getMap();
        assertNotNull(wordCounts, "The word map must be initialised");
        assertEquals(3, wordCounts.size(), "There must be 3 unique words");
        assertEquals(3, wordCounts.get("hello"), "The word ‘hello’ must occur 3 times");
        assertEquals(1, wordCounts.get("world"), "The word ‘world’ must occur 1 time");
        assertEquals(1, wordCounts.get("test"), "The word ‘test’ must occur 1 time");
    }

    @Test
    void testSorting() {
        Map<String, Integer> sortedMap = dictionary.getMap();
        assertEquals("hello", sortedMap.keySet().iterator().next(), "The word with the highest frequency should come first");
    }

    @Test
    void testTotalWordsCount() {
        assertEquals(5, dictionary.getTotalWords(), "The total number of words should be 5");
    }
}