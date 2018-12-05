package fr.vg.adventofcode.lib;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests are for now only testing expected behavior. It does not tests Objects nullability & cie...
 */
public class Day5Test {

    private Day5 day5;

    @BeforeEach
    public void init() {
        day5 = new Day5();
    }

    @Test
    public void reactPolymerShouldBeValid() {
        assertEquals("", day5.reactPolymer("abBA"));
        assertEquals("abAB", day5.reactPolymer("abAB"));
        assertEquals("aabAAB", day5.reactPolymer("aabAAB"));
        assertEquals("dabCBAcaDA", day5.reactPolymer("dabAcCaCBAcCcaDA"));

    }
}
