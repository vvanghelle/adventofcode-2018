package fr.vg.adventofcode.lib;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {

    public Long parseAndSumInputs(Stream<String> inputs) {
        return inputs.mapToLong(Long::valueOf).sum();
    }

    public Long findFrequency(Stream<String> inputs) {
        List<Long> numbers = inputs.map(Long::valueOf).collect(Collectors.toList());
        Iterator<Long> numbersIterator = numbers.iterator();
        Set<Long> totals = new HashSet<>();

        long currentTotal = 0;

        while (!totals.contains(currentTotal) && numbersIterator.hasNext()) {
            totals.add(currentTotal);
            currentTotal += numbersIterator.next();
            if (!numbersIterator.hasNext()) {
                numbersIterator = numbers.iterator();
            }
        }

        return currentTotal;
    }
}
