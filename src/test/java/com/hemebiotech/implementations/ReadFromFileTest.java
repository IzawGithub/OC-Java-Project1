package com.hemebiotech.implementations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.hemebiotech.analytics.service.implementations.ReadFromFile;

class ReadFromFileTest {
    private String testFileName = "testSymptoms";
    private ReadFromFile testReader;
    
    @AfterEach
    void tearDown() {
        var file = new File(testFileName);
        file.delete();
    }

    @Test
    void fileDoesNotExist() {
        assertThrowsExactly(IOException.class, () -> {
            testReader = new ReadFromFile(testFileName);
        });
    }

    @Test
    void fileIsFolder() {
        var file = new File(testFileName);
        file.mkdir();
        assertThrowsExactly(IOException.class, () -> {
            testReader = new ReadFromFile(testFileName);
        });
    }

    @Test
    void fileEmpty() throws IOException {
        var file = new File(testFileName);
        file.createNewFile();
        testReader = new ReadFromFile(testFileName);

        String expected = "";
        var actual = testReader.read();
        assertEquals(expected, actual);
    }

    @Test
    void fileContent() throws IOException {
        var file = new File(testFileName);
        file.createNewFile();
        testReader = new ReadFromFile(testFileName);

        String expected = "First\nSecond";
        List<String> lines = Arrays.asList(expected);
        Files.write(Paths.get(testFileName), lines);

        var actual = testReader.read();
        assertEquals(expected+"\n", actual);
    }
}
