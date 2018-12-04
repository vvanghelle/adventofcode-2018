package fr.vg.adventofcode.lib;

import fr.vg.adventofcode.lib.day4.Event;
import fr.vg.adventofcode.lib.day4.EventType;
import fr.vg.adventofcode.lib.day4.Shift;
import javafx.util.Pair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;


public class Day4 {

    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    public List<Event> parseAndSort(Stream<String> inputs) {
        //[1518-07-10 23:59] Guard #1901 begins shift

        return inputs.map(s -> {
            String[] in = s.split(" ", 3);
            Event event = new Event();
            event.event = in[2];
            try {
                Date d = sdfDate.parse(in[0].substring(1) + " " + in[1].substring(0, in[1].length() - 1));
                event.date = d;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return event;
        })
                .sorted(Comparator.comparing(o -> o.date))
                .collect(Collectors.toList());
    }


    public List<Shift> listShifts(Stream<String> inputs) {
        List<Event> events = parseAndSort(inputs);
        List<Shift> shifts = new ArrayList<>();
        Shift currentShift = null;

        for (Event e : events) {
            EventType type = getType(e.event);
            e.type = type;

            switch (type) {
                case SHIFT:
                    currentShift = new Shift(extractAgentId(e.event));
                    shifts.add(currentShift);
                    break;
                default:
                    currentShift.events.add(e);
            }
        }
        return shifts;
    }

    public EventType getType(String event) {
        switch (event) {
            case "falls asleep":
                return EventType.SLEEP;
            case "wakes up":
                return EventType.WAKESUP;
            default:
                return EventType.SHIFT;
        }
    }

    public String extractAgentId(String event) {
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(event);
        m.find();
        return m.group();
    }


    public Map<String, long[]> sumNumberOfMinutesAsleepByAgent(Stream<String> inputs) {
        List<Shift> shifts = listShifts(inputs);

        Map<String, long[]> minutesByAgentId = new HashMap<>();
        shifts.forEach(shift -> {
            long[] minutes = minutesByAgentId.get(shift.id);
            if (minutes == null) {
                minutes = new long[60];
                for (int i = 0; i < 60; i++) {
                    minutes[i] = 0;
                }
                minutesByAgentId.put(shift.id, minutes);
            }
            Iterator<Event> eventIterator = shift.events.iterator();
            int minuteStart = 0;
            int minuteStop = 0;
            while (eventIterator.hasNext()) {
                Event event = eventIterator.next();
                switch (event.type) {
                    case SLEEP:
                        // date deprecated, but for this, who cares...
                        minuteStart = event.date.getMinutes();
                        break;
                    case WAKESUP:
                        // date deprecated, but for this, who cares...
                        minuteStop = event.date.getMinutes();
                        for (int i = minuteStart; i < minuteStop; i++) {
                            minutes[i] += 1;
                        }
                }
            }


        });

        return minutesByAgentId;
    }


    public long calculateWorstAgentWithStrategyOne(Stream<String> inputs) {
        Map<String, long[]> minutesByAgentId = sumNumberOfMinutesAsleepByAgent(inputs);

        Pair<Long, Long> agentMinute = minutesByAgentId.entrySet()
                .stream()
                .sorted((entry1, entry2) -> Long.valueOf(LongStream.of(entry2.getValue()).sum()).compareTo(LongStream.of(entry1.getValue()).sum()))
                .findFirst()
                .map(entry ->
                {
                    long[] minutes = entry.getValue();
                    long bestValue = 0;
                    int index = -1;
                    for (int i = 0; i < 60; i++) {
                        if (minutes[i] > bestValue) {
                            bestValue = minutes[i];
                            index = i;
                        }
                    }
                    return new Pair<>(Long.valueOf(entry.getKey()), Long.valueOf(index));
                })
                .get();

        return agentMinute.getValue() * agentMinute.getKey();
    }

    public long calculateWorstAgentWithStrategyTwo(Stream<String> inputs) {
        Map<String, long[]> minutesByAgentId = sumNumberOfMinutesAsleepByAgent(inputs);

        Optional<Pair<Long, Long>> agentMinute = minutesByAgentId.entrySet()
                .stream()
                .map(entry ->
                {
                    long[] minutes = entry.getValue();
                    long bestValue = 0;
                    int index = -1;
                    for (int i = 0; i < 60; i++) {
                        if (minutes[i] > bestValue) {
                            bestValue = minutes[i];
                            index = i;
                        }
                    }
                    return new Pair<Long, Pair<Long, Long>>(Long.valueOf(entry.getKey()), new Pair(Long.valueOf(index), Long.valueOf(bestValue)));
                })
                .sorted((t1, t2) -> ((Pair<Long, Pair<Long, Long>>) t2).getValue().getValue().compareTo(((Pair<Long, Pair<Long, Long>>) t1).getValue().getValue()))
                .findFirst()
                .map(t -> new Pair(((Pair<Long, Pair<Long, Long>>) t).getKey(), ((Pair<Long, Pair<Long, Long>>) t).getValue().getKey()));

        return agentMinute.get().getValue() * agentMinute.get().getKey();
    }
}
