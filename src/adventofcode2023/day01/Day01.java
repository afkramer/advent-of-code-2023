package adventofcode2023.day01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventofcode2023.Day;
import adventofcode2023.TextParserUtil;

public class Day01 implements Day {

    private static Map<String, Integer> stringsAsNumbers;
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
            List<Integer> ints = new ArrayList<Integer>();
            Arrays.stream(characters)
                  .forEach(c -> {
                      if (parseInteger(c) != null) {
                          ints.add(parseInteger(c));
                      }
                  });
            Integer firstAndLast = parseInteger("" + ints.get(0) + ints.get(ints.size() - 1));
            sum += firstAndLast;
        }
        System.out.println(sum);
    }

    // Refactor, as the solutions include the same code
    @Override
    public void partTwo() {
        int sum = 0;
        int counter = 1;
        for (String input : inputs) {
            String[] characters = replaceNumberWordsWithNumbers(input).split("");
            List<Integer> ints = new ArrayList<Integer>();
            Arrays.stream(characters)
                  .forEach(c -> {
                      if (parseInteger(c) != null) {
                          ints.add(parseInteger(c));
                      }
                  });
            Integer first = ints.get(0);
            Integer last = ints.get(ints.size() - 1);
            Integer firstAndLast = parseInteger("" + first + last);

            sum += firstAndLast;
            System.out.println(String.format(
                    "Input line: %d, input string: %s, first: %d, last: %d, firstAndLast: %d sum: %d", counter,
                    input, first, last, firstAndLast,
                    sum));
            counter++;
        }
        System.out.println(sum);
    }

    public Integer parseInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    public void initializeNumbersAsStringsMap() {
        stringsAsNumbers = new HashMap<String, Integer>();
        stringsAsNumbers.put("one", 1);
        stringsAsNumbers.put("two", 2);
        stringsAsNumbers.put("three", 3);
        stringsAsNumbers.put("four", 4);
        stringsAsNumbers.put("five", 5);
        stringsAsNumbers.put("six", 6);
        stringsAsNumbers.put("seven", 7);
        stringsAsNumbers.put("eight", 8);
        stringsAsNumbers.put("nine", 9);

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
                            stringNum) + string.substring(
                                    lastFoundPattern + (stringNum.length() - 1));
                    lastFoundPattern += stringNum.length();
                }
            } while (lastFoundPattern != -1);
        }
        return string;
    }

}
