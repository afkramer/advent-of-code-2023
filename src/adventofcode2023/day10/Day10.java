package adventofcode2023.day10;

import adventofcode2023.Day;

public class Day10 implements Day {

    @Override
    public void partOne() {
        PipeMatrix.initializePipeMatrix();
        PipeMatrix.createPipeSequence();
        System.out.println(PipeMatrix.getFarthestRoute());
    }

    @Override
    public void partTwo() {
        PipeMatrix.initializePipeMatrix();
        PipeMatrix.createPipeSequence();
        PipeMatrix.initializeExpandedPipeMatrix();
        System.out.println(PipeMatrix.countInteriorTiles());
    }

}
