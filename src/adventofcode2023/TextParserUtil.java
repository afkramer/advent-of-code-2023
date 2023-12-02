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

    public static Integer parseInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}
