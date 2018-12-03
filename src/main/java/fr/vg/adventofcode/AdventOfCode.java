package fr.vg.adventofcode;

import fr.vg.adventofcode.lib.Day1;
import fr.vg.adventofcode.lib.Day2;
import fr.vg.adventofcode.lib.Puzzle;

import java.util.List;

public class AdventOfCode {

    public static void main(String[] args) throws Exception {
        Puzzle puzzle = new Puzzle();
        Day1 day1 = new Day1();
        Day2 day2 = new Day2();
        String day1File = "day1.txt";
        String day2File = "day2.txt";

        System.out.println("------------------------------");
        System.out.println("AdventOfCode day 01 part 1 : " + day1.parseAndSumInputs(puzzle.readInput(day1File)));
        System.out.println("AdventOfCode day 01 part 2 : " + day1.findFrequency(puzzle.readInput(day1File)));
        System.out.println("------------------------------");
        System.out.println("AdventOfCode day 02 part 1 : " + day2.checkSum(puzzle.readInput(day2File)));
        List<String> correctBoxIds = day2.stringsWithOneCharacterDiffering(puzzle.readInput(day2File));
        System.out.println("AdventOfCode day 02 part 2 - correct box ids   : " + correctBoxIds);
        System.out.println("AdventOfCode day 02 part 2 - letters in common : " + day2.charsInCommon(correctBoxIds.get(0), correctBoxIds.get(1)));
    }
}
