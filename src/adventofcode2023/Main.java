package adventofcode2023;

import adventofcode2023.day02.Day02;

public class Main {
    public static void main(String[] args) {
        run(new Day02(), new Day02());
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
