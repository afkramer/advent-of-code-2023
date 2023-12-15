package adventofcode2023.day12;

import adventofcode2023.Day;

public class Day12 implements Day {

    @Override
    public void partOne() {
        SpringAnalyzer analyzer = new SpringAnalyzer();
        System.out.println(analyzer.sumOfAllPossibleIterations());
    }

    @Override
    public void partTwo() {
        SpringAnalyzer analyzer = new SpringAnalyzer();
        System.out.println(analyzer.findCounts());
    }

}
