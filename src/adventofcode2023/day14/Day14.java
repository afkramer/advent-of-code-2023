package adventofcode2023.day14;

import adventofcode2023.Day;

public class Day14 implements Day {

    @Override
    public void partOne() {
        RockShifter shifter = new RockShifter();
        System.out.println(shifter.sumUpRocks(shifter.shiftRocksNorth()));
    }

    @Override
    public void partTwo() {
        RockShifter shifter = new RockShifter();
        System.out.println(shifter.sumRocksAfterCycles(1000000000));
    }

}
