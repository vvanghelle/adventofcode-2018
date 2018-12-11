package fr.vg.adventofcode.lib;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9 {

    public Long buildGameAndCalculateHighScore(String readInput) {
        //424 players; last marble is worth 71144 points

        // FIND params
        Pattern p = Pattern.compile("(\\d+) players; last marble is worth (\\d+) points");
        Matcher m = p.matcher(readInput);
        m.find();
        Integer nbPlayers = Integer.valueOf(m.group(1));
        Integer lastMarblePoints = Integer.valueOf(m.group(2));

        // Start game
        long[] scores = new long[nbPlayers];
        List<Integer> game = new ArrayList<>(lastMarblePoints + 1);
        game.add(0);
        int currentIndex = 0;
        int nextIndex = 0;
        int nextMarbleValue = 1;
        int indexCurrentPlayer = 0;

        for (int tour = 1; tour <= lastMarblePoints; tour++) {
            if (nextMarbleValue % 23 == 0) {
                scores[indexCurrentPlayer] += nextMarbleValue;

                int nextToRemove = currentIndex - 7;
                if (nextToRemove < 0) {
                    nextToRemove += game.size();
                }
                scores[indexCurrentPlayer] += game.remove(nextToRemove);

                currentIndex = nextToRemove;
                if (currentIndex > game.size()) {
                    currentIndex -= game.size();
                }
            } else {
                // play classic turn
                nextIndex = calculateNextIndex(currentIndex, game.size());
                game.add(nextIndex, nextMarbleValue);
                currentIndex = nextIndex;
            }
            nextMarbleValue++;
            indexCurrentPlayer = (indexCurrentPlayer + 1) % nbPlayers;
            //System.out.println(game);
        }
        return Arrays.stream(scores).max().getAsLong();
    }

    private int calculateNextIndex(int currentIndex, int size) {
        if (size <= 1)
            return size;

        int next = currentIndex + 2;
        if (next > size) {
            next -= size;
        }
        return next;
    }


}
