package fr.vg.adventofcode.lib;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Day1Test {

    private Day1 day1;

    @BeforeEach
    public void init() {
        day1 = new Day1();
    }

    @Test
    public void sumInputsShouldReturnCorrectCalculus() {
        Stream<String> inputs = Stream.of("+1", "+1", "+1");
        Stream<String> inputs2 = Stream.of("+1", "+1", "-2");
        Stream<String> inputs3 = Stream.of("-1", "-2", "-3");

        Long sum = day1.parseAndSumInputs(inputs);
        Long sum2 = day1.parseAndSumInputs(inputs2);
        Long sum3 = day1.parseAndSumInputs(inputs3);

        assertNotNullAndEquals(3L, sum.longValue());
        assertNotNullAndEquals(0L, sum2.longValue());
        assertNotNullAndEquals(-6L, sum3.longValue());
    }

    @Test
    public void findFrequencyShouldReturnCorrectCalculus() {
        Stream<String> inputs = Stream.of("+1", "-1");
        Stream<String> inputs2 = Stream.of("+3", "+3", "+4", "-2", "-4");
        Stream<String> inputs3 = Stream.of("-6", "+3", "+8", "+5", "-6");
        Stream<String> inputs4 = Stream.of("+7", "+7", "-2", "-7", "-4");

        Long frequencyTwice = day1.findFrequency(inputs);
        Long frequencyTwice2 = day1.findFrequency(inputs2);
        Long frequencyTwice3 = day1.findFrequency(inputs3);
        Long frequencyTwice4 = day1.findFrequency(inputs4);

        assertNotNullAndEquals(0L, frequencyTwice.longValue());
        assertNotNullAndEquals(10L, frequencyTwice2.longValue());
        assertNotNullAndEquals(5L, frequencyTwice3.longValue());
        assertNotNullAndEquals(14L, frequencyTwice4.longValue());
    }

    private void assertNotNullAndEquals(long expected, Long value) {
        assertNotNull(value);
        assertEquals(expected, value.longValue());
    }
}
