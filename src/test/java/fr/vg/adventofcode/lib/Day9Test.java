package fr.vg.adventofcode.lib;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests are for now only testing expected behavior. It does not tests Objects nullability & cie...
 */
public class Day9Test {

    private Day9 day9;

    @BeforeEach
    public void init() {
        day9 = new Day9();
    }


    @Test
    public void testBuildGame1() {
        assertEquals(32, day9.buildGameAndCalculateHighScore("9 players; last marble is worth 25 points").intValue());
    }

    @Test
    public void testBuildGame2() {
        assertEquals(8317, day9.buildGameAndCalculateHighScore("10 players; last marble is worth 1618 points").intValue());
    }
    @Test
    public void testBuildGame3() {
        assertEquals(146373, day9.buildGameAndCalculateHighScore("13 players; last marble is worth 7999 points").intValue());
    }
    @Test
    public void testBuildGame4() {
        assertEquals(2764, day9.buildGameAndCalculateHighScore("17 players; last marble is worth 1104 points").intValue());
    }
    @Test
    public void testBuildGame5() {
        assertEquals(54718, day9.buildGameAndCalculateHighScore("21 players; last marble is worth 6111 points").intValue());
    }
    @Test
    public void testBuildGame6() {
        assertEquals(37305, day9.buildGameAndCalculateHighScore("30 players; last marble is worth 5807 points").intValue());
    }




}
