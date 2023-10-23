package com.hemebiotech.analytics.domain;

import java.io.IOException;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import com.hemebiotech.analytics.service.ICountSymptoms;
import com.hemebiotech.analytics.service.IReadSymptoms;
import com.hemebiotech.analytics.service.IWriteSymptoms;

/**
 * Read a file and extract a list of symptoms from it.
 * <p>
 * The Symptoms class algorithm work by extracting text line by line, and
 * counting the number of repeat.
 * <p>
 * There is no special word filter to see if a line is actually a symptom.
 * This also mean that empty line count as a symptom.
 */
public class Symptoms {
    /**
     * Create a Symptoms data structure.
     * Read from some plain text data, and extract a map of symptoms from the
     * data.
     * 
     * @param reader  Implementations defined reader of plain text data
     * @param counter Implementations defined counter of symptoms
     * @param writer  Implementations defined writer of treated and cleaned up data
     */
    public @NotNull Symptoms(
            @NotNull final IReadSymptoms reader,
            @NotNull final ICountSymptoms counter,
            @NotNull final IWriteSymptoms writer) {
        Objects.requireNonNull(reader);
        Objects.requireNonNull(counter);
        Objects.requireNonNull(writer);
        try {
            writer.writeData(reader.read(), counter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
