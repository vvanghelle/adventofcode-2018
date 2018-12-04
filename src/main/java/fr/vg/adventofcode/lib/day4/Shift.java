package fr.vg.adventofcode.lib.day4;


import java.util.ArrayList;
import java.util.List;

public class Shift {

    public Shift(String id) {
        this.id = id;
        this.events = new ArrayList<>();
    }

    public final String id;

    public final List<Event> events;

    @Override
    public String toString() {
        return "Shift{" +
                "id='" + id + '\'' +
                ", events=" + events +
                "}\n";
    }
}