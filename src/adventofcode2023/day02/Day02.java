package adventofcode2023.day02;

import java.util.List;

import adventofcode2023.Day;
import adventofcode2023.TextParserUtil;

public class Day02 implements Day {
    List<String> gameStrings;

    @Override
    public void init() {
        gameStrings = TextParserUtil.readData("day02.txt");
    }

    @Override
    public void partOne() {
        int sum = 0;
        for (String gameString : gameStrings) {
            ElfGame game = new ElfGame(12, 13, 14, gameString);
            if (game.validGame()) {
                sum += game.getId();
            }
        }
        System.out.println(sum);
    }

    @Override
    public void partTwo() {
        int sum = 0;
        for (String gameString : gameStrings) {
            ElfGame game = new ElfGame(gameString);
            sum += game.powerOfMinimumCubes();
        }
        System.out.println(sum);
    }

}
