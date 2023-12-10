package adventofcode2023.day10;

import adventofcode2023.Day;

public class Day10 implements Day {

    @Override
    public void partOne() {
        PipeAnalyzer analyzer = new PipeAnalyzer();
        System.out.println(analyzer.createPipeSequence().length() / 2 + 1);
    }

    @Override
    public void partTwo() {
        System.out.println();
    }

}
