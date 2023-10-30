package com.hemebiotech.analytics.service.implementations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.jetbrains.annotations.NotNull;

import com.hemebiotech.analytics.service.IReadSymptoms;

public class ReadFromFile implements IReadSymptoms {
    // BufferedReader implements java.lang.AutoCloseable, does not leak
    final BufferedReader fileToRead;

    public @NotNull String read() throws IOException {
        String fileContent = "";
        var line = fileToRead.readLine();
        while (line != null) {
            fileContent += line + "\n";
            line = fileToRead.readLine();
        }
        return fileContent;
    }

    public ReadFromFile(@NotNull final String pathToFile) throws IOException {
        if (pathToFile == null) {
            throw new IOException("Path to symptoms file cannot be null");
        }
        var file = new File(pathToFile);
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            throw new IOException("Path given does not exist, or is not a file");
        }
        fileToRead = new BufferedReader(new FileReader(file));
    }
}
