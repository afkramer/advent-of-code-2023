package adventofcode2023.day17;

import adventofcode2023.Day;

public class Day17 implements Day {

    @Override
    public void partOne() {
        // DijkstraAnalyzer analyzer = new DijkstraAnalyzer();
        // analyzer.initializeForMax3();
        System.out.println("The heat loss route at minimum is: ");
        // System.out.println(analyzer.sumShortestRoute());
    }

    @Override
    public void partTwo() {
        DijkstraAnalyzer analyzer = new DijkstraAnalyzer();
        analyzer.initializeForGraphClumsy();
        System.out.println("The heat loss of the ultra crucible is: ");
        System.out.println(analyzer.sumShortestRoute());
    }

}
