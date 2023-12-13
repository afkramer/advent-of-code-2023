package adventofcode2023.day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Mirror {
    private List<String> mirror;

    public Mirror(List<String> mirrorLines) {
        mirror = mirrorLines;
    }

    public int verticalMatchColumnCount(List<String> mirrorToAnalyze) {
        System.out.println("In verticalMatchColumnCount with argument");
        printMirror(mirrorToAnalyze);
        boolean isAMatch = true;
        for (int i = 0; i < mirrorToAnalyze.get(0).length() - 1; i++) {
            for (String line : mirrorToAnalyze) {
                String partOne = line.substring(0, i + 1);
                String partTwo = line.substring(i + 1);
                int partOneLength = partOne.length();
                int partTwoLength = partTwo.length();
                if (partOneLength < partTwoLength) {
                    partTwo = partTwo.substring(0, partOneLength);
                } else if (partTwoLength < partOneLength) {
                    int correctedStart = line.length() - (partTwoLength * 2);
                    partOne = partOne.substring(correctedStart);
                }
                if (!partOne.equals(reverseString(partTwo))) {
                    isAMatch = false;
                    break;
                }
            }
            if (isAMatch) {
                System.out.println("Found a vertical match at i: " + i);
                return i + 1;
            } else {
                isAMatch = true;
            }
        }
        System.out.println("Did not find a vertical match");
        return 0;
    }

    public int verticalMatchColumnCount() {
        System.out.println("Passing on this.mirror as argument to vertical matches: ");
        return verticalMatchColumnCount(this.mirror);
    }

    public int horizontalMatchRowCount() {
        System.out.println("Finding horizontal matches: ");
        return (verticalMatchColumnCount(pivotMirror(this.mirror))) * 100;
    }

    public String reverseString(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            sb.append(string.charAt(i) + "");
        }
        return sb.toString();
    }

    public List<String> reverseList(List<String> toReverse) {
        List<String> newList = new ArrayList<>();
        for (int i = toReverse.size(); i >= 0; i--) {
            newList.add(toReverse.get(i));
        }
        return newList;
    }

    public void printMirror(List<String> mirror) {
        mirror.stream().forEach(System.out::println);
    }

    // TODO: refactor! Variable names in the loop are not intuitive or helpful
    public List<String> pivotMirror(List<String> toPivot) {
        int numRowsSource = toPivot.size();
        int numColsSource = toPivot.get(0).length();
        int numRowsTarget = toPivot.get(0).length();
        int numColsTarget = toPivot.size();

        String[][] pivotedStrings = new String[numRowsTarget][numColsTarget];

        for (int x = 0; x < numRowsSource; x++) {
            String reversed = reverseString(toPivot.get(x));
            for (int y = 0; y < numColsSource; y++) {
                pivotedStrings[y][x] = reversed.charAt(y) + "";
            }
        }

        List<String> pivotedStringList = new ArrayList<>();

        for (int row = 0; row < pivotedStrings.length; row++) {
            String string = Arrays.stream(pivotedStrings[row]).collect(Collectors.joining(""));
            pivotedStringList.add(string);
        }

        System.out.println("Mirror reversed: ");
        printMirror(pivotedStringList);
        return pivotedStringList;
    }

}
