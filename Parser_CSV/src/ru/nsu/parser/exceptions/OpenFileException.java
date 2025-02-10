package ru.nsu.parser.exceptions;

import java.io.IOException;

public class OpenFileException extends IOException {
    public OpenFileException(String message, String filename) {
        super(message);
    }
}
