package com.hemebiotech.analytics;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

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
import org.junit.jupiter.api.Test;

class SymptomsTest {
    private String testFileName = "testSymptoms";

    @AfterEach
    void tearDown() {
        var file = new File(testFileName);
        file.delete();
    }

    @Test
    void testFileDontExist() {
        assertThrowsExactly(IOException.class, () -> {
            new Symptoms(testFileName);
        });
    }

    @Test
    void testFileIsFolder() {
        var file = new File(testFileName);
        file.mkdir();
        assertThrowsExactly(IOException.class, () -> {
            new Symptoms(testFileName);
        });
    }

    @Test
    void testFileEmpty() {
        SortedMap<String, Integer> expected = new TreeMap<>();

        var file = new File(testFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            var actual = new Symptoms(testFileName);
            assertEquals(expected, actual.getSymptoms());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFileLF() {
        var file = new File(testFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> line = Arrays.asList("FirstLine\nSecondLine");
        try {
            Files.write(Paths.get(testFileName), line, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertDoesNotThrow(() -> {
            new Symptoms(testFileName);
        });
    }

    @Test
    void testFileCRLF() {
        var file = new File(testFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> line = Arrays.asList("FirstLine\r\nSecondLine");
        try {
            Files.write(Paths.get(testFileName), line, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertDoesNotThrow(() -> {
            new Symptoms(testFileName);
        });
    }

    @Test
    void testFileSymptoms() {
        SortedMap<String, Integer> expected = new TreeMap<>();
        expected.put("fever", 3);
        expected.put("anxiety", 2);
        expected.put("cough", 1);

        var file = new File(testFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        try {
            var actual = new Symptoms(testFileName);
            assertEquals(expected, actual.getSymptoms());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
