package com.hemebiotech.analytics.service;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;

public interface IReadSymptoms {
    /**
     * Read plain text data from a source.
     * Return a newline separated String containing said data.
     */
    public @NotNull String read() throws IOException;
}
