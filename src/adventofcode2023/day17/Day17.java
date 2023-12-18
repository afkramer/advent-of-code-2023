package adventofcode2023.day17;

import adventofcode2023.Day;

public class Day17 implements Day {

    @Override
    public void partOne() {
        PathAnalyzer analyzer = new PathAnalyzer();
        System.out.println("The heat loss at minimum is: ");
        System.out.println(analyzer.getSmallestPath());
    }

    @Override
    public void partTwo() {
        System.out.println();
    }

}
