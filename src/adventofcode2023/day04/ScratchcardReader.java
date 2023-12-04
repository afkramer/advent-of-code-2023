package adventofcode2023.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventofcode2023.TextParserUtil;

public class ScratchcardReader {
    private List<List<Integer>> winningNumbers = new ArrayList<>();
    private List<List<Integer>> scratchedNumbers = new ArrayList<>();
    private Map<List<Integer>, List<Integer>> cards = new HashMap<>();

    public ScratchcardReader() {
        List<String> inputStrings = TextParserUtil.readData("day04.txt");
        for (String inputString : inputStrings) {
            String[] stringElements = inputString.split(":");
            String[] numbers = stringElements[1].split("\\|");
            winningNumbers
                    .add(Arrays.stream(numbers[0].split("\\s")).map(n -> TextParserUtil.parseInteger(n))
                            .filter(n -> n != null)
                            .toList());
            scratchedNumbers
                    .add(Arrays.stream(numbers[1].split("\\s")).map(n -> TextParserUtil.parseInteger(n))
                            .filter(n -> n != null)
                            .toList());

            List<Integer> winningNumbers = Arrays.stream(numbers[0].split("\\s"))
                    .map(n -> TextParserUtil.parseInteger(n))
                    .filter(n -> n != null)
                    .toList();
            List<Integer> scratchedNumbers = Arrays.stream(numbers[1].split("\\s"))
                    .map(n -> TextParserUtil.parseInteger(n))
                    .filter(n -> n != null)
                    .toList();
            cards.put(winningNumbers, scratchedNumbers);
        }

    }

    public int sumWinnings() {
        int sum = 0;
        for (int cardIndex = 0; cardIndex < winningNumbers.size(); cardIndex++) {
            List<Integer> winners = winningNumbers.get(cardIndex);
            int cardSum = scoreSingleCard(winners, scratchedNumbers.get(cardIndex));
            sum += cardSum;
        }
        return sum;
    }

    public int scoreSingleCard(List<Integer> winningNumbers, List<Integer> scratchedNumbers) {
        long matches = numberOfMatches(winningNumbers, scratchedNumbers);
        int cardSum = 0;
        for (int i = 0; i < matches; i++) {
            if (cardSum == 0) {
                cardSum = 1;
            } else {
                cardSum *= 2;
            }
        }
        return cardSum;
    }

    public long numberOfMatches(List<Integer> winningNumbers, List<Integer> scratchedNumbers) {
        return scratchedNumbers.stream()
                .filter(n -> winningNumbers.contains(n))
                .count();
    }

    public int sumWonCopies() {
        int sum = 0;
        Map<Integer, Integer> cardsToProcess = setUpCardsToProcess();

        for (int cardIndex = 0; cardIndex < winningNumbers.size(); cardIndex++) {

            for (int timesToProcess = 0; timesToProcess < cardsToProcess.get(cardIndex); timesToProcess++) {
                long matches = numberOfMatches(winningNumbers.get(cardIndex), scratchedNumbers.get(cardIndex));
                for (int i = 1; i <= matches; i++) {
                    cardsToProcess.replace(cardIndex + i, cardsToProcess.get(cardIndex + i) + 1);
                }
                sum++;
            }
        }
        return sum;
    }

    public Map<Integer, Integer> setUpCardsToProcess() {
        Map<Integer, Integer> cardsToProcess = new HashMap<>();
        for (int i = 0; i < winningNumbers.size(); i++) {
            cardsToProcess.put(i, 1);
        }
        return cardsToProcess;
    }
}
