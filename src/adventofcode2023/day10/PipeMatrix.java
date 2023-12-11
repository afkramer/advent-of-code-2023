package adventofcode2023.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

    public static List<Pipe> getPipeMaze() {
        return pipeMaze;
    }

    public static List<Pipe> findNeighboringUnvisitedEnclosedPipes(Pipe pipe) {
        List<Pipe> unvisitedEnclosedPipes = new ArrayList<>();
        List<Pipe> pipesToProcess = new ArrayList<>();
        pipesToProcess.add(pipe);
        Pipe currentPipe;

        while (true) {
            final List<Pipe> notCounted = pipesToProcess.stream().filter(p -> !p.wasCounted() && !p.wasVisited())
                    .toList();
            if (notCounted.isEmpty()) {
                break;
            }

            currentPipe = notCounted.get(0);

            // Just in case a visited or already counted pipe is passed in to analyze
            if (currentPipe.wasVisited() || currentPipe.wasCounted()) {
                return unvisitedEnclosedPipes;
            }

            Pipe pipeToNorth = currentPipe.findPipeToNorth();
            Pipe pipeToEast = currentPipe.findPipeToEast();
            Pipe pipeToSouth = currentPipe.findPipeToSouth();
            Pipe pipeToWest = currentPipe.findPipeToWest();

            boolean isOnEdgeOfMap = pipeToNorth == null || pipeToEast == null || pipeToSouth == null
                    || pipeToWest == null;

            if (isOnEdgeOfMap) {
                // None of the bordering values are to be counted
                for (Pipe p : unvisitedEnclosedPipes) {
                    p.count();
                }

                return new ArrayList<>();
            } else {
                unvisitedEnclosedPipes.add(currentPipe);
                currentPipe.count();
            }

            if (!pipeToNorth.wasVisited() && !pipeToNorth.wasCounted()) {
                pipesToProcess.add(pipeToNorth);
            }

            if (!pipeToEast.wasVisited() && !pipeToEast.wasCounted()) {
                pipesToProcess.add(pipeToEast);
            }

            if (!pipeToSouth.wasVisited() && !pipeToSouth.wasCounted()) {
                pipesToProcess.add(pipeToSouth);
            }

            if (!pipeToWest.wasVisited() && !pipeToWest.wasCounted()) {
                pipesToProcess.add(pipeToWest);
            }

            // pipesToProcess.remove();

        }

        return unvisitedEnclosedPipes;
    }

    public static List<Pipe> findNeighboringUnvisitedEnclosedPipesOld(Pipe pipe) {
        List<Pipe> unvisitedEnclosedPipes = new ArrayList<>();
        List<Pipe> toProcess = new ArrayList<>();
        toProcess.add(pipe);
        ListIterator<Pipe> pipesToProcess = toProcess.listIterator();
        Pipe currentPipe;

        while (pipesToProcess.hasNext()) {
            currentPipe = pipesToProcess.next();

            // Just in case a visited or already counted pipe is passed in to analyze
            if (currentPipe.wasVisited() || currentPipe.wasCounted()) {
                return unvisitedEnclosedPipes;
            }

            Pipe pipeToNorth = currentPipe.findPipeToNorth();
            Pipe pipeToEast = currentPipe.findPipeToEast();
            Pipe pipeToSouth = currentPipe.findPipeToSouth();
            Pipe pipeToWest = currentPipe.findPipeToWest();

            boolean isOnEdgeOfMap = pipeToNorth == null || pipeToEast == null || pipeToSouth == null
                    || pipeToWest == null;

            if (isOnEdgeOfMap) {
                // None of the bordering values are to be counted
                for (Pipe p : unvisitedEnclosedPipes) {
                    p.count();
                }

                return new ArrayList<>();
            } else {
                unvisitedEnclosedPipes.add(currentPipe);
                currentPipe.count();
            }

            if (!pipeToNorth.wasVisited() && !pipeToNorth.wasCounted()) {
                pipesToProcess.add(pipeToNorth);
            }

            if (!pipeToEast.wasVisited() && !pipeToEast.wasCounted()) {
                pipesToProcess.add(pipeToEast);
            }

            if (!pipeToSouth.wasVisited() && !pipeToSouth.wasCounted()) {
                pipesToProcess.add(pipeToSouth);
            }

            if (!pipeToWest.wasVisited() && !pipeToWest.wasCounted()) {
                pipesToProcess.add(pipeToWest);
            }

            // pipesToProcess.remove();

        }

        return unvisitedEnclosedPipes;
    }

}
