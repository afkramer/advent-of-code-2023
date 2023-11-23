package adventofcode2023;

import day01.Day01;

public class Main {
    public static void main(String[] args) {
        run(new Day01(), new Day01());
    }

    public static void run(Day instanceForPartOne, Day instanceForPartTwo) {
        instanceForPartOne.init();
        instanceForPartTwo.init();
        System.out.print("Part one: ");
        instanceForPartOne.partOne();
        System.out.print("Part two: ");
        instanceForPartTwo.partTwo();
    }
}
