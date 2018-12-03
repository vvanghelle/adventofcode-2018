package fr.vg.adventofcode.lib;


import fr.vg.adventofcode.lib.day3.Claim;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    public void parseClaim() {
        List<Claim> claims = day3.parseClaims(Stream.of("#123 @ 3,2: 5x4", "#2 @ 3,1: 4x4"));

        assertNotNull(claims);
        assertNotNull(claims.get(0));
        assertNotNull(claims.get(1));
        assertEquals(123l, claims.get(0).id);
        assertEquals(3l, claims.get(0).offsetLeft);
        assertEquals(2l, claims.get(0).offsetTop);
        assertEquals(5l, claims.get(0).width);
        assertEquals(4l, claims.get(0).height);
        //System.out.println(claims.get(0));
    }

    @Test
    public void testMerge() {
        Claim claim = day3.mergeClaims(Stream.of("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2"));
        System.out.println(claim);
    }

}
