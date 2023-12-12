package adventofcode2023.day10;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import adventofcode2023.TextParserUtil;

public class PipeMatrix {
    private static String dataToRead = "day10.txt";
    private static List<String> pipeInputData = TextParserUtil.readData(dataToRead);
    private static int numRows = pipeInputData.size();
    private static int numCols = pipeInputData.get(0).length();
    private static Pipe startPipe;
    private static List<Pipe> pipeMaze;
    private static String pathString;

    private static int numBigRows = numRows * 2 + 1;
    private static int numBigCols = numCols * 2 + 1;
    private static List<Pipe> pipeMazeBig;

    private PipeMatrix() {
    }

    public static List<Pipe> initializePipeMatrix() {
        List<Pipe> newPipeMaze = new ArrayList<>();

        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numCols; y++) {
                String code = pipeInputData.get(x).charAt(y) + "";
                Pipe pipe = new Pipe(x, y, code);
                newPipeMaze.add(pipe);
                if (pipe.getType() == PipeType.START_END) {
                    startPipe = pipe;
                }
            }
        }
        pipeMaze = newPipeMaze;
        return newPipeMaze;
    }

    public static List<Pipe> initializeExpandedPipeMatrix() {
        List<Pipe> newPipeMazeBig = new ArrayList<>();

        int pipeCounter = 0;
        for (int x = 0; x < numBigRows; x++) {
            for (int y = 0; y < numBigCols; y++) {
                Pipe newPipe;
                if (x % 2 == 1 && y % 2 == 1) {
                    Pipe existingPipe = pipeMaze.get(pipeCounter);
                    newPipe = new Pipe(existingPipe);
                    newPipe.setCoord(new Coordinate(x, y));
                    pipeCounter++;
                } else {
                    newPipe = new Pipe(x, y);
                }
                newPipeMazeBig.add(newPipe);
            }
        }

        pipeMazeBig = newPipeMazeBig;

        for (Pipe pipe : pipeMazeBig) {
            if (pipe.getType() == null) {
                pipe.setPipeTypeBasedOnSurroundings();
            }
        }

        return newPipeMazeBig;
    }

    public static String createPipeSequence() {
        StringBuilder sb = new StringBuilder();
        int pipeLength = 0;

        Pipe currPipe = startPipe;
        boolean isEndOfPipe = false;

        while (!isEndOfPipe) {

            Pipe pipeToNorth = currPipe.findPipeToNorth();
            Pipe pipeToEast = currPipe.findPipeToEast();
            Pipe pipeToSouth = currPipe.findPipeToSouth();
            Pipe pipeToWest = currPipe.findPipeToWest();
            currPipe.visit();

            if ((currPipe.getType().canLeaveToNorth() && pipeToNorth != null && !pipeToNorth.wasVisited()
                    && pipeToNorth.getType().canEnterFromSouth(currPipe))
                    || (pipeToNorth != null && pipeToNorth.getType() == PipeType.START_END
                            && pipeToNorth.getType().canEnterFromSouth(currPipe) && pipeLength > 1)) {
                sb.append(pipeToNorth.getCode());
                currPipe.setConnectedToPipeNorth(true);
                currPipe = pipeToNorth;
                currPipe.setConnectedToPipeSouth(true);
            } else if ((currPipe.getType().canLeaveToEast() && pipeToEast != null && !pipeToEast.wasVisited()
                    && pipeToEast.getType().canEnterFromWest(currPipe))
                    || (pipeToEast != null && pipeToEast.getType() == PipeType.START_END
                            && pipeToEast.getType().canEnterFromWest(currPipe) && pipeLength > 1)) {
                sb.append(pipeToEast.getCode());
                currPipe.setConnectedToPipeEast(true);
                currPipe = pipeToEast;
                currPipe.setConnectedToPipeWest(true);
            } else if ((currPipe.getType().canLeaveToSouth() && pipeToSouth != null && !pipeToSouth.wasVisited()
                    && pipeToSouth.getType().canEnterFromNorth(currPipe))
                    || (pipeToSouth != null && pipeToSouth.getType() == PipeType.START_END
                            && pipeToSouth.getType().canEnterFromNorth(currPipe) && pipeLength > 1)) {
                sb.append(pipeToSouth.getCode());
                currPipe.setConnectedToPipeSouth(true);
                currPipe = pipeToSouth;
                currPipe.setConnectedToPipeNorth(true);
            } else if ((currPipe.getType().canLeaveToWest() && pipeToWest != null && !pipeToWest.wasVisited()
                    && pipeToWest.getType().canEnterFromEast(currPipe))
                    || (pipeToWest != null && pipeToWest.getType() == PipeType.START_END
                            && pipeToWest.getType().canEnterFromEast(currPipe) && pipeLength > 1)) {
                sb.append(pipeToWest.getCode());
                currPipe.setConnectedToPipeWest(true);
                currPipe = pipeToWest;
                currPipe.setConnectedToPipeEast(true);
            }

            if (currPipe.getType() == PipeType.START_END) {
                isEndOfPipe = true;
            }

            pipeLength++;
        }

        pathString = sb.toString();
        return sb.toString();
    }

    public static int getFarthestRoute() {
        return pathString.length() / 2;
    }

    // TODO refactor to take x and y coordinates instead of a coord -> too many
    // objects created
    public static Pipe getPipeAtCoord(Coordinate coord) {
        return pipeMaze.stream().filter(pipe -> pipe.getCoord().equals(coord)).findFirst().orElse(null);
    }

    public static Pipe getPipeAtCoordBig(Coordinate coord) {
        return pipeMazeBig.stream().filter(pipe -> pipe.getCoord().equals(coord)).findFirst().orElse(null);
    }

    public static long countInteriorTiles() {
        fillPipes();

        return pipeMazeBig.stream()
                .filter(pipe -> pipe.getFill() != FillType.OUTSIDE && !pipe.wasVisited()
                        && pipe.getType() != PipeType.DUMMY_FILLER)
                .count();

    }

    public static void fillPipes() {
        List<Pipe> pipesToProcess = new ArrayList<>();
        pipesToProcess.add(getPipeAtCoordBig(new Coordinate(0, 0)));
        Pipe currentPipe;

        while (!pipesToProcess.isEmpty()) {
            final List<Pipe> notFilled = pipesToProcess.stream()
                    .filter(p -> p.getFill() == FillType.NONE).toList();
            if (notFilled.isEmpty()) {
                break;
            }

            currentPipe = notFilled.get(0);

            Pipe pipeToNorth = currentPipe.findPipeToNorthBig();
            Pipe pipeToEast = currentPipe.findPipeToEastBig();
            Pipe pipeToSouth = currentPipe.findPipeToSouthBig();
            Pipe pipeToWest = currentPipe.findPipeToWestBig();

            boolean isOnEdgeOfMap = pipeToNorth == null || pipeToEast == null || pipeToSouth == null
                    || pipeToWest == null;

            boolean neighborIsOutside = (pipeToNorth != null && pipeToNorth.getFill() == FillType.OUTSIDE)
                    || (pipeToEast != null && pipeToEast.getFill() == FillType.OUTSIDE)
                    || (pipeToSouth != null && pipeToSouth.getFill() == FillType.OUTSIDE)
                    || (pipeToWest != null && pipeToWest.getFill() == FillType.OUTSIDE);

            if (currentPipe.wasVisited() || currentPipe.getType() == PipeType.DUMMY_CONNECTION) {
                currentPipe.setFill(FillType.SEQUENCE);
            } else if (isOnEdgeOfMap || neighborIsOutside) {
                currentPipe.setFill(FillType.OUTSIDE);
            }

            if (pipeToNorth != null && pipeToNorth.getFill() == FillType.NONE && !pipeToNorth.wasCounted()
                    && !pipeToNorth.wasVisited()) {
                pipesToProcess.add(pipeToNorth);
                pipeToNorth.count();
            }

            if (pipeToEast != null && pipeToEast.getFill() == FillType.NONE && !pipeToEast.wasCounted()
                    && !pipeToEast.wasVisited()) {
                pipesToProcess.add(pipeToEast);
                pipeToEast.count();
            }

            if (pipeToSouth != null && pipeToSouth.getFill() == FillType.NONE && !pipeToSouth.wasCounted()
                    && !pipeToSouth.wasVisited()) {
                pipesToProcess.add(pipeToSouth);
                pipeToSouth.count();
            }

            if (pipeToWest != null && pipeToWest.getFill() == FillType.NONE && !pipeToWest.wasCounted()
                    && !pipeToWest.wasVisited()) {
                pipesToProcess.add(pipeToWest);
                pipeToWest.count();
            }

        }
    }

    public static void printPipeMaze() {
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numCols; y++) {
                sb.append(pipeMaze.get(counter).getCode());
                counter++;
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void printPipeMazeBig() {
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < numBigRows; x++) {
            for (int y = 0; y < numBigCols; y++) {
                sb.append(pipeMazeBig.get(counter).getCode());
                counter++;
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void printPipeMazeBigFillers() {
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < numBigRows; x++) {
            for (int y = 0; y < numBigCols; y++) {
                sb.append(pipeMazeBig.get(counter).getFill().getString());
                counter++;
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void saveBigPipeMazeToFile() {
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < numBigRows; x++) {
            for (int y = 0; y < numBigCols; y++) {
                String code = pipeMazeBig.get(counter).getCode();
                sb.append(code.equals("none") ? " " : code);
                counter++;
            }
            sb.append("\n");
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("./res/maze.txt"))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
