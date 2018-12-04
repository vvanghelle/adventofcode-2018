package fr.vg.adventofcode.lib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fr.vg.adventofcode.lib.day3.Claim.FABRIC_LENGTH;

public class Day4 {

    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    public static enum EventType {
         SHIFT, SLEEP, WAKESUP
    }

    public static class Event {
        public Date date;

        public String event;
    }

    public List<Event> parseAndSort(Stream<String> inputs){
        //[1518-07-10 23:59] Guard #1901 begins shift

        return inputs.map(s -> {
                    String[] in = s.split(" ", 3);
                    Event event = new Event();
                    event.event = in[2];
                    try {
                        Date d = sdfDate.parse(in[0].substring(1) + " "+in[1].substring(0, in[1].length()-1));
                        event.date = d;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return event;
                })
                .sorted(Comparator.comparing(o -> o.date))
                .collect(Collectors.toList());
    }

}
