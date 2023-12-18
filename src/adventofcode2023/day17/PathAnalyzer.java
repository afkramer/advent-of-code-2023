package adventofcode2023.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import adventofcode2023.TextParserUtil;

public class PathAnalyzer {
    private static final List<String> blockData = TextParserUtil.readData("testday17.txt");
    public static final int MIN_X = 0;
    public static final int X_SIZE = blockData.size();
    public static final int MIN_Y = 0;
    public static final int Y_SIZE = blockData.get(0).length();
    private final int[][] map = new int[X_SIZE][Y_SIZE];

    private List<Path> paths = new ArrayList<>();

    public PathAnalyzer() {
        initializeBlocks();
    }

    private void initializeBlocks() {
        for (int x = MIN_X; x < X_SIZE; x++) {
            for (int y = MIN_Y; y < Y_SIZE; y++) {
                map[x][y] = TextParserUtil.parseInteger(blockData.get(x).charAt(x) + "");
            }
        }
    }

    private void traversePaths() {
        Path firstPath = new Path(MIN_X, MIN_Y, Direction.EAST);
        paths.add(firstPath);
        int counter = 0;

        while (!allPathsFinished()) {
            System.out.printf("%d active paths at iteration %d%n", paths.size(), counter);
            ListIterator<Path> pathsIter = paths.listIterator();
            while (pathsIter.hasNext()) {
                Path path = pathsIter.next();
                if (path.canGoLeft() && path.canGoRight()) {
                    path.goLeft();
                    path.increaseHeatLossBy(getHeatLossAtCoord(path.getXVal(), path.getYVal()));

                    Path nextPath = new Path(path);

                    nextPath.goRight();
                    nextPath.increaseHeatLossBy(getHeatLossAtCoord(nextPath.getXVal(), nextPath.getYVal()));
                    pathsIter.add(nextPath);
                } else if (path.canGoLeft()) {
                    path.goLeft();
                    path.increaseHeatLossBy(getHeatLossAtCoord(path.getXVal(), path.getYVal()));
                } else if (path.canGoRight()) {
                    path.goRight();
                    path.increaseHeatLossBy(getHeatLossAtCoord(path.getXVal(), path.getYVal()));
                } else if (!path.canGoRight() && !path.canGoLeft() && !path.isAtTargetBlock()) {
                    path.setInactive();
                    pathsIter.remove();
                }

            }
            counter++;
        }
    }

    private int getHeatLossAtCoord(int xVal, int yVal) {
        return map[xVal][yVal];
    }

    private boolean allPathsFinished() {
        return this.paths.stream().allMatch(path -> path.isAtTargetBlock() || !path.isActive());
    }

    private void printHeatLossesPerPath() {
        for (Path path : paths) {
            System.out.printf("Heat loss: %d%n", path.getHeatLoss());
        }
    }

    public int getSmallestPath() {
        traversePaths();
        System.out.println();
        printHeatLossesPerPath();
        return this.paths.stream().map(Path::getHeatLoss).min(Integer::compareTo).orElse(0);
    }

}
