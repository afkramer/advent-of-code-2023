package adventofcode2023.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventofcode2023.TextParserUtil;

public class Grid {
    List<String> tileInput = TextParserUtil.readData("day16.txt");
    List<Tile> tiles;
    List<Tile> prevTiles;
    int counter = 0;
    List<LightBeam> currLightBeams;
    List<LightBeam> newLightBeams;
    Map<Coordinate, Direction> startingValues;

    public Grid() {
        initializeStartingValues();
    }

    public void reset() {
        this.tiles = null;
        initializeTiles();
        this.currLightBeams = null;
        this.newLightBeams = null;
    }

    public void initializeTiles() {
        if (this.tiles == null) {
            this.tiles = new ArrayList<>();
        }
        for (int x = 0; x < tileInput.size(); x++) {
            for (int y = 0; y < tileInput.get(0).length(); y++) {
                String code = tileInput.get(x).charAt(y) + "";
                TileType tileType = TileType.parseTileTypeFromString(code);
                Tile newTile = new Tile(tileType, new Coordinate(x, y));
                this.tiles.add(newTile);
            }
        }
    }

    public void addNewLightBeam(LightBeam lightBeam) {
        if (newLightBeams == null) {
            newLightBeams = new ArrayList<>();
        }

        this.newLightBeams.add(lightBeam);
    }

    public Tile getTileAtCoordinate(int xVal, int yVal) {
        return tiles.stream().filter(tile -> tile.getCoordinate().xVal() == xVal && tile.getCoordinate().yVal() == yVal)
                .findFirst().orElse(null);
    }

    public boolean areLightBeamsActive() {
        for (LightBeam lightBeam : this.currLightBeams) {
            if (lightBeam.isActive()) {
                if (TextParserUtil.PRINT_IS_ON) {
                    System.out.printf(
                            "At least light beam %d is active at coord %d, %d. Continue running light beams.%n",
                            lightBeam.getId(), lightBeam.getCurrCoord().xVal(), lightBeam.getCurrCoord().yVal());
                }

                return true;
            }
        }
        if (TextParserUtil.PRINT_IS_ON) {
            System.out.println("No more light beams are active.");
        }

        return false;
    }

    public void initializeStartingValues() {
        int minXVal = 0;
        int maxXVal = tileInput.size() - 1;
        int minYVal = 0;
        int maxYVal = tileInput.get(0).length() - 1;

        startingValues = new HashMap<>();

        for (int y = minYVal; y < maxYVal; y++) {
            startingValues.put(new Coordinate(minXVal, y), Direction.DOWN);
            startingValues.put(new Coordinate(maxXVal, y), Direction.UP);
        }

        for (int x = minXVal; x < maxXVal; x++) {
            startingValues.put(new Coordinate(x, minYVal), Direction.RIGHT);
            startingValues.put(new Coordinate(x, maxYVal), Direction.LEFT);
        }
    }

    public long findLongestChargedPath() {
        long maxLength = 0L;
        for (Map.Entry<Coordinate, Direction> entry : startingValues.entrySet()) {
            runLightBeamsThroughGrid(entry.getKey(), entry.getValue());
            long chargedTiles = findChargedTiles();
            if (chargedTiles > maxLength) {
                maxLength = chargedTiles;
            }
        }
        return maxLength;
    }

    public void runLightBeamsThroughGrid() {
        runLightBeamsThroughGrid(new Coordinate(0, 0), Direction.RIGHT);
    }

    public void runLightBeamsThroughGrid(Coordinate startCoord, Direction direction) {
        // TODO -> add reset method
        // this.tiles = null;
        // initializeTiles();
        // this.currLightBeams = null;
        // this.newLightBeams = null;
        reset();

        if (this.currLightBeams == null) {
            currLightBeams = new ArrayList<>();
            currLightBeams.add(new LightBeam(0, startCoord, true, direction));
        }

        if (TextParserUtil.PRINT_IS_ON) {
            if (counter % 1000 == 0) {
                System.out.printf("%n%n%nPrinting grid at iteration %d: %n", counter);
                printGrid();
            }
        }

        while (areLightBeamsActive()) {
            for (LightBeam lightBeam : this.currLightBeams) {
                if (lightBeam.isActive()) {
                    int xVal = lightBeam.getCurrCoord().xVal();
                    int yVal = lightBeam.getCurrCoord().yVal();

                    Tile nextTile = getTileAtCoordinate(xVal, yVal);

                    if (nextTile != null) {
                        nextTile.getTileType().moveLightBeam(lightBeam, nextTile, this);
                    }

                }

            }

            if (newLightBeams != null) {
                for (LightBeam lightBeam : newLightBeams) {
                    currLightBeams.add(lightBeam);
                }
                newLightBeams.clear();
            }

            deactivateLightBeams();
            counter++;
        }

        if (TextParserUtil.PRINT_IS_ON) {
            System.out.println("\n\nThis is what the grid looks like: ");
            printGrid();
        }

    }

    public void deactivateLightBeams() {
        for (LightBeam lightBeam : this.currLightBeams) {
            if (lightBeam.isActive()) {
                int xVal = lightBeam.getCurrCoord().xVal();
                int yVal = lightBeam.getCurrCoord().yVal();

                if (getTileAtCoordinate(xVal, yVal) == null) {
                    lightBeam.setIsInactive();
                    if (TextParserUtil.PRINT_IS_ON) {
                        System.out.printf("Deactivating beam %d because coord %d, %d is not on the grid. %n",
                                lightBeam.getId(),
                                xVal, yVal);
                    }

                }
            }

        }
    }

    public long findChargedTiles() {
        return this.tiles.stream().filter(Tile::wasVisited).count();
    }

    public void printGrid() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < tileInput.size(); x++) {
            for (int y = 0; y < tileInput.get(0).length(); y++) {
                Tile tile = getTileAtCoordinate(x, y);
                if (tile.wasVisitedFromLeft() && tile.getTileType() == TileType.EMPTY) {
                    sb.append(">");
                } else if (tile.wasVisitedFromRight() && tile.getTileType() == TileType.EMPTY) {
                    sb.append("<");
                } else if (tile.wasVisitedFromAbove() && tile.getTileType() == TileType.EMPTY) {
                    sb.append("V");
                } else if (tile.wasVisitedFromBelow() && tile.getTileType() == TileType.EMPTY) {
                    sb.append("^");
                } else {
                    sb.append(tile.getTileType().toString());
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
