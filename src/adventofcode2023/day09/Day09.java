package adventofcode2023.day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import adventofcode2023.Day;
import adventofcode2023.TextParserUtil;

public class Day09 implements Day {

    private List<List<Long>> inputs;

    @Override
    public void init() {
        inputs = new ArrayList<>();
        List<String> rawDataStrings = TextParserUtil.readData("day09.txt");
        for (String rawString : rawDataStrings) {
            inputs.add(Arrays.asList(rawString.split("\\s")).stream().map(TextParserUtil::parseLong)
                    .filter(n -> n != null).toList());
        }
    }

    @Override
    public void partOne() {
        long totalSum = 0L;

        for (List<Long> input : this.inputs) {
            totalSum += createHistoriesAndSumForOneSequence(input, "last");
        }

        System.out.println(totalSum);
    }

    @Override
    public void partTwo() {
        long totalSum = 0L;

        for (List<Long> input : this.inputs) {
            totalSum += createHistoriesAndSumForOneSequence(input, "first");
        }

        System.out.println(totalSum);
    }

    public long createHistoriesAndSumForOneSequence(List<Long> sequence, String firstOrLast) {
        List<List<Long>> histories = new ArrayList<>();
        histories.add(sequence);
        List<Long> lastHistory = sequence;

        while (!lastHistory.stream().allMatch(n -> n == 0)) {
            List<Long> newHistory = new ArrayList<>();
            for (int i = 0; i < lastHistory.size() - 1; i++) {
                newHistory.add(lastHistory.get(i + 1) - lastHistory.get(i));
            }

            histories.add(newHistory);
            lastHistory = newHistory;
        }

        if (firstOrLast.equals("last")) {
            return findLastExtrapolatedValuesFromHistories(histories);
        } else {
            return findFirstExtrapolatedValuesFromHistories(histories);
        }

    }

    public long findLastExtrapolatedValuesFromHistories(List<List<Long>> histories) {

        Collections.reverse(histories);
        List<Long> lastNumbers = histories.stream().map(list -> list.get(list.size() - 1)).toList();

        long lastExtrapolatedValue = 0L;
        for (int i = 0; i < lastNumbers.size(); i++) {
            long newExtrapolatedValue = lastNumbers.get(i) + lastExtrapolatedValue;
            lastExtrapolatedValue = newExtrapolatedValue;
        }

        return lastExtrapolatedValue;
    }

    public long findFirstExtrapolatedValuesFromHistories(List<List<Long>> histories) {
        long lastExtrapolatedValue = 0L;

        Collections.reverse(histories);
        List<Long> firstNumbers = histories.stream().map(list -> list.get(0)).toList();

        for (int i = 0; i < firstNumbers.size(); i++) {
            long newExtrapolatedValue = firstNumbers.get(i) - lastExtrapolatedValue;
            lastExtrapolatedValue = newExtrapolatedValue;
        }
        return lastExtrapolatedValue;
    }

}
