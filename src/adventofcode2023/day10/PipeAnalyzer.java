package adventofcode2023.day10;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import adventofcode2023.TextParserUtil;

public class PipeAnalyzer {
    private List<String> pipeLines;
    private String[][] pipeMatrix;
    private int startRow;
    private int startColumn;
    private List<String> validPipes = Arrays.asList(new String[] { "|", "-", "L", "J", "7", "F", "." });

    public PipeAnalyzer() {
        // this.pipeLines = TextParserUtil.readData("testday10part1.txt");
        // this.pipeMatrix = TextParserUtil.readDataAsArray("testday10part1.txt");
        this.pipeLines = TextParserUtil.readData("day10.txt");
        this.pipeMatrix = TextParserUtil.readDataAsArray("day10.txt");
        findStartOfPipes();
        System.out.printf("Starting row: %d", this.startRow);
        System.out.printf("Starting col: %d", this.startColumn);
    }

    public void findStartOfPipes() {
        this.startRow = IntStream.range(0, pipeLines.size()).boxed().filter(i -> pipeLines.get(i).contains("S"))
                .findFirst().get();
        this.startColumn = pipeLines.get(startRow).indexOf("S");
    }

    public String createPipeSequence() {
        StringBuilder sb = new StringBuilder();

        int currentRow = this.startRow;
        int currentLine = this.startColumn;
        boolean isEndOfPipe = false;

        while (!isEndOfPipe) {

        }

        return "";
    }

    public Coordinate shiftToNextPipe(Coordinate entryCoord, String pipeType) {
        return switch (pipeType) {
            case "|" -> new Coordinate(-1, 0);
            case "-" -> new Coordinate(0, 0);
            case "L" -> new Coordinate(0, 0);
            case "J" -> new Coordinate(0, 0);
            case "7" -> new Coordinate(0, 0);
            case "F" -> new Coordinate(0, 0);
            case "." -> new Coordinate(0, 0);
            case "S" -> new Coordinate(0, 0);
            default -> null;
        };
    }

}
