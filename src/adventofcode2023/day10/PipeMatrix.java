package adventofcode2023.day10;

import java.util.ArrayList;
import java.util.List;

import adventofcode2023.TextParserUtil;

public class PipeMatrix {
    private static List<Pipe> pipeMaze;
    private static Pipe startPipe;

    static {
        initializePipeMatrix();
    }

    public static void initializePipeMatrix() {
        List<String> pipeInputData = TextParserUtil.readData("day10.txt");
        pipeMaze = new ArrayList<>();
        int numRows = pipeInputData.size();
        int numCols = pipeInputData.get(0).length();

        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numCols; y++) {
                String code = pipeInputData.get(x).charAt(y) + "";
                Pipe pipe = new Pipe(x, y, code);
                pipeMaze.add(pipe);
                if (pipe.getType() == PipeType.START_END) {
                    startPipe = pipe;
                }
            }
        }
    }

    public static Pipe getPipeAtCoord(Coordinate coord) {
        return pipeMaze.stream().filter(pipe -> pipe.getCoord().equals(coord)).findFirst().orElse(null);
    }

    public static Pipe getStartPipe() {
        return startPipe;
    }

}
