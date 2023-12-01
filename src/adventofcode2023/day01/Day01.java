package adventofcode2023.day01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adventofcode2023.Day;
import adventofcode2023.TextParserUtil;

public class Day01 implements Day {

    private static String[] numbersAsStrings = { "one", "two", "three", "four", "five", "six", "seven", "eight",
            "nine" };
    private static List<String> inputs = TextParserUtil.readData("day01.txt");

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

    @Override
    public void partTwo() {
        int sum = 0;
        inputs = 
        
        for (String input : inputs) {
            
        }
    }

    public Integer parseInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    public String replaceNumberWordsWithNumbers(String string) {

    }

}
