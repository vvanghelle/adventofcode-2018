package fr.vg.adventofcode.lib;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day5 {


    public String reactPolymer(String polymer) {
        StringBuilder sb = new StringBuilder();
        int nextPositionInSb = 0;
        for (int i = 0; i < polymer.length(); i++) {
            char nextInPolymer = polymer.charAt(i);
            if (nextPositionInSb > 0) {
                char current = sb.charAt(nextPositionInSb - 1);

                if (isSameTypeOppositePolarity(current, nextInPolymer)) {
                    // skip both
                    nextPositionInSb--;
                    sb.setLength(nextPositionInSb);
                } else {
                    sb.append(nextInPolymer);
                    nextPositionInSb++;
                }
            } else {
                sb.append(nextInPolymer);
                nextPositionInSb++;
            }

        }
        return sb.toString();
    }

    private boolean isSameTypeOppositePolarity(char current, char next) {
        return (Character.isLowerCase(current) && Character.toUpperCase(current) == next)
                || (Character.isUpperCase(current) && Character.toLowerCase(current) == next);
    }

    public String improveAndReactPolymer(String polymer) {
        List<String> improveds = new ArrayList<>();
        for (char i = 'a'; i <= 'z'; i++){
            String improved = polymer.replaceAll(i+"", "").replaceAll(Character.toUpperCase(i)+"", "");
            improveds.add(reactPolymer(improved));
        }
        return improveds.stream()
                .sorted(Comparator.comparingInt(String::length))
                .findFirst()
                .get();
    }
}
