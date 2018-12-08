package fr.vg.adventofcode.lib;


import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests are for now only testing expected behavior. It does not tests Objects nullability & cie...
 */
public class Day8Test {

    private Day8 day8;

    @BeforeEach
    public void init() {
        day8 = new Day8();
    }


    @Test
    public void testBuildGraph() {
        Day8.Node root = day8.buildGraph("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2");

        // A
        assertNotNull(root);
        assertEquals(2, root.children.size());
        assertEquals(3, root.metadata.size());
        // B
        assertNotNull(root.children.get(0));
        assertEquals(0, root.children.get(0).children.size());
        assertEquals(3, root.children.get(0).metadata.size());
        // C
        assertNotNull(root.children.get(1));
        assertEquals(1, root.children.get(1).children.size());
        assertEquals(1, root.children.get(1).metadata.size());
        // D
        assertNotNull(root.children.get(1).children.get(0));
        assertEquals(0, root.children.get(1).children.get(0).children.size());
        assertEquals(1, root.children.get(1).children.get(0).metadata.size());
    }

    @Test
    public void testBuildGraphAndSumMetadata() {
        Integer sum = day8.buildGraphAndSumMetadata("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2");

        assertEquals(138, sum.intValue());
    }


    @Test
    public void buildGraphAndVerifyAllNodeValues() {
        Day8.Node root = day8.buildGraph("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2");


        // B
        assertNotNull(root.children.get(0));
        assertEquals(33, root.children.get(0).value().intValue());
        // D
        assertNotNull(root.children.get(1).children.get(0));
        assertEquals(99, root.children.get(1).children.get(0).value().intValue());

        // C
        assertNotNull(root.children.get(1));
        assertEquals(0, root.children.get(1).value().intValue());
        // A
        assertNotNull(root);
        assertEquals(66, root.value().intValue());
    }

    @Test
    public void buildGraphAndCalculateRootNodeValue() {
        Integer sum = day8.buildGraphAndCalculateRootNodeValue("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2");
        assertEquals(66, sum.intValue());
    }

}
