package fr.vg.adventofcode;

import fr.vg.adventofcode.lib.Day1;
import fr.vg.adventofcode.lib.Day2;
import fr.vg.adventofcode.lib.Day3;
import fr.vg.adventofcode.lib.Puzzle;

import java.util.List;

public class AdventOfCode {

    public static void main(String[] args) throws Exception {
        Puzzle puzzle = new Puzzle();
        executeDay1(puzzle);
        executeDay2(puzzle);
        executeDay3(puzzle);
    }

    private static void executeDay1(Puzzle puzzle) throws Exception  {
        Day1 day1 = new Day1();
        String day1File = "day1.txt";

        System.out.println("------------------------------");
        System.out.println("AdventOfCode day 01 part 1 : " + day1.parseAndSumInputs(puzzle.readInput(day1File)));
        System.out.println("AdventOfCode day 01 part 2 : " + day1.findFrequency(puzzle.readInput(day1File)));
    }

    private static void executeDay2(Puzzle puzzle) throws Exception  {
        Day2 day2 = new Day2();
        String day2File = "day2.txt";

        System.out.println("------------------------------");
        System.out.println("AdventOfCode day 02 part 1 : " + day2.checkSum(puzzle.readInput(day2File)));
        List<String> correctBoxIds = day2.stringsWithOneCharacterDiffering(puzzle.readInput(day2File));
        System.out.println("AdventOfCode day 02 part 2 - correct box ids   : " + correctBoxIds);
        System.out.println("AdventOfCode day 02 part 2 - letters in common : " + day2.charsInCommon(correctBoxIds.get(0), correctBoxIds.get(1)));
    }

    private static void executeDay3(Puzzle puzzle) throws Exception  {
        Day3 day3 = new Day3();
        String day3File = "day3.txt";

        System.out.println("------------------------------");
        System.out.println("AdventOfCode day 03 part 1 : " + day3.nbInchSquareInConflict(puzzle.readInput(day3File)));
        System.out.println("AdventOfCode day 03 part 2 : " + day3.findClaimWithoutConflict(puzzle.readInput(day3File)));
    }
}
