package adventofcode2023.day03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adventofcode2023.TextParserUtil;

public class EngineSchematic {
    private List<String> schematicLines;
    // private final Pattern specialCharacters = Pattern.compile("[*@=%+$&/#\\-]");
    private static final String SPECIAL_CHARACTERS = ".*[\\*@\\=%\\+\\$&/#\\-].*";
    private static final int[][] SHIFTS = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
            { 1, 1 } };

    public EngineSchematic(List<String> schematicLines) {
        this.schematicLines = schematicLines;
    }

    public int sumNumbersAdjacentToCharacters() {
        int numSchematicLines = schematicLines.size();
        int numCharactersPerLine = schematicLines.get(0).length();

        boolean matcherFlag = false;
        int sum = 0;
        StringBuilder currentNumberAsString = new StringBuilder();

        for (int row = 0; row < numSchematicLines; row++) {
            for (int col = 0; col < numCharactersPerLine; col++) {
                String currentChar = schematicLines.get(row).charAt(col) + "";
                Integer num = TextParserUtil.parseInteger(currentChar);

                if (num != null) {
                    currentNumberAsString.append(currentChar);
                    if (hasAdjacentSpecialCharacter(row, col)) {
                        matcherFlag = true;
                    }
                }

                if (matcherFlag && (currentChar.equals(".") || currentChar.matches(SPECIAL_CHARACTERS))
                        && !currentNumberAsString.toString().equals("")) {
                    sum += TextParserUtil.parseInteger(currentNumberAsString.toString());
                    currentNumberAsString = new StringBuilder();
                    matcherFlag = false;
                } else if ((currentChar.equals(".") || currentChar.matches(SPECIAL_CHARACTERS))
                        && !currentNumberAsString.toString().equals("")) {
                    currentNumberAsString = new StringBuilder();
                }
            }

            if (matcherFlag && !currentNumberAsString.toString().equals("")) {
                sum += TextParserUtil.parseInteger(currentNumberAsString.toString());
                matcherFlag = false;
            }

            currentNumberAsString = new StringBuilder();

        }
        return sum;
    }

    public boolean hasAdjacentSpecialCharacter(int row, int col) {
        StringBuilder adjacentChars = new StringBuilder();
        for (int[] shift : SHIFTS) {
            try {
                adjacentChars.append(schematicLines.get(row + shift[0]).charAt(col + shift[1]) + "");
            } catch (Exception e) {

            }
        }
        return adjacentChars.toString().matches(SPECIAL_CHARACTERS);
    }

    public int sumGearRatios() {
        int sum = 0;
        int numSchematicLines = schematicLines.size();
        int numCharactersPerLine = schematicLines.get(0).length();

        for (int row = 0; row < numSchematicLines; row++) {
            for (int col = 0; col < numCharactersPerLine; col++) {
                String currentChar = schematicLines.get(row).charAt(col) + "";
                if (currentChar.equals("*") && hasTwoAdjacentNumbers(row, col)) {
                    sum += calculateGearRatioOfAdjacentGears(row, col);
                    System.out.println(String.format("Current sum: %d", sum));
                }
            }
        }

        return sum;
    }

    public boolean hasTwoAdjacentNumbers(int row, int col) {
        StringBuilder adjacentChars = new StringBuilder();
        for (int[] shift : SHIFTS) {
            try {
                String adjacentChar = schematicLines.get(row + shift[0]).charAt(col + shift[1]) + "";
                if (adjacentChar.matches("\\d")) {
                    adjacentChars.append(adjacentChar);
                }
            } catch (Exception e) {

            }
        }

        return adjacentChars.toString().length() >= 2;

    }

    public int calculateGearRatioOfAdjacentGears(int row, int col) {
        List<int[]> coordinatesOfMatches = new ArrayList<>();

        for (int[] shift : SHIFTS) {
            try {
                int currentRow = row + shift[0];
                int currentCol = col + shift[1];
                String adjacentChar = schematicLines.get(currentRow).charAt(currentCol) + "";
                if (adjacentChar.matches("\\d")) {
                    coordinatesOfMatches.add(new int[] { currentRow, currentCol });
                }

            } catch (Exception e) {

            }
        }

        return calculateGearRatioForDistinctCoords(coordinatesOfMatches);
    }

    public int calculateGearRatioForDistinctCoords(List<int[]> coords) {
        List<Integer> gearValues = new ArrayList<>();
        for (int[] coord : coords) {
            gearValues.add(getWholeNumberAtCoord(coord[0], coord[1]));
        }

        gearValues = gearValues.stream().distinct().toList();

        if (gearValues.size() == 2) {
            return gearValues.get(0) * gearValues.get(1);
        } else {
            return 0;
        }

        // if (gearValues.size() == 2) {
        // System.out.println(String.format("Found gear! num1: %d, num2: %d, sum: %d",
        // gearValues.get(0),
        // gearValues.get(1), gearValues.get(0) * gearValues.get(1)));
        // return gearValues.get(0) * gearValues.get(1);
        // } else {
        // gearValues = gearValues.stream().distinct().toList();
        // if (gearValues.size() == 2) {
        // System.out.println(String.format("Found gear! num1: %d, num2: %d, sum: %d",
        // gearValues.get(0),
        // gearValues.get(1), gearValues.get(0) * gearValues.get(1)));
        // return gearValues.get(0) * gearValues.get(1);
        // } else {
        // System.out.println(String.format("Not actually a match -> %d",
        // gearValues.get(0)));
        // return 0;
        // }
        // }

    }

    public int getWholeNumberAtCoord(int row, int col) {
        String relevantLine = schematicLines.get(row);
        String currentNumber = "";
        boolean isRelevantNumber = false;
        for (int currentCol = 0; currentCol < relevantLine.length(); currentCol++) {
            String currentChar = relevantLine.charAt(currentCol) + "";

            if ((currentChar.equals(".") || currentChar.matches(SPECIAL_CHARACTERS)) && isRelevantNumber) {
                return TextParserUtil.parseInteger(currentNumber);
            } else if ((currentChar.equals(".") || currentChar.matches(SPECIAL_CHARACTERS))) {
                currentNumber = "";
            } else if (currentCol == relevantLine.length() - 1) {
                currentNumber += currentChar;
                return TextParserUtil.parseInteger(currentNumber);
            } else {
                currentNumber += currentChar;
            }

            if (currentCol == col) {
                isRelevantNumber = true;
            }

        }

        return 0;
    }

    public String findUniqueCharactersInSchematic() {
        String characters = "";
        for (String schematicLine : schematicLines) {
            characters += Arrays.stream(schematicLine.split("")).distinct().toList().toString();
        }
        return Arrays.stream(characters.split("")).distinct().toList().toString();
    }
}
