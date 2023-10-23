package com.hemebiotech.analytics.service;

import org.jetbrains.annotations.NotNull;

public interface IWriteSymptoms {
    /**
     * Write extract data to a sink.
     * 
     * The place to write data is implementation defined.
     * It can be anything that can hold data (a file, an URL, a database...)
     */
    public @NotNull boolean writeData(
            @NotNull final String data,
            @NotNull final ICountSymptoms counter);
}
