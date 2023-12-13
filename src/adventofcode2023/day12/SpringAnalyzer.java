package adventofcode2023.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adventofcode2023.TextParserUtil;

public class SpringAnalyzer {
    private List<String> rawData = TextParserUtil.readData("testday12.txt");

    private void initializeSpringTypesAndCounts() {

    }

    public int sumOfAllPossibleIterations() {
        int sum = 0;
        for (String string : rawData) {
            String[] elements = string.split("\\s");
            sum += generatePossibleSpringTypesForUnknownSprings(convertSymbolsToOneAndZero(elements[0]),
                    convertNumbers(elements[1]));
        }
        return sum;
    }

    public long sumOfAllPossibleIterationsBig() {
        long sum = 0;
        for (String string : rawData) {
            System.out.println("Working on string: " + string);
            String[] elements = string.split("\\s");
            String copiedSprings = copySprings(elements[0]);
            String copiedValues = copyValues(elements[1]);
            sum += generatePossibleSpringTypesForUnknownSprings(convertSymbolsToOneAndZero(copiedSprings),
                    convertNumbers(copiedValues));
        }
        return sum;
    }

    private String copySprings(String string) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            sb.append(string);
            if (!string.endsWith("?") || i != 4) {
                sb.append("?");
            }
        }
        return sb.toString();
    }

    private String copyValues(String string) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            sb.append(string);
            if (i != 4) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private String convertSymbolsToOneAndZero(String string) {
        return string.replace(".", "0").replace("#", "1");
    }

    private List<Integer> convertNumbers(String string) {
        List<Integer> numbers = new ArrayList<>();
        String[] rawNumbers = string.split(",");
        for (int i = 0; i < rawNumbers.length; i++) {
            Integer parsedInt = TextParserUtil.parseInteger(rawNumbers[i]);
            if (parsedInt != null) {
                numbers.add(parsedInt);
            }
        }
        return numbers;
    }

    public List<Integer> convertSpringsToCounts(String springs) {
        List<Integer> counts = new ArrayList<>();
        int damaged = 0;
        for (int i = 0; i < springs.length(); i++) {
            if (springs.charAt(i) == '1') {
                damaged++;
            } else if (damaged > 0) {
                counts.add(damaged);
                damaged = 0;
            }
        }
        if (damaged > 0) {
            counts.add(damaged);
        }
        return counts;
    }

    public int generatePossibleSpringTypesForUnknownSprings(String springs,
            List<Integer> counts) {
        int count = 0;

        int unknownCount = (int) Arrays.stream(springs.split("")).filter(s -> s.equals("?")).count();
        Integer integerFromBinary = Integer.parseInt("1".repeat(unknownCount), 2);
        for (int i = 0; i <= integerFromBinary; i++) {
            String countAsBinaryString = Long.toBinaryString(integerFromBinary - i);
            countAsBinaryString = "0".repeat(unknownCount - countAsBinaryString.length()) + countAsBinaryString;
            List<Integer> generatedCounts = convertSpringsToCounts(spliceTogetherStrings(springs, countAsBinaryString));
            if (generatedCounts.equals(counts)) {
                count++;
            }
        }
        return count;
    }

    public String spliceTogetherStrings(String stringWithUnknowns, String possibleValues) {
        int possiblesCounter = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringWithUnknowns.length(); i++) {
            if (stringWithUnknowns.charAt(i) == '?') {
                sb.append(possibleValues.charAt(possiblesCounter));
                possiblesCounter++;
            } else {
                sb.append(stringWithUnknowns.charAt(i));
            }
        }

        return sb.toString();
    }

    // public List<Integer> convertSpringsToCounts(List<SpringType> springs) {
    // List<Integer> counts = new ArrayList<>();
    // int damaged = 0;
    // for (SpringType type : springs) {
    // if (type == SpringType.DAMAGED) {
    // damaged++;
    // } else if (damaged > 0) {
    // counts.add(damaged);
    // damaged = 0;
    // }
    // }
    // return counts;
    // }

}
