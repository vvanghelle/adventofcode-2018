package fr.vg.adventofcode.lib;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * FIXME : Tests are for now only testing expected behavior. It does not tests Objects nullability & cie...
 */
public class Day3Test {

    private Day3 day3;

    @BeforeEach
    public void init() {
        day3 = new Day3();
    }

    @Test
    public void testNbInchSquareInConflict() {
        Long nbInConflict = day3.nbInchSquareInConflict(Stream.of("#1 @ 1,1: 1x2", "#2 @ 1,1: 2x1"));

        assertEquals(1, nbInConflict.longValue());
    }


}
