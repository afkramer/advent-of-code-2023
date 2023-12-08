package adventofcode2023.day01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventofcode2023.Day;
import adventofcode2023.TextParserUtil;

public class Day01 implements Day {

    private static Map<String, String> stringsAsNumbers;
    private static List<String> inputs = TextParserUtil.readData("day01.txt");
    private String numberToConvert;

    {
        initializeNumbersAsStringsMap();
    }

    @Override
    public void partOne() {
        int sum = 0;

        for (String input : inputs) {
            String[] characters = input.split("");
            sum += getNumberFromInputLineCharacters(characters);
        }
        System.out.println(sum);
    }

    @Override
    public void partTwo() {
        int sum = 0;

        for (String input : inputs) {
            String[] characters = replaceNumberWordsWithNumbers(input).split("");
            sum += getNumberFromInputLineCharacters(characters);
        }

        System.out.println(sum);
    }

    public Integer getNumberFromInputLineCharacters(String[] characters) {
        List<Integer> ints = new ArrayList<Integer>();
        Arrays.stream(characters)
                .forEach(c -> {
                    if (parseInteger(c) != null) {
                        ints.add(parseInteger(c));
                    }
                });
        Integer first = ints.get(0);
        Integer last = ints.get(ints.size() - 1);
        return parseInteger("" + first + last);
    }

    public Integer parseInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    public void initializeNumbersAsStringsMap() {
        stringsAsNumbers = new HashMap<String, String>();
        stringsAsNumbers.put("one", "o1e");
        stringsAsNumbers.put("two", "t2o");
        stringsAsNumbers.put("three", "t3e");
        stringsAsNumbers.put("four", "f4r");
        stringsAsNumbers.put("five", "f5e");
        stringsAsNumbers.put("six", "s6x");
        stringsAsNumbers.put("seven", "s7n");
        stringsAsNumbers.put("eight", "e8t");
        stringsAsNumbers.put("nine", "n9e");

    }

    public List<String> replacesNumberWordsWithNumbers(List<String> inputs) {
        return inputs.stream()
                .map(s -> replaceNumberWordsWithNumbers(s))
                .toList();
    }

    public String replaceNumberWordsWithNumbersOld(String string) {
        // Had to use instance variable because you can't change local variables inside
        // a stream
        this.numberToConvert = string;
        stringsAsNumbers.forEach((k, v) -> this.numberToConvert = this.numberToConvert.replace(k, v.toString()));

        return this.numberToConvert;
    }

    public String replaceNumberWordsWithNumbers(String string) {
        for (String stringNum : stringsAsNumbers.keySet()) {
            int lastFoundPattern = 0;
            do {
                lastFoundPattern = string.indexOf(stringNum, lastFoundPattern);
                if (lastFoundPattern != -1) {
                    string = string.substring(0, lastFoundPattern + (stringNum.length() - 1)) + stringsAsNumbers.get(
                            stringNum)
                            + string.substring(
                                    lastFoundPattern + (stringNum.length() - 1));
                    lastFoundPattern += stringNum.length();
                }
            } while (lastFoundPattern != -1);
        }
        return string;
    }

}
