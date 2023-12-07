package adventofcode2023;

import java.time.Duration;
import java.time.Instant;

import day07.Day07;

public class Main {
    public static void main(String[] args) {
        run(new Day07(), new Day07());
    }

    public static void run(Day instanceForPartOne, Day instanceForPartTwo) {
        instanceForPartOne.init();
        instanceForPartTwo.init();
        System.out.print("Part one: ");
        Instant startOne = Instant.now();
        instanceForPartOne.partOne();
        Instant finishOne = Instant.now();
        System.out
                .println(String.format("Part one took %d seconds", Duration.between(startOne, finishOne).toSeconds()));
        System.out.print("Part two: ");
        Instant startTwo = Instant.now();
        instanceForPartTwo.partTwo();
        Instant finishTwo = Instant.now();
        System.out
                .println(String.format("Part two took %d seconds", Duration.between(startTwo, finishTwo).toSeconds()));
    }
}
