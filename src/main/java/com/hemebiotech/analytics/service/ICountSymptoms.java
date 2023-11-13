package com.hemebiotech.analytics.service;

import java.util.Map;

import org.jetbrains.annotations.NotNull;

public interface ICountSymptoms {
    /**
     * Count the number of medical symptoms in a string
     * 
     * @param txtSymptoms Newline separated string of symptoms
     * @return SortedMap<String, Integer> of the symptoms and the number of occurence of each.
     * 
     * 
     */
    public @NotNull Map<String, Integer> count(@NotNull final String txtSymptoms);
}
