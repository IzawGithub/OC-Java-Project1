package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

    // protected

    // public


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
        // BufferedReader implements java.lang.AutoCloseable, does not leak
    }
}
