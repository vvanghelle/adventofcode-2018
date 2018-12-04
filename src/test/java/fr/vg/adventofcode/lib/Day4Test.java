package fr.vg.adventofcode.lib;


import fr.vg.adventofcode.lib.day4.Event;
import fr.vg.adventofcode.lib.day4.Shift;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests are for now only testing expected behavior. It does not tests Objects nullability & cie...
 */
public class Day4Test {

    private Day4 day4;

    @BeforeEach
    public void init() {
        day4 = new Day4();
    }


    @Test
    public void parseAndSortShouldSortEvents() {
        //[1518-07-10 23:59] Guard #1901 begins shift
        List<Event> events = day4.parseAndSort(Stream.of("[1518-07-10 23:59] Guard #1901 begins shift", "[1518-07-10 23:50] Guard #1902 begins shift"));

        assertNotNull(events);
        assertEquals(2, events.size());
        assertNotNull(events.get(0));
        assertEquals("Guard #1902 begins shift", events.get(0).event);
        assertNotNull(events.get(1));
        assertEquals("Guard #1901 begins shift", events.get(1).event);
    }

    @Test
    public void listShiftsShouldListShifts() {
        //[1518-07-10 23:59] Guard #1901 begins shift
        List<Shift> shifts = day4.listShifts(Stream.of("[1518-07-08 23:00] Guard #1901 begins shift", "[1518-07-08 23:30] falls asleep",  "[1518-07-07 00:30] falls asleep",
                "[1518-07-07 00:10] Guard #1902 begins shift"));

        assertNotNull(shifts);
        assertEquals(2, shifts.size());
        assertNotNull(shifts.get(0));
        assertEquals("1902", shifts.get(0).id);
        assertNotNull(shifts.get(0).events);
        assertEquals(1,shifts.get(0).events.size());
        assertNotNull(shifts.get(1));
        assertEquals("1901", shifts.get(1).id);
        assertNotNull(shifts.get(1).events);
        assertEquals(1,shifts.get(1).events.size());
    }

    @Test
    public void sumNumberOfMinutesAsleepByAgentShouldBeValid() {
        //[1518-07-10 23:59] Guard #1901 begins shift
        Map<String, long[]> minutes = day4.sumNumberOfMinutesAsleepByAgent(Stream.of("[1518-07-08 00:00] Guard #1901 begins shift", "[1518-07-08 00:10] falls asleep", "[1518-07-08 00:15] wakes up",
                "[1518-07-08 23:58] Guard #1901 begins shift", "[1518-07-09 00:13] falls asleep", "[1518-07-09 00:17] wakes up",
                "[1518-07-10 00:00] Guard #1902 begins shift", "[1518-07-10 00:40] falls asleep", "[1518-07-10 00:41] wakes up"));

        assertNotNull(minutes);
        assertEquals(2, minutes.size());
        assertNotNull(minutes.get("1901"));
        assertEquals(60, minutes.get("1901").length);
        assertNotNull(minutes.get("1902"));
        assertEquals(60, minutes.get("1902").length);
        assertEquals(1, minutes.get("1901")[10]);
        assertEquals(1, minutes.get("1901")[11]);
        assertEquals(1, minutes.get("1901")[12]);
        assertEquals(2, minutes.get("1901")[13]);
        assertEquals(2, minutes.get("1901")[14]);
        assertEquals(1, minutes.get("1901")[15]);
        assertEquals(1, minutes.get("1901")[16]);
        assertEquals(0, minutes.get("1901")[17]);
        assertEquals(1, minutes.get("1902")[40]);
        assertEquals(0, minutes.get("1902")[41]);
    }
    @Test
    public void calculateWorstAgentWithStrategyOneShouldFindWorstAgent() {
        //[1518-07-10 23:59] Guard #1901 begins shift
        long minutes = day4.calculateWorstAgentWithStrategyOne(Stream.of("[1518-11-01 00:00] Guard #10 begins shift",
                "[1518-11-01 00:05] falls asleep",
                "[1518-11-01 00:25] wakes up",
                "[1518-11-01 00:30] falls asleep",
                "[1518-11-01 00:55] wakes up",
                "[1518-11-01 23:58] Guard #99 begins shift",
                "[1518-11-02 00:40] falls asleep",
                "[1518-11-02 00:50] wakes up",
                "[1518-11-03 00:05] Guard #10 begins shift",
                "[1518-11-03 00:24] falls asleep",
                "[1518-11-03 00:29] wakes up",
                "[1518-11-04 00:02] Guard #99 begins shift",
                "[1518-11-04 00:36] falls asleep",
                "[1518-11-04 00:46] wakes up",
                "[1518-11-05 00:03] Guard #99 begins shift",
                "[1518-11-05 00:45] falls asleep",
                "[1518-11-05 00:55] wakes up"));
        System.out.println(minutes);
    }
}
