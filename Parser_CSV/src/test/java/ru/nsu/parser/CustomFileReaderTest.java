package ru.nsu.parser;

import ru.nsu.parser.io.*;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileReaderTest {
    private File tempFile;
    private CustomFileReader customFileReader;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("test", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("Hello, world!\nThis is a test file.");
        }
        customFileReader = new CustomFileReader(tempFile.getAbsolutePath());
    }

    @AfterEach
    void tearDown() {
        if (customFileReader != null) {
            customFileReader.close();
        }
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testFileReading() throws IOException {
        BufferedReader reader = customFileReader.getFileReader();
        assertNotNull(reader, "BufferedReader must be created");

        assertEquals("Hello, world!", reader.readLine());
        assertEquals("This is a test file.", reader.readLine());
        assertNull(reader.readLine(), "The file must end after two lines");
    }

    @Test
    void testFileNotFound() {
        assertThrows(FileNotFoundException.class, () -> new CustomFileReader("non_existing_file.txt"));
    }

    @Test
    void testClose() {
        customFileReader.close();
        assertDoesNotThrow(() -> customFileReader.close(), "A re-close should not raise an exception");
    }
}
