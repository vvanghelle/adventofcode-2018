package fr.vg.adventofcode.lib;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Tests are for now only testing expected behavior. It does not tests Objects nullability & cie...
 */
public class Day6Test {

    private Day6 day6;

    @BeforeEach
    public void init() {
        day6 = new Day6();
    }


    @Test
    public void testBuildMap() {
        CharSequence[][] result = day6.buildMap(Stream.of("1, 1", "1, 6", "8, 3", "3, 4", "5, 5", "8, 9"));
        printResult(result);
    }


    @Test
    public void testBuildManhattanDistance() {
        CharSequence[][] result = day6.buildManhattanDistance(Stream.of("1, 1", "1, 6", "8, 3", "3, 4", "5, 5", "8, 9"));
        printResult(result);
    }

    private void printResult(CharSequence[][] result) {
        for (int j = 0; j < result[0].length; j++) {
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
    }

}
