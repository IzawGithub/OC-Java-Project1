package com.hemebiotech.analytics;

import java.io.IOException;

class MainSymptoms {
    public static void main(String[] args) {
        try {
            var symptoms = new Symptoms("symptoms.txt");
            for (var symptom : symptoms.getSymptoms().entrySet()) {
                System.out.println("Key: " + symptom.getKey() + "; value: " + symptom.getValue());
            }
            symptoms.writeToFile("result.out");
        } catch (IOException e) {
        }
    }
}
