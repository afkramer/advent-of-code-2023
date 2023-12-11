package adventofcode2023;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

import adventofcode2023.day11.Day11;

public class Main {
    public static void main(String[] args) {
        run(new Day11(), new Day11());
    }

    public static void run(Day instanceForPartOne, Day instanceForPartTwo) {
        instanceForPartOne.init();
        instanceForPartTwo.init();
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("Timestamp start part one: " + dateTime);
        System.out.print("Part one: ");
        Instant startOne = Instant.now();
        instanceForPartOne.partOne();
        Instant finishOne = Instant.now();
        System.out
                .println(String.format("Part one took %d seconds", Duration.between(startOne, finishOne).toSeconds()));
        System.out.println("Timestamp start part two: " + dateTime);
        System.out.print("Part two: ");
        Instant startTwo = Instant.now();
        instanceForPartTwo.partTwo();
        Instant finishTwo = Instant.now();
        System.out
                .println(String.format("Part two took %d seconds", Duration.between(startTwo, finishTwo).toSeconds()));
    }
}
