package com.hemebiotech.analytics;

import java.io.IOException;

import com.hemebiotech.analytics.domain.Symptoms;
import com.hemebiotech.analytics.service.implementations.CountAndSort;
import com.hemebiotech.analytics.service.implementations.ReadFromFile;
import com.hemebiotech.analytics.service.implementations.WriteToFile;

class MainSymptoms {
    private static final String pathOutput = "result.out";

    public static void main(String[] args) {
        try {
            var reader = new ReadFromFile("symptoms.txt");
            var counter = new CountAndSort();
            var writer = new WriteToFile(pathOutput);
            new Symptoms(reader, counter, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
