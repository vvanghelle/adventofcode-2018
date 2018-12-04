package fr.vg.adventofcode.lib;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

/**
 * Tests are for now only testing expected behavior. It does not tests Objects nullability & cie...
 */import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests are for now only testing expected behavior. It does not tests Objects nullability & cie...
 */
public class Day3Test {

    private Day3 day3;

    @BeforeEach
    public void init() {
        day3 = new Day3();
    }


    @Test
    public void testBuildMap() {
        List<String>[][] result = day3.buildMap(Stream.of("#1 @ 1,1: 1x2", "#2 @ 1,1: 2x1"));
        assertEquals(1l, result[1][2].size());
        assertEquals(1l, result[2][1].size());
        assertEquals(2l, result[1][1].size());
    }

    @Test
    public void testNbInchSquareInConflict() {
        Long nbInConflict = day3.nbInchSquareInConflict(Stream.of("#1 @ 1,1: 1x2", "#2 @ 1,1: 2x1"));
        assertEquals(1, nbInConflict.longValue());
    }

    @Test
    public void testClaimWithoutConflictWithNoMatchingValues() {
        String noConflict = day3.findClaimWithoutConflict(Stream.of("#1 @ 1,1: 1x2", "#2 @ 1,1: 2x1"));
        assertNull(noConflict);
    }

    @Test
    public void testClaimWithoutConflictWithMatchingValues() {
        String noConflict = day3.findClaimWithoutConflict(Stream.of("#1 @ 1,1: 1x2", "#2 @ 1,1: 2x1", "#727 @ 5,8: 3x3"));
        assertNotNull(noConflict);
        assertEquals("727", noConflict);
    }
}
