package adventofcode2023.day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import adventofcode2023.Day;
import adventofcode2023.TextParserUtil;

public class Day09 implements Day {
    private List<List<Long>> inputs;
    private String firstOrLast;

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
        this.firstOrLast = "last";
        System.out.println(sumExtrapolatedValuesOfAllSequences());
    }

    @Override
    public void partTwo() {
        this.firstOrLast = "first";
        System.out.println(sumExtrapolatedValuesOfAllSequences());
    }

    public long sumExtrapolatedValuesOfAllSequences() {
        long totalSum = 0L;

        for (List<Long> input : this.inputs) {
            List<List<Long>> histories = createHistoriesForOneSequence(input);
            totalSum += findExtrapolatedValuesFromHistories(histories);
        }

        return totalSum;
    }

    public List<List<Long>> createHistoriesForOneSequence(List<Long> sequence) {
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

        return histories;
    }

    public long findExtrapolatedValuesFromHistories(List<List<Long>> histories) {
        Collections.reverse(histories);
        List<Long> numbersToExtrapolate;
        if (this.firstOrLast.equals("first")) {
            numbersToExtrapolate = histories.stream().map(list -> list.get(0)).toList();
        } else {
            numbersToExtrapolate = histories.stream().map(list -> list.get(list.size() - 1)).toList();
        }

        long lastExtrapolatedValue = 0L;
        for (int i = 0; i < numbersToExtrapolate.size(); i++) {
            long newExtrapolatedValue;

            if (this.firstOrLast.equals("first")) {
                newExtrapolatedValue = numbersToExtrapolate.get(i) - lastExtrapolatedValue;
            } else {
                newExtrapolatedValue = numbersToExtrapolate.get(i) + lastExtrapolatedValue;
            }

            lastExtrapolatedValue = newExtrapolatedValue;
        }

        return lastExtrapolatedValue;
    }

}
