package adventofcode2023.day17;

import adventofcode2023.Day;

public class Day17 implements Day {

    @Override
    public void partOne() {
        DijkstraAnalyzer analyzer = new DijkstraAnalyzer();
        System.out.println("The heat loss route at minimum is: ");
        System.out.println(analyzer.sumShortestRoute());
    }

    @Override
    public void partTwo() {
        System.out.println("Part two has not been implemented yet");
    }

}
