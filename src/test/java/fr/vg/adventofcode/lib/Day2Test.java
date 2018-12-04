package fr.vg.adventofcode.lib;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests are for now only testing expected behavior. It does not tests Objects nullability & cie...
 */
public class Day2Test {

    private Day2 day2;

    @BeforeEach
    public void init() {
        day2 = new Day2();
    }

    @Test
    public void hasDoubleShouldBeValid() {
        assertFalse(day2.hasDouble("abcdef"));
        assertTrue(day2.hasDouble("bababc"));
        assertTrue(day2.hasDouble("abbcde"));
        assertFalse(day2.hasDouble("abcccd"));
        assertTrue(day2.hasDouble("aabcdd"));
        assertTrue(day2.hasDouble("abcdee"));
        assertFalse(day2.hasDouble("ababab"));
    }

    @Test
    public void hasTripleShouldBeValid() {
        assertFalse(day2.hasTriple("abcdef"));
        assertTrue(day2.hasTriple("bababc"));
        assertFalse(day2.hasTriple("abbcde"));
        assertTrue(day2.hasTriple("abcccd"));
        assertFalse(day2.hasTriple("aabcdd"));
        assertFalse(day2.hasTriple("abcdee"));
        assertTrue(day2.hasTriple("ababab"));
    }

    @Test
    public void checkSumShouldBeValid() {
        assertEquals(12L, day2.checkSum(Stream.of("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")));
    }

    @Test
    public void nbCharDifferingShouldBeValid() {
        assertEquals(2L, day2.nbCharDiffering("abcde", "axcye"));
        assertEquals(1L, day2.nbCharDiffering("fghij", "fguij"));
    }

    @Test
    public void stringsWithOneCharacterDifferingFromStringShouldBeValid() {
        String referenceString1 = "abcde";
        List<String> initList1 = Arrays.asList("fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz");

        List<String> result1 = day2.stringsWithOneCharacterDifferingFromString(referenceString1, initList1);
        assertNotNull(result1);
        assertEquals(0L, result1.size());


        String referenceString2 = "fghij";
        List<String> initList2 = Arrays.asList("abcde", "klmno", "pqrst", "fguij", "axcye", "wvxyz");

        List<String> result2 = day2.stringsWithOneCharacterDifferingFromString(referenceString2, initList2);
        assertNotNull(result2);
        assertEquals(1L, result2.size());
        assertEquals("fguij", result2.get(0));
    }

    @Test
    public void stringsWithOneCharacterDifferingShouldBeValid() {
        Stream<String> initList = Stream.of("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz");

        List<String> result = day2.stringsWithOneCharacterDiffering(initList);
        assertNotNull(result);
        assertEquals(2L, result.size());
        assertTrue(result.contains("fghij"));
        assertTrue(result.contains("fguij"));
    }

    @Test
    public void charsInCommonShouldBeValid() {
        assertEquals("fgij", day2.charsInCommon("fghij","fguij"));
    }
}
