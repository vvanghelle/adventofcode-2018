package fr.vg.adventofcode.lib;


import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * FIXME code and test are visuals... rework this uggly and shitty code...
 */
public class Day7 {

    public List<Step> buildInstructionGraph(Stream<String> readInput) {
        List<Instruction> instructions = readInput.map(input -> new Instruction(input)).collect(Collectors.toList());
        Map<String, Step> steps = new HashMap<>();
        instructions.forEach(i -> {
            Step in = steps.get(i.in);
            Step out = steps.get(i.out);
            if (in == null) {
                in = new Step(i.in);
                steps.put(i.in, in);
            }
            if (out == null) {
                out = new Step(i.out);
                steps.put(i.out, out);
            }
            in.outputs.add(out);
            out.inputs.add(in);
        });
        return new ArrayList<>(steps.values());
    }


    public String buildGraphExecutionSequence(Stream<String> readInput) {
        List<Step> steps = buildInstructionGraph(readInput);
        // begin with first elements
        List<Step> firsts = steps.stream().filter(s -> s.inputs.isEmpty()).sorted(Comparator.comparing(s -> s.id)).collect(Collectors.toList());
        List<Step> result = new ArrayList<>();
        while (result.size() != steps.size()) {
            Step current = firsts.remove(0);
            result.add(current);
            addOutputsToProcessingList(current, result, firsts);
            firsts.sort(Comparator.comparing(s -> s.id));
        }
        return result.stream().map(s -> s.toString()).collect(Collectors.joining());
    }

    private void addOutputsToProcessingList(Step current, List<Step> processedSteps, List<Step> processingList) {
        for (Step out : current.outputs) {
            boolean canBeAdded = true;
            for (Step nextInput : out.inputs) {
                canBeAdded = canBeAdded & processedSteps.contains(nextInput);
            }
            if (canBeAdded) {
                processingList.add(out);
            }
        }
    }


    public Pair<String, Integer> buildGraphExecutionSequenceWithWorkers(Stream<String> readInput, int nbWorkers, int workDelay) {
        List<Step> steps = buildInstructionGraph(readInput);
        // begin with first elements
        List<Step> firsts = steps.stream().filter(s -> s.inputs.isEmpty()).sorted(Comparator.comparing(s -> s.id)).collect(Collectors.toList());
        List<Step> result = new ArrayList<>();
        List<Pair<Step, AtomicInteger>> temporaryResult = new ArrayList<>();

        AtomicInteger second = new AtomicInteger(-1);
        AtomicInteger availableWorkers = new AtomicInteger(nbWorkers);

        while (result.size() != steps.size()) {

            second.incrementAndGet();
            List<Pair<Step, AtomicInteger>> finishedSteps = temporaryResult.stream()
                    .map(pair -> {
                        pair.getValue().decrementAndGet();
                        return pair;
                    })
                    .filter(pair -> pair.getValue().get() == 0)
                    .sorted(Comparator.comparing(p -> p.getKey().id)).collect(Collectors.toList());
            temporaryResult.removeAll(finishedSteps);
            finishedSteps.forEach(pair -> {
                Step current = pair.getKey();
                result.add(current);
                availableWorkers.incrementAndGet();
                addOutputsToProcessingList(current, result, firsts);
            });
            firsts.sort(Comparator.comparing(s -> s.id));
            System.out.println("temporary result : " + result.stream().map(s -> s.toString()).collect(Collectors.joining()));

            while (availableWorkers.get() > 0 && !firsts.isEmpty()) {
                availableWorkers.decrementAndGet();
                Step current = firsts.remove(0);
                AtomicInteger nbSecondForStep = new AtomicInteger(workDelay + (current.id.charAt(0) - 'A' + 1));
                temporaryResult.add(new Pair<>(current, nbSecondForStep));
            }
        }
        return new Pair(result.stream().map(s -> s.toString()).collect(Collectors.joining()), second.get());
    }

    public static class Instruction {
        public String in;

        public String out;

        public Instruction(String instruction) {
            //Step V must be finished before step U can begin.
            Pattern p = Pattern.compile("Step (.) must be finished before step (.) can begin\\.");
            Matcher m = p.matcher(instruction);
            m.find();
            in = m.group(1);
            out = m.group(2);
        }
    }

    public static class Step {

        public final String id;

        public final List<Step> inputs;

        public final List<Step> outputs;

        public Step(String id) {
            this.id = id;
            this.inputs = new ArrayList<>();
            this.outputs = new ArrayList<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Step)) return false;
            Step step = (Step) o;
            return Objects.equals(id, step.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return id;
        }
    }
}
