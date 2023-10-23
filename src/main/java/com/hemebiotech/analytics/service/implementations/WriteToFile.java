package com.hemebiotech.analytics.service.implementations;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import com.hemebiotech.analytics.service.ICountSymptoms;
import com.hemebiotech.analytics.service.IWriteSymptoms;

public class WriteToFile implements IWriteSymptoms {
    private File file;

    public @NotNull boolean writeData(
            @NotNull final String data,
            @NotNull final ICountSymptoms counter) {
        Objects.requireNonNull(counter);
        List<String> lines = new ArrayList<>();
        for (var symptom : counter.count(data).entrySet()) {
            // Replace with String template when released (Preview in Java21)
            lines.add(MessageFormat.format("{0} : {1}", symptom.getKey(), symptom.getValue()));
        }
        try {
            Files.write(file.toPath(), lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public WriteToFile(@NotNull final String pathToWrite) throws IOException {
        Objects.requireNonNull(pathToWrite);
        file = new File(pathToWrite);
        file.createNewFile();
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            throw new IOException("Path given does not exist, or is not a file");
        }
        file.createNewFile();
    }
}
