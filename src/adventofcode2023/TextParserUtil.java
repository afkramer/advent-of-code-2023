package adventofcode2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextParserUtil {
    public static final boolean PRINT_IS_ON = false;
    public static int maxId = 0;

    public static List<String> readData(String fileName) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("./res/" + fileName))) {
            return reader.lines()
                    .toList();
        } catch (IOException e) {
            System.err.print("Could not read data.");
            return null;
        }
    }

    public static List<String> readModifiableData(String fileName) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("./res/" + fileName))) {
            List<String> modifiableList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                modifiableList.add(line);
            }
            return modifiableList;
        } catch (IOException e) {
            System.err.print("Could not read data.");
            return null;
        }
    }

    public static String[][] readDataAsArray(String fileName) {
        List<String> lines = readData(fileName);
        String[][] matrix = new String[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            matrix[i] = lines.get(i).split("");
        }
        return matrix;
    }

    public static Integer parseInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    public static Long parseLong(String string) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    public static List<String> pivotStringList(List<String> toPivot) {
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

        return pivotedStringList;
    }

    public static String reverseString(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            sb.append(string.charAt(i) + "");
        }
        return sb.toString();
    }

    // start here -> pretty much last thing to
    public static List<String> pivotBackStringList(List<String> pivotedMap) {
        int numRowsSource = pivotedMap.size();
        int numColsSource = pivotedMap.get(0).length();

        List<String> pivotedBackStrings = new ArrayList<>();

        for (int y = 0; y < numColsSource; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < numRowsSource; x++) {
                sb.append(pivotedMap.get(x).charAt(y) + "");
            }
            pivotedBackStrings.add(reverseString(sb.toString()));
        }

        return pivotedBackStrings;
    }

    public static void printLists(List<String> listsToPrint) {
        listsToPrint.stream().forEach(System.out::println);

    }

    public static List<String> copyList(List<String> listToCopy) {
        List<String> copiedList = new ArrayList<>();
        copiedList.addAll(listToCopy);
        return copiedList;
    }

    public static int provideId() {
        return maxId++;
    }
}
