package com.hemebiotech.analytics.service.implementations;

import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jetbrains.annotations.NotNull;

import com.hemebiotech.analytics.service.ICountSymptoms;

public class CountAndSort implements ICountSymptoms {
    public @NotNull Map<String, Integer> count(@NotNull final String txtSymptoms) {
        Objects.requireNonNull(txtSymptoms);
        SortedMap<String, Integer> mapSymptoms = new TreeMap<>();
        var symptomsList = txtSymptoms.split("\n");

        for (String symptom : symptomsList) {
            if (mapSymptoms.containsKey(symptom)) {
                mapSymptoms.put(symptom, mapSymptoms.get(symptom) + 1);
            } else {
                mapSymptoms.put(symptom, 1);
            }
        }

        return mapSymptoms;
    }
}
