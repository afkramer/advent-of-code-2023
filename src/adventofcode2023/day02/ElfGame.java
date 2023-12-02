package adventofcode2023.day02;

import java.util.ArrayList;
import java.util.List;

import adventofcode2023.TextParserUtil;

public class ElfGame {
    private int id;
    private int maxRed;
    private int maxBlue;
    private int maxGreen;
    List<ElfGameRound> rounds = new ArrayList<>();

    public ElfGame(int maxRed, int maxGreen, int maxBlue, String gameInput) {
        this.maxRed = maxRed;
        this.maxBlue = maxBlue;
        this.maxGreen = maxGreen;
        initializeGame(gameInput);
    }

    public ElfGame(String gameInput) {
        this(0, 0, 0, gameInput);
    }

    private void initializeGame(String gameInput) {

        String[] gameInputElements = gameInput.split(":");
        String[] gameAndId = gameInputElements[0].split(" ");
        this.id = TextParserUtil.parseInteger(gameAndId[1]);
        initializeRounds(gameInputElements[1]);
    }

    private void initializeRounds(String roundInput) {
        String[] roundStrings = roundInput.split(";");
        for (String roundString : roundStrings) {
            rounds.add(new ElfGameRound(roundString));
        }
    }

    public boolean validGame() {
        return rounds.stream()
                .allMatch(round -> round.roundValuesAreUnderMax(this.maxRed, this.maxGreen, this.maxBlue));
    }

    public int powerOfMinimumCubes() {
        int minRed = 0;
        int minGreen = 0;
        int minBlue = 0;

        for (ElfGameRound round : this.rounds) {
            minRed = minRed < round.getRed() ? round.getRed() : minRed;
            minGreen = minGreen < round.getGreen() ? round.getGreen() : minGreen;
            minBlue = minBlue < round.getBlue() ? round.getBlue() : minBlue;
        }

        return minRed * minGreen * minBlue;
    }

    public int getId() {
        return this.id;
    }
}
