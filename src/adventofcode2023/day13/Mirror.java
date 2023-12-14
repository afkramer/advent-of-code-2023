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
        // System.out.println("In verticalMatchColumnCount with argument");
        // printMirror(mirrorToAnalyze);
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
                // System.out.println("Found a vertical match at i: " + i);
                return i + 1;
            } else {
                isAMatch = true;
            }
        }
        // System.out.println("Did not find a vertical match");
        return 0;
    }

    public int verticalMatchColumnCountIgnorePrevMatch(List<String> mirrorToAnalyze, int prevMatch) {
        // System.out.println("In verticalMatchColumnCount with argument");
        // printMirror(mirrorToAnalyze);
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
            if (isAMatch && (i + 1) != prevMatch) {
                // System.out.println("Found a vertical match at i: " + i);
                return i + 1;
            } else {
                isAMatch = true;
            }
        }
        // System.out.println("Did not find a vertical match");
        return 0;
    }

    public int verticalMatchColumnCount() {
        // System.out.println("Passing on this.mirror as argument to vertical matches:
        // ");
        return verticalMatchColumnCount(this.mirror);
    }

    public long changedSums() {
        int horizontalMatch = horizontalMatchRowCount();
        int verticalMatch = verticalMatchColumnCount();
        for (int x = 0; x < this.mirror.size(); x++) {
            for (int y = 0; y < this.mirror.get(0).length(); y++) {
                String oldString = this.mirror.get(x);
                Character oldChar = this.mirror.get(x).charAt(y);
                Character toChange;
                if (oldChar == '.') {
                    toChange = '#';
                } else {
                    toChange = '.';
                }
                String newString = oldString.substring(0, y) + toChange + oldString.substring(y + 1);
                this.mirror.remove(x);
                this.mirror.add(x, newString);
                int horizontalResult = horizontalMatchRowCountIgnorePrevMatch(this.mirror, horizontalMatch);
                if (horizontalResult > 0 && horizontalResult != horizontalMatch) {
                    this.mirror.remove(x);
                    this.mirror.add(x, oldString);
                    return horizontalResult * 100l;
                }
                int verticalResult = verticalMatchColumnCountIgnorePrevMatch(this.mirror, verticalMatch);
                if (verticalResult > 0 && verticalResult != verticalMatch) {
                    this.mirror.remove(x);
                    this.mirror.add(x, oldString);
                    return verticalResult;
                }
                this.mirror.remove(x);
                this.mirror.add(x, oldString);
            }
        }
        System.out.println("Did not find a match for mirror:");
        printMirror(this.mirror);
        return 0;
    }

    public int horizontalMatchRowCountIgnorePrevMatch(List<String> mirrorStrings, int prevMatch) {
        // System.out.println("Finding horizontal matches: ");
        return verticalMatchColumnCountIgnorePrevMatch(pivotMirror(mirrorStrings), prevMatch);
    }

    public int horizontalMatchRowCount(List<String> mirrorStrings) {
        // System.out.println("Finding horizontal matches: ");
        return verticalMatchColumnCount(pivotMirror(mirrorStrings));
    }

    public int horizontalMatchRowCount() {
        return horizontalMatchRowCount(this.mirror);
    }

    public String reverseString(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            sb.append(string.charAt(i) + "");
        }
        return sb.toString();
    }

    public void printMirror(List<String> mirror) {
        mirror.stream().forEach(System.out::println);
    }

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

        // System.out.println("Mirror reversed: ");
        // printMirror(pivotedStringList);
        return pivotedStringList;
    }

}
