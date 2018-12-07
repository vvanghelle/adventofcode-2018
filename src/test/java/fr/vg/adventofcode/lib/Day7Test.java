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
public class Day7Test {

    private Day7 day7;

    @BeforeEach
    public void init() {
        day7 = new Day7();
    }


    @Test
    public void testInstruction() {
        Day7.Instruction i = new Day7.Instruction("Step V must be finished before step U can begin.");
        assertNotNull(i);
        assertEquals("V", i.in);
        assertEquals("U", i.out);
    }

    @Test
    public void testBuildInstructionGraph() {
        List<Day7.Step> steps = day7.buildInstructionGraph(Stream.of("Step C must be finished before step A can begin.",
                "Step C must be finished before step F can begin.",
                "Step A must be finished before step B can begin.",
                "Step A must be finished before step D can begin.",
                "Step B must be finished before step E can begin.",
                "Step D must be finished before step E can begin.",
                "Step F must be finished before step E can begin."));

        assertNotNull(steps);
        assertEquals(6, steps.size());
        Day7.Step A = steps.stream().filter(s -> s.id.equals("A")).findFirst().get();
        assertNotNull(A);
        assertNotNull(A.inputs);
        assertNotNull(A.outputs);
        assertEquals(1, A.inputs.size());
        assertNotNull(A.inputs.stream().filter(s -> s.id.equals("C")).findFirst().get());
        assertEquals(2, A.outputs.size());
        assertNotNull(A.outputs.stream().filter(s -> s.id.equals("B")).findFirst().get());
        assertNotNull(A.outputs.stream().filter(s -> s.id.equals("D")).findFirst().get());
    }

    @Test
    public void testBuildGraphExecutionSequence() {
        String execution = day7.buildGraphExecutionSequence(Stream.of("Step C must be finished before step A can begin.",
                "Step C must be finished before step F can begin.",
                "Step A must be finished before step B can begin.",
                "Step A must be finished before step D can begin.",
                "Step B must be finished before step E can begin.",
                "Step D must be finished before step E can begin.",
                "Step F must be finished before step E can begin."));

        assertEquals("CABDFE", execution);
    }

    @Test
    public void testBuildGraphExecutionSequenceWithWorkers() {
        Pair<String, Integer> execution = day7.buildGraphExecutionSequenceWithWorkers(Stream.of("Step C must be finished before step A can begin.",
                "Step C must be finished before step F can begin.",
                "Step A must be finished before step B can begin.",
                "Step A must be finished before step D can begin.",
                "Step B must be finished before step E can begin.",
                "Step D must be finished before step E can begin.",
                "Step F must be finished before step E can begin."), 2, 0);

        assertEquals("CABFDE", execution.getKey());
        assertEquals(15, execution.getValue().intValue());
    }


}
