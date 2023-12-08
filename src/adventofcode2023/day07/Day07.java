package adventofcode2023.day07;

import adventofcode2023.Day;

public class Day07 implements Day {

    @Override
    public void partOne() {
        PokerGame game = new PokerGame(false);
        System.out.println(game.rankAndScoreHands());
    }

    @Override
    public void partTwo() {
        PokerGame game = new PokerGame(true);
        System.out.println(game.rankAndScoreHands());
    }

}
