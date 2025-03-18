package ru.nsu.parser.data;

import java.util.Map;

public interface IDictionary {
    Map<String, Integer> getMap();

    String getOutputFile();

    long getTotalWords();

    void incrementTotalWords(int count);
}