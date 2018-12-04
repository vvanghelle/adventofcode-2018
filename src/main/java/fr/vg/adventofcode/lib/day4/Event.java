package fr.vg.adventofcode.lib.day4;

import java.util.Date;

public class Event {
    public Date date;

    public String event;

    public EventType type;

    @Override
    public String toString() {
        return "Event{" +
                "date=" + date +
                ", event='" + event + '\'' +
                ", type=" + type +
                '}';
    }
}