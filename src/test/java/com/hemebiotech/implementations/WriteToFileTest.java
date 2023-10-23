package com.hemebiotech.implementations;

import java.io.File;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.hemebiotech.analytics.service.ICountSymptoms;
import com.hemebiotech.analytics.service.implementations.WriteToFile;

class WriteToFileTest {
    private String testFileName = "testSymptoms";
    private WriteToFile testWriter;

    private class MockWriter implements ICountSymptoms {
        public @NotNull SortedMap<String, Integer> count(@NotNull final String txtSymptoms) {
            return new TreeMap<>();
        }
    }

    @AfterEach
    void tearDown() {
        var file = new File(testFileName + ".out");
        file.delete();
    }

    @Test
    void writeData() throws IOException {
        testWriter = new WriteToFile(testFileName + ".out");

        String expected = "test data";
        testWriter.writeData(expected, new MockWriter());
    }
}
