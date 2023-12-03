package adventofcode2023.day03;

import java.util.List;

import adventofcode2023.Day;
import adventofcode2023.TextParserUtil;

public class Day03 implements Day {
    List<String> inputStrings = TextParserUtil.readData("day03.txt");

    @Override
    public void partOne() {
        EngineSchematic schematic = new EngineSchematic(inputStrings);
        System.out.println(schematic.sumNumbersAdjacentToCharacters());
    }

    @Override
    public void partTwo() {
        EngineSchematic schematic = new EngineSchematic(inputStrings);
        System.out.println(schematic.sumGearRatios());
    }

}
