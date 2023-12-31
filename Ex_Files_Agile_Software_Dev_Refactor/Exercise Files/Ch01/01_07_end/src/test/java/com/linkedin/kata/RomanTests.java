package com.linkedin.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RomanTests {
    @Test
    public void junitWorks() {
        assertTrue(true);
    }

    @Test
    public void convertsSingleDigitRoman() {
        assertEquals(1, Roman.convert("I"));
        assertEquals(5, Roman.convert("V"));
        assertEquals(10, Roman.convert("X"));
    }
}
