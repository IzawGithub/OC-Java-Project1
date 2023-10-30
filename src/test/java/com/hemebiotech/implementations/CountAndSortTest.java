package com.hemebiotech.implementations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import com.hemebiotech.analytics.service.implementations.CountAndSort;

class CountAndSortTest {
    private CountAndSort testCounter = new CountAndSort();

    @Test
    void CountLF() {
        SortedMap<String, Integer> expected = new TreeMap<>();
        expected.put("First", 1);
        expected.put("Second", 2);

        String symptoms = "First\nSecond\nSecond";
        var actual = testCounter.count(symptoms);
        assertEquals(expected, actual);
    }

    @Test
    void CountCRLF() {
        SortedMap<String, Integer> expected = new TreeMap<>();
        expected.put("First\r", 1);
        expected.put("Second\r", 2);

        String symptoms = "First\r\nSecond\r\nSecond\r\n";
        var actual = testCounter.count(symptoms);
        assertEquals(expected, actual);
    }

}
