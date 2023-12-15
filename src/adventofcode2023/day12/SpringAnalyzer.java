package adventofcode2023.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventofcode2023.TextParserUtil;

public class SpringAnalyzer {
    private List<String> rawData = TextParserUtil.readData("day12.txt");

    public long findCounts() {
        long totalSum = 0L;
        for (String string : rawData) {
            String[] elements = string.split("\\s");

            totalSum += findCounts(elements[0], convertNumbers(elements[1]), new HashMap<>());
        }
        return totalSum;
    }

    public long findCountsLong() {
        long totalSum = 0L;
        for (String string : rawData) {
            String[] elements = string.split("\\s");

            totalSum += findCounts(copySprings(elements[0]), convertNumbers(copyValues(elements[1])), new HashMap<>());
        }
        return totalSum;
    }

    public long findCounts(String springs, List<Integer> groups,
            Map<SpringPartialProblem, Long> cache) {

        SpringPartialProblem problem = new SpringPartialProblem(springs, groups);

        if (groups.isEmpty() && springs.contains("#")) {
            return 0;
        } else if (springs.isEmpty() && !groups.isEmpty()) {
            return 0;
        } else if (groups.isEmpty()) {
            return 1;
        }

        Long combinations = cache.get(problem);
        if (combinations != null) {
            return combinations;
        }

        long count;

        char nextChar = springs.charAt(0);
        if (nextChar == '.') {
            int indexOfNextHash = springs.indexOf("#");
            int indexOfNextQuestion = springs.indexOf("?");
            if (indexOfNextHash == -1 && indexOfNextQuestion == -1) {
                count = findCounts("", groups, cache);
            } else if ((indexOfNextHash != -1 && indexOfNextHash < indexOfNextQuestion) || indexOfNextQuestion == -1) {
                count = findCounts(springs.substring(indexOfNextHash), groups, cache);
            } else {
                count = findCounts(springs.substring(indexOfNextQuestion), groups, cache);
            }

        } else if (nextChar == '#') {
            String[] subStrings = splitIntoFirstGroupAndRest(springs, groups.get(0));
            boolean nextStringEmpty = subStrings[1].isBlank();
            if (subStrings[0].length() == groups.get(0) && (nextStringEmpty || subStrings[1].charAt(0) != '#')) {
                List<Integer> toProcess = new ArrayList<>();
                if (groups.size() > 1) {
                    toProcess = groups.subList(1, groups.size());
                }
                String nextStringToCheck = subStrings[1].length() > 1 ? subStrings[1].substring(1) : "";
                count = findCounts(nextStringToCheck, toProcess, cache);
            } else {
                count = 0;
            }
        } else {
            String startWithHash = springs.replaceFirst("\\?", "#");
            String startWithDot = springs.replaceFirst("\\?", ".");
            count = findCounts(startWithHash, groups, cache) + findCounts(startWithDot, groups, cache);
        }
        problem = new SpringPartialProblem(springs, groups);
        cache.put(problem, count);
        return count;
    }

    private String[] splitIntoFirstGroupAndRest(String springs, int groupSize) {
        String[] components = new String[2];
        StringBuilder firstString = new StringBuilder();
        int firstIntOfSecondString = 0;
        String secondString = "";
        int lengthOfStringToGrab = Math.min(springs.length(), groupSize);
        for (int i = 0; i < lengthOfStringToGrab; i++) {

            char nextChar = springs.charAt(i);
            if (nextChar == '?' || nextChar == '#') {
                firstString.append("#");
            } else {
                firstIntOfSecondString = i;
                break;
            }
            firstIntOfSecondString = groupSize;

        }
        secondString += springs.length() > firstIntOfSecondString ? springs.substring(firstIntOfSecondString) : "";
        components[0] = firstString.toString();
        components[1] = secondString;
        return components;
    }

    private String copySprings(String string) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            sb.append(string);
            if (i != 4) {
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

}
