package com.hemebiotech.analytics;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hemebiotech.analytics.domain.Symptoms;
import com.hemebiotech.analytics.service.implementations.CountAndSort;
import com.hemebiotech.analytics.service.implementations.ReadFromFile;
import com.hemebiotech.analytics.service.implementations.WriteToFile;

class SymptomsTest {
    private String testFileName = "testSymptoms";
    private ReadFromFile testReader;
    private CountAndSort testCounter = new CountAndSort();
    private WriteToFile testWriter;

    @BeforeEach
    void setUp() throws IOException {
        var file = new File(testFileName);
        file.createNewFile();

        testReader = new ReadFromFile(testFileName);
        testWriter = new WriteToFile(testFileName + ".out");
    }

    @AfterEach
    void tearDown() {
        var fileReader = new File(testFileName);
        fileReader.delete();
        var fileWriter = new File(testFileName + ".out");
        fileWriter.delete();
    }

    @Test
    void SymptomsInit() {
        List<String> line = Arrays.asList("FirstLine\r\nSecondLine");
        try {
            Files.write(Paths.get(testFileName), line, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertDoesNotThrow(() -> {
            new Symptoms(testReader, testCounter, testWriter);
        });
    }

    @Test
    void SmyptomsWithData() throws IOException {
        SortedMap<String, Integer> expected = new TreeMap<>();
        expected.put("fever", 3);
        expected.put("anxiety", 2);
        expected.put("cough", 1);

        List<String> line = new ArrayList<>();
        for (var symptom : expected.entrySet()) {
            for (int i = 0; i < symptom.getValue(); i++) {
                line.add(symptom.getKey());
            }
        }
        try {
            Files.write(Paths.get(testFileName), line, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        var actual = testCounter.count(testReader.read());
        assertEquals(expected, actual);
    }
}
