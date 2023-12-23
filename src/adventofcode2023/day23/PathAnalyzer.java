package adventofcode2023.day23;

import java.util.ArrayList;
import java.util.List;

import adventofcode2023.TextParserUtil;

public class PathAnalyzer {
    private String[][] map = TextParserUtil.readDataAsArray("testday23.txt");
    private int minX = 0;
    private int maxX = map.length;
    private int minY = 0;
    private int maxY = map[0].length;

    public int determinePaths() {
        List<List<Coordinate>> paths = new ArrayList<>();

        Coordinate startCoordinate = new Coordinate(0, 1, MapElement.parseElementFromText("."));
        List<Coordinate> nextCoordinates = findNextoordinates(startCoordinate);

        while (!nextCoordinates.isEmpty()) {

        }

        return 0;
    }

    public List<Coordinate> findNextCoordinates(Coordinate coord) {
        List<Coordinate> nextCoordinates = new ArrayList<>();
        int[][] shifts = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] shift : shifts) {
            if (shift)
        }
    }

    public boolean isWithinBounds(String axis) {

    }

    public List<Coordinate> copyPath(List<Coordinate> pathToCopy) {
        List<Coordinate> copy = new ArrayList<>();
        copy.addAll(pathToCopy);
        return copy;
    }

}
