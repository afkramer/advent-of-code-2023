package adventofcode2023.day16;

import adventofcode2023.Day;

public class Day16 implements Day {

    @Override
    public void partOne() {
        Grid grid = new Grid();
        grid.runLightBeamsThroughGrid();
        System.out.println(grid.findChargedTiles());
    }

    @Override
    public void partTwo() {
        Grid grid = new Grid();
        System.out.println(grid.findLongestChargedPath());
    }

}
