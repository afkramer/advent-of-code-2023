package adventofcode2023.day03;

import java.util.Arrays;
import java.util.List;

import adventofcode2023.TextParserUtil;

public class EngineSchematic {
    private List<String> schematicLines;
    // private final Pattern specialCharacters = Pattern.compile("[*@=%+$&/#\\-]");
    private static final String SPECIAL_CHARACTERS = ".*[\\*@\\=%\\+\\$&/#\\-].*";

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
        int[][] shifts = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
        for (int[] shift : shifts) {
            try {
                adjacentChars.append(schematicLines.get(row + shift[0]).charAt(col + shift[1]) + "");
            } catch (Exception e) {

            }
        }
        return adjacentChars.toString().matches(SPECIAL_CHARACTERS);
    }

    public String findUniqueCharactersInSchematic() {
        String characters = "";
        for (String schematicLine : schematicLines) {
            characters += Arrays.stream(schematicLine.split("")).distinct().toList().toString();
        }
        return Arrays.stream(characters.split("")).distinct().toList().toString();
    }
}
