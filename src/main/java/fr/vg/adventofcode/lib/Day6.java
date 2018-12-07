package fr.vg.adventofcode.lib;


import javafx.util.Pair;

import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * FIXME code and test are visuals... rework this uggly and shitty code...
 */
public class Day6 {

    /* Build the following map
.........
.A.......
.........
........C
...D.....
.....E...
.B.......
.........
.........
........F
     */
    public CharSequence[][] buildMap(Stream<String> readInput) {
        //1, 1
        final AtomicInteger index = new AtomicInteger();
        List<Position> positions = readInput.map(str -> {
            String[] coords = str.split(", ");
            return new Position(index.getAndIncrement(), Integer.valueOf(coords[0]), Integer.valueOf(coords[1]));
        }).collect(Collectors.toList());
        int maxX = positions.stream().sorted((o1, o2) -> o2.x - o1.x).findFirst().get().x +1 ;
        int maxY = positions.stream().sorted((o1, o2) -> o2.y - o1.y).findFirst().get().y +1 ;

        CharSequence[][] result = new CharSequence[maxX + 1][];
        for (int i = 0; i < maxX + 1; i++) {
            result[i] = new CharSequence[maxY + 1];
            for (int j = 0; j < maxY + 1; j++) {
                result[i][j] = ".";
            }
        }
        for (Position p : positions) {
            result[p.x][p.y] = p;
        }

        return result;
    }

    /*
    complete with low
    aaaaa.ccc
    aAaaa.ccc
    aaaddeccc
    aadddeccC
    ..dDdeecc
    bb.deEeec
    bBb.eeee.
    bbb.eeeff
    bbb.eefff
    bbb.ffffF
     */
    public CharSequence[][] buildManhattanDistance(Stream<String> readInput) {
        CharSequence[][] map = buildMap(readInput);

        List<Position> positions = Arrays.stream(map)
                .map(arr -> Arrays.stream(arr)
                        .filter(x -> x instanceof Position)
                        .map(x -> (Position) x)
                        .collect(Collectors.toList()))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (!(map[i][j] instanceof Position)) {
                    List<Pair<Integer, Position>> distances = calculateDistancesWithPoints(i, j, positions);
                    int minDistance = distances.stream().sorted(Comparator.comparingInt(Pair::getKey)).findFirst().get().getKey();
                    List<Pair<Integer, Position>> nearestPoints = distances.stream().filter(d -> d.getKey().equals(minDistance)).collect(Collectors.toList());
                    if (nearestPoints.size() == 1) {
                        map[i][j] = nearestPoints.get(0).getValue().representation.toLowerCase();
                    }
                }
            }
        }
        return map;
    }

    private List<Pair<Integer, Position>> calculateDistancesWithPoints(int i, int j, List<Position> positions) {
        return positions.stream()
                .map(p -> new Pair<>(Math.abs(i - p.x) + Math.abs(j - p.y), p))
                .collect(Collectors.toList());
    }


    public Integer calculateBiggestArea(Stream<String> readInput) {
        CharSequence[][] map = buildManhattanDistance(readInput);
        Set<CharSequence> outsiderChars = new HashSet<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == 0 || i == map.length - 1 || j == 0 || j == map[i].length - 1) {
                    outsiderChars.add(map[i][j].toString().toLowerCase());
                }
            }
        }
        Map<String, Integer> charOccurence = new HashMap<>();
        for (int i = 0; i < map.length ; i++) {
            for (int j = 0; j < map[i].length ; j++) {
                if (!outsiderChars.contains(map[i][j].toString().toLowerCase())) {
                    Integer nbOccurence = charOccurence.get(map[i][j].toString().toLowerCase());
                    if (nbOccurence == null)
                        charOccurence.put(map[i][j].toString().toLowerCase(), 1);
                    else {
                        charOccurence.put(map[i][j].toString().toLowerCase(), ++nbOccurence);
                    }
                }
            }
        }
        return charOccurence.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).findFirst().get().getValue();
    }

    public static class Position implements CharSequence {
        public final String representation;
        public final int x;
        public final int y;

        public Position(int position, int x, int y) {
            char ch = 'A';
            int pos = position;
            String s = "";
            while (pos >= 0 ){
                int min = Math.min(pos, 25);
                ch = (char) ('A' + min);
                s += ch;
                pos -= min;
                if (min == 0)
                    break;
            }
            ch += position;
            representation = s;
            this.x = x;
            this.y = y;
        }

        @Override
        public int length() {
            return representation.length();
        }

        @Override
        public char charAt(int i) {
            return representation.charAt(i);
        }

        @Override
        public CharSequence subSequence(int i, int i1) {
            return representation.subSequence(i, i1);
        }

        @Override
        public String toString() {
            return new String(representation);
        }
    }
}
