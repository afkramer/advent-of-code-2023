package adventofcode2023.day04;

import adventofcode2023.Day;

public class Day04 implements Day {

    @Override
    public void partOne() {
        ScratchcardReader reader = new ScratchcardReader();
        System.out.println(reader.sumWinnings());
    }

    @Override
    public void partTwo() {
        ScratchcardReader reader = new ScratchcardReader();
        System.out.println(reader.sumWonCopies());
    }

}
