package fr.vg.adventofcode;

import fr.vg.adventofcode.lib.*;

import java.util.List;

public class AdventOfCode {

    public static void main(String[] args) throws Exception {
        Puzzle puzzle = new Puzzle();
        executeDay1(puzzle);
        executeDay2(puzzle);
        executeDay3(puzzle);
        executeDay4(puzzle);
        executeDay5(puzzle);
        executeDay6(puzzle);
        executeDay7(puzzle);
        executeDay8(puzzle);
        executeDay9(puzzle);
    }

    private static void executeDay1(Puzzle puzzle) throws Exception {
        Day1 day1 = new Day1();
        String day1File = "day1.txt";

        System.out.println("------------------------------");
        System.out.println("AdventOfCode day 01 part 1 : " + day1.parseAndSumInputs(puzzle.readInput(day1File)));
        System.out.println("AdventOfCode day 01 part 2 : " + day1.findFrequency(puzzle.readInput(day1File)));
    }

    private static void executeDay2(Puzzle puzzle) throws Exception {
        Day2 day2 = new Day2();
        String day2File = "day2.txt";

        System.out.println("------------------------------");
        System.out.println("AdventOfCode day 02 part 1 : " + day2.checkSum(puzzle.readInput(day2File)));
        List<String> correctBoxIds = day2.stringsWithOneCharacterDiffering(puzzle.readInput(day2File));
        System.out.println("AdventOfCode day 02 part 2 - correct box ids   : " + correctBoxIds);
        System.out.println("AdventOfCode day 02 part 2 - letters in common : " + day2.charsInCommon(correctBoxIds.get(0), correctBoxIds.get(1)));
    }

    private static void executeDay3(Puzzle puzzle) throws Exception {
        Day3 day3 = new Day3();
        String day3File = "day3.txt";

        System.out.println("------------------------------");
        System.out.println("AdventOfCode day 03 part 1 : " + day3.nbInchSquareInConflict(puzzle.readInput(day3File)));
        System.out.println("AdventOfCode day 03 part 2 : " + day3.findClaimWithoutConflict(puzzle.readInput(day3File)));
    }

    private static void executeDay4(Puzzle puzzle) throws Exception {
        Day4 day4 = new Day4();
        String day4File = "day4.txt";

        System.out.println("------------------------------");
        System.out.println("AdventOfCode day 04 part 1 : " + day4.calculateWorstAgentWithStrategyOne(puzzle.readInput(day4File)));
        System.out.println("AdventOfCode day 04 part 2 : " + day4.calculateWorstAgentWithStrategyTwo(puzzle.readInput(day4File)));
    }

    private static void executeDay5(Puzzle puzzle) throws Exception {
        Day5 day5 = new Day5();
        String day5File = "day5.txt";

        System.out.println("------------------------------");
        String polymerResult = day5.reactPolymer(puzzle.readInput(day5File).findFirst().get());
        System.out.println("AdventOfCode day 05 part 1 : " + polymerResult);
        System.out.println("AdventOfCode day 05 part 1 result : " + polymerResult.length());

        String improvedPolymerResult = day5.improveAndReactPolymer(puzzle.readInput(day5File).findFirst().get());
        System.out.println("AdventOfCode day 05 part 2 : " + improvedPolymerResult);
        System.out.println("AdventOfCode day 05 part 2 result : " + improvedPolymerResult.length());
    }

    private static void executeDay6(Puzzle puzzle) throws Exception {
        Day6 day6 = new Day6();
        String day6File = "day6.txt";

        System.out.println("------------------------------");
        System.out.println("AdventOfCode day 06 part 1 : " + day6.calculateBiggestAreaWithManhattanDistance(puzzle.readInput(day6File)));
        System.out.println("AdventOfCode day 06 part 2 : " + day6.calculateBiggestAreaWithSumDistance(puzzle.readInput(day6File), 10000));
    }

    private static void executeDay7(Puzzle puzzle) throws Exception {
        Day7 day7 = new Day7();
        String day7File = "day7.txt";

        System.out.println("------------------------------");
        System.out.println("AdventOfCode day 07 part 1 : " + day7.buildGraphExecutionSequence(puzzle.readInput(day7File)));
        System.out.println("AdventOfCode day 07 part 2 : " + day7.buildGraphExecutionSequenceWithWorkers(puzzle.readInput(day7File), 5, 60));

    }

    private static void executeDay8(Puzzle puzzle) throws Exception {
        Day8 day8 = new Day8();
        String day8File = "day8.txt";

        System.out.println("------------------------------");
        System.out.println("AdventOfCode day 08 part 1 : " + day8.buildGraphAndSumMetadata(puzzle.readInput(day8File).findFirst().get()));
        System.out.println("AdventOfCode day 08 part 2 : " + day8.buildGraphAndCalculateRootNodeValue(puzzle.readInput(day8File).findFirst().get()));

    }

    private static void executeDay9(Puzzle puzzle) throws Exception {
        Day9 day9 = new Day9();
        System.out.println("------------------------------");
        System.out.println("AdventOfCode day 09 part 1 : " + day9.buildGameAndCalculateHighScore(puzzle.readInput("day9.txt").findFirst().get()));

    }

}
