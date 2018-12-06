package fr.vg.adventofcode.lib;


import java.util.List;
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
        int maxX = 0;
        int maxY = 0;
        for (Position p : positions) {
            if (p.x > maxX)
                maxX = p.x;
            if (p.y > maxY)
                maxY = p.y;
        }
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
    FIXME WONT WORK :

result :
--------c
-A-------
---------
--------C
---D-----
-----E---
-B-------
---------
---------
b-------F

     */
    public CharSequence[][] buildManhattanDistance(Stream<String> readInput) {
        CharSequence[][] map = buildMap(readInput);
        boolean hasProcessedForTurn = true;
        int offset = 1;
        while (hasProcessedForTurn) {
            hasProcessedForTurn = false;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (processManhanttanDistance(map, i, j, offset)) {
                        hasProcessedForTurn = true;
                    }
                }
            }
            offset++;
        }
        return map;
    }

    private boolean processManhanttanDistance(CharSequence[][] map, int i, int j, int offset) {
        boolean hasModified = false;
        if (map[i][j] instanceof Position) {
            for (int i2 = i - offset; i2 <= i + offset; i2++) {
                for (int j2 = j - offset; j2 <= j + offset; j2++) {
                    if (i2 == i - offset || i2 == i + offset || j2 == j - offset || j2 == j + offset) {
                        hasModified = hasModified | fillPositionWithOccupation(map, i2, j2, (Position) map[i][j]);
                    }
                }
            }

        }
        return hasModified;
    }

    private boolean fillPositionWithOccupation(CharSequence[][] map, int i, int j, Position position) {
        try {

            String charAtPosition = map[i][j].toString();
            if (charAtPosition.equals(".")) {
                // empty space
                map[i][j] = position.toString().toLowerCase();
                return true;
            }
            if (charAtPosition.charAt(0) >= 'a') {
                map[i][j] = "-";
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {

        }
        return false;
    }

    public String doSomething(Stream<String> readInput) {
        return null;
    }

    public static class Position implements CharSequence {
        public final String representation;
        public final int x;
        public final int y;

        public Position(int position, int x, int y) {
            char ch = 'A';
            ch += position;
            representation = String.valueOf(ch);
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
