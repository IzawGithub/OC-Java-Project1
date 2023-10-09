package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jetbrains.annotations.NotNull;

/**
 * Read a file and extract a list of symptoms from it.
 * <p>
 * The Symptoms class algorithm work by extracting text line by line, and
 * counting the number of repeat.
 * <p>
 * There is no special word filter to see if a line is actually a symptom.
 * This also mean that empty line count as a symptom.
 * 
 */
class Symptoms {
    // private

    SortedMap<String, Integer> mapSymptoms = new TreeMap<>();

    /**
     * Read a file and put data in the map 
     * 
     * @param fileToRead Opened file to read
     */
    private @NotNull void Read(@NotNull final BufferedReader fileToRead) {
        // No need for error checking, Ctor already sanitized it
        try {
            var line = fileToRead.readLine();
            while (line != null) {
                if (mapSymptoms.containsKey(line)) {
                    mapSymptoms.put(line, mapSymptoms.get(line) + 1);
                } else {
                    mapSymptoms.put(line, 1);
                }
                line = fileToRead.readLine();
            }
        } catch (IOException e) {
        }
    }

    // protected

    // public

    // -- Getters --

    public final @NotNull SortedMap<String, Integer> getSymptoms() {
        return mapSymptoms;
    }

    // -- Functions --

    /** 
     * Write results to a file
     * 
     * @param pathToFile Path to a text file to write on the filesystem
     * @throws IOException Thrown if the given path either does not exist, or is not writable
     */
    public final @NotNull void writeToFile(@NotNull final String pathToFile) throws IOException {
        var file = new File(pathToFile);
        try {
            file.createNewFile();
        } catch (IOException e) {
        }

        List<String> lines = new ArrayList<>();
        for (var symptom : getSymptoms().entrySet()) {
            // Replace with String template when released (Preview in Java21)
            lines.add(MessageFormat.format("{0} : {1}", symptom.getKey(), symptom.getValue()));
        }

        try {
            Files.write(file.toPath(), lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -- Ctors --

    /**
     * Create a Symptoms object.
     * Read from a file given from a path, and extract a list of symptoms from the
     * data.
     * Duplicate symptoms in the file increment the value.
     * 
     * @param pathToFile Path to a text file on the filesystem
     * @throws IOException Thrown if the file is either null or does not exist
     */
    public @NotNull Symptoms(@NotNull final String pathToFile) throws IOException {
        if (pathToFile == null) {
            throw new IOException("Path to symptoms file cannot be null");
        }

        var file = new File(pathToFile);
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            throw new IOException("Path given does not exist, or is not a file");
        }
        var fileReader = new BufferedReader(new FileReader(file));
        Read(fileReader);
        // BufferedReader implements java.lang.AutoCloseable, does not leak
    }
}
