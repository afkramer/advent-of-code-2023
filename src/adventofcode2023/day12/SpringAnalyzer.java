package adventofcode2023.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import adventofcode2023.TextParserUtil;

public class SpringAnalyzer {
    private List<String> rawData = TextParserUtil.readData("testday12.txt");
    private Map<List<SpringType>, List<Integer>> springTypesAndCounts;

    public SpringAnalyzer() {
        initializeSpringTypesAndCounts();
    }

    private void initializeSpringTypesAndCounts() {
        for (String string : rawData) {
            String[] elements = string.split("\\s");
            springTypesAndCounts.put(convertStringToTypesList(elements[0]), convertNumbers(elements[1]));
        }
    }

    private List<SpringType> convertStringToTypesList(String rawString) {
        List<SpringType> springTypes = new ArrayList<>();
        for (int i = 0; i < rawString.length(); i++) {
            springTypes.add(SpringType.parseSpringTypeFromText(rawString.charAt(i) + ""));
        }
        return springTypes;
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

    public List<Integer> convertSpringsToCounts(List<SpringType> springs) {
        List<Integer> counts = new ArrayList<>();
        int damaged = 0;
        for (SpringType type : springs) {
            if (type == SpringType.DAMAGED) {
                damaged++;
            } else if (damaged > 0) {
                counts.add(damaged);
                damaged = 0;
            }
        }
        return counts;
    }

    public List<List<SpringType>> generatePossibleSpringTypesForUnknownSprings(List<SpringType> springs,
            List<Integer> counts) {
        List<List<SpringType>> possibleCombinations = new ArrayList<>();

        return possibleCombinations;
    }

}
