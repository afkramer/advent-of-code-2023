package adventofcode2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TextParserUtil {
    public static List<String> readData(String fileName) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("./res/" + fileName))) {
            return reader.lines()
                    .toList();
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
}
