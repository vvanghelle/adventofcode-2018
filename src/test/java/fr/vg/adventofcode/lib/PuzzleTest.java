package fr.vg.adventofcode.lib;

 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PuzzleTest {

    private Puzzle puzzle;

    @BeforeEach
    public void init() {
        puzzle = new Puzzle();
    }

    @Test
    public void readInputShouldThrowError() {
        String filename = "test-fail.txt";

        assertThrows(NullPointerException.class,
                () -> {
                    puzzle.readInput(filename);
                });
    }

    @Test
    public void readInputShouldReturnLines() throws IOException, URISyntaxException {
        String filename = "input-sample.txt";

        Stream<String> inputs = puzzle.readInput(filename);

        assertNotNull(inputs);
        assertEquals(3, inputs.count());
    }
}
