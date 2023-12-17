package adventofcode2023.day16;

import adventofcode2023.TextParserUtil;

public enum TileType {
    EMPTY(".", false),
    MIRROR_DOWN("\\", false),
    MIRROR_UP("/", false),
    SPLITTER_VERT("|", true),
    SPLITTER_HOR("-", true),
    INVALID("", false);

    private String code;
    private boolean splitsBeam;

    private TileType(String code, boolean splitsBeam) {
        this.code = code;
        this.splitsBeam = splitsBeam;
    }

    public String getCode() {
        return this.code;
    }

    public boolean isSplitter() {
        return this.splitsBeam;
    }

    public static TileType parseTileTypeFromString(String code) {
        return switch (code) {
            case "." -> EMPTY;
            case "\\" -> MIRROR_DOWN;
            case "/" -> MIRROR_UP;
            case "|" -> SPLITTER_VERT;
            case "-" -> SPLITTER_HOR;
            default -> INVALID;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case EMPTY -> ".";
            case MIRROR_DOWN -> "\\";
            case MIRROR_UP -> "/";
            case SPLITTER_VERT -> "|";
            case SPLITTER_HOR -> "-";
            default -> "invalid";
        };
    }

    // TODO: This could be a use case for reflection?
    public void moveLightBeam(LightBeam lightBeam, Tile tileToEnter, Grid grid) {
        switch (lightBeam.getCurrDirection()) {
            case LEFT -> enterTileFromRight(lightBeam, tileToEnter, grid);
            case RIGHT -> enterTileFromLeft(lightBeam, tileToEnter, grid);
            case UP -> enterTileFromBelow(lightBeam, tileToEnter, grid);
            case DOWN -> enterTileFromAbove(lightBeam, tileToEnter, grid);
            default -> throw new IllegalArgumentException("Could not pass on light beam in method moveLightBeam");
        }
    }

    public void enterTileFromLeft(LightBeam lightBeam, Tile tileToEnter, Grid grid) {
        if (!tileToEnter.getTileType().isSplitter() && tileToEnter.wasVisitedFromLeft()) {
            lightBeam.setIsInactive();
            if (TextParserUtil.PRINT_IS_ON) {
                System.out.printf(
                        "Deactivating lightbeam %d -> it entered tile %s at %d, %d but it has already been visited from the left%n",
                        lightBeam.getId(), tileToEnter.getTileType().getCode(), tileToEnter.getCoordinate().xVal(),
                        tileToEnter.getCoordinate().yVal());
            }
            return;
        }

        int prevXVal = lightBeam.getCurrCoord().xVal();
        int prevYVal = lightBeam.getCurrCoord().yVal();

        lightBeam.addPreviousCoord(lightBeam.getCurrCoord());
        if (TextParserUtil.PRINT_IS_ON) {
            System.out.printf("Beam %d at tile %d, %d (%s) entered from the left, about to process%n",
                    lightBeam.getId(),
                    prevXVal, prevYVal, tileToEnter.getTileType().toString());
        }

        switch (tileToEnter.getTileType()) {
            case EMPTY -> lightBeam.setCurrCoord(new Coordinate(prevXVal, prevYVal + 1));
            case MIRROR_DOWN -> {
                lightBeam.setCurrCoord(new Coordinate(prevXVal + 1, prevYVal));
                lightBeam.setCurrDirection(Direction.DOWN);
            }
            case MIRROR_UP -> {
                lightBeam.setCurrCoord(new Coordinate(prevXVal - 1, prevYVal));
                lightBeam.setCurrDirection(Direction.UP);
            }
            case SPLITTER_VERT -> {
                lightBeam.setCurrCoord(new Coordinate(prevXVal - 1, prevYVal));
                lightBeam.setCurrDirection(Direction.UP);

                LightBeam newBeam = new LightBeam(TextParserUtil.provideId(), new Coordinate(prevXVal + 1, prevYVal),
                        true,
                        Direction.DOWN);
                grid.addNewLightBeam(newBeam);
                if (TextParserUtil.PRINT_IS_ON) {
                    System.out.printf(
                            "New light beam %d added. Currently at coord %d, %d, (%s) next coord %d, %d and going %s%n",
                            newBeam.getId(), prevXVal, prevYVal, tileToEnter.getTileType().toString(),
                            newBeam.getCurrCoord().xVal(), newBeam.getCurrCoord().yVal(),
                            newBeam.getCurrDirection().toString());
                }

            }
            case SPLITTER_HOR -> lightBeam.setCurrCoord(new Coordinate(prevXVal, prevYVal + 1));
            default -> throw new IllegalArgumentException("No matching result for enter tile from left");
        }

        if (!tileToEnter.getTileType().isSplitter()) {
            if (TextParserUtil.PRINT_IS_ON) {
                System.out.printf("Marking tile %s at %d, %d as visited from the left.%n",
                        tileToEnter.getTileType().getCode(), tileToEnter.getCoordinate().xVal(),
                        tileToEnter.getCoordinate().yVal());
            }

            tileToEnter.visitFromLeft();
        }

        tileToEnter.visit();

        if (TextParserUtil.PRINT_IS_ON) {
            int newX = lightBeam.getCurrCoord().xVal();
            int newY = lightBeam.getCurrCoord().yVal();
            System.out.printf("Beam %d at tile %d, %d processed, new coord: %d, %d and going %s%n", lightBeam.getId(),
                    prevXVal, prevYVal, newX, newY, lightBeam.getCurrDirection().toString());
        }

    }

    public void enterTileFromRight(LightBeam lightBeam, Tile tileToEnter, Grid grid) {
        if (!tileToEnter.getTileType().isSplitter() && tileToEnter.wasVisitedFromRight()) {
            lightBeam.setIsInactive();
            if (TextParserUtil.PRINT_IS_ON) {
                System.out.printf(
                        "Deactivating lightbeam %d -> it entered tile %s at %d, %d but it has already been visited from the right%n",
                        lightBeam.getId(), tileToEnter.getTileType().getCode(), tileToEnter.getCoordinate().xVal(),
                        tileToEnter.getCoordinate().yVal());
            }

            return;
        }

        int prevXVal = lightBeam.getCurrCoord().xVal();
        int prevYVal = lightBeam.getCurrCoord().yVal();

        lightBeam.addPreviousCoord(lightBeam.getCurrCoord());
        if (TextParserUtil.PRINT_IS_ON) {
            System.out.printf("Beam %d at tile %d, %d (%s) entered from the right, about to process%n",
                    lightBeam.getId(),
                    prevXVal, prevYVal, tileToEnter.getTileType().toString());
        }

        switch (tileToEnter.getTileType()) {
            case EMPTY -> lightBeam.setCurrCoord(new Coordinate(prevXVal, prevYVal - 1));
            case MIRROR_DOWN -> {
                lightBeam.setCurrCoord(new Coordinate(prevXVal - 1, prevYVal));
                lightBeam.setCurrDirection(Direction.UP);
            }
            case MIRROR_UP -> {
                lightBeam.setCurrCoord(new Coordinate(prevXVal + 1, prevYVal));
                lightBeam.setCurrDirection(Direction.DOWN);
            }
            case SPLITTER_VERT -> {
                lightBeam.setCurrCoord(new Coordinate(prevXVal - 1, prevYVal));
                lightBeam.setCurrDirection(Direction.UP);

                LightBeam newBeam = new LightBeam(TextParserUtil.provideId() + 1,
                        new Coordinate(prevXVal + 1, prevYVal), true,
                        Direction.DOWN);
                grid.addNewLightBeam(newBeam);
                if (TextParserUtil.PRINT_IS_ON) {
                    System.out.printf(
                            "New light beam %d added. Currently at coord %d, %d, (%s) next coord %d, %d and going %s%n",
                            newBeam.getId(), prevXVal, prevYVal, tileToEnter.getTileType().toString(),
                            newBeam.getCurrCoord().xVal(), newBeam.getCurrCoord().yVal(),
                            newBeam.getCurrDirection().toString());

                }

            }
            case SPLITTER_HOR -> lightBeam.setCurrCoord(new Coordinate(prevXVal, prevYVal - 1));
            default -> throw new IllegalArgumentException("No matching result for enter tile from right");
        }

        if (!tileToEnter.getTileType().isSplitter()) {
            tileToEnter.visitFromRight();
            if (TextParserUtil.PRINT_IS_ON) {
                System.out.printf("Marking tile %s at %d, %d as visited from the right.%n",
                        tileToEnter.getTileType().getCode(), tileToEnter.getCoordinate().xVal(),
                        tileToEnter.getCoordinate().yVal());
            }

        }

        tileToEnter.visit();

        if (TextParserUtil.PRINT_IS_ON) {
            int newX = lightBeam.getCurrCoord().xVal();
            int newY = lightBeam.getCurrCoord().yVal();
            System.out.printf("Beam %d at tile %d, %d processed, new coord: %d, %d and going %s%n", lightBeam.getId(),
                    prevXVal, prevYVal, newX, newY, lightBeam.getCurrDirection().toString());
        }

    }

    public void enterTileFromAbove(LightBeam lightBeam, Tile tileToEnter, Grid grid) {
        if (!tileToEnter.getTileType().isSplitter() && tileToEnter.wasVisitedFromAbove()) {
            lightBeam.setIsInactive();
            if (TextParserUtil.PRINT_IS_ON) {
                System.out.printf(
                        "Deactivating lightbeam %d -> it entered tile %s at %d, %d but it has already been visited from above%n",
                        lightBeam.getId(), tileToEnter.getTileType().getCode(), tileToEnter.getCoordinate().xVal(),
                        tileToEnter.getCoordinate().yVal());
            }

            return;
        }

        int prevXVal = lightBeam.getCurrCoord().xVal();
        int prevYVal = lightBeam.getCurrCoord().yVal();

        lightBeam.addPreviousCoord(lightBeam.getCurrCoord());
        if (TextParserUtil.PRINT_IS_ON) {
            System.out.printf("Beam %d at tile %d, %d (%s) entered from above, about to process%n", lightBeam.getId(),
                    prevXVal, prevYVal, tileToEnter.getTileType().toString());
        }

        switch (tileToEnter.getTileType()) {
            case EMPTY -> lightBeam.setCurrCoord(new Coordinate(prevXVal + 1, prevYVal));
            case MIRROR_DOWN -> {
                lightBeam.setCurrCoord(new Coordinate(prevXVal, prevYVal + 1));
                lightBeam.setCurrDirection(Direction.RIGHT);
            }
            case MIRROR_UP -> {
                lightBeam.setCurrCoord(new Coordinate(prevXVal, prevYVal - 1));
                lightBeam.setCurrDirection(Direction.LEFT);
            }
            case SPLITTER_VERT -> lightBeam.setCurrCoord(new Coordinate(prevXVal + 1, prevYVal));
            case SPLITTER_HOR -> {
                lightBeam.setCurrCoord(new Coordinate(prevXVal, prevYVal - 1));
                lightBeam.setCurrDirection(Direction.LEFT);

                LightBeam newBeam = new LightBeam(TextParserUtil.provideId() + 1,
                        new Coordinate(prevXVal, prevYVal + 1), true,
                        Direction.RIGHT);
                grid.addNewLightBeam(newBeam);
                if (TextParserUtil.PRINT_IS_ON) {
                    System.out.printf(
                            "New light beam %d added. Currently at coord %d, %d, (%s) next coord %d, %d and going %s%n",
                            newBeam.getId(), prevXVal, prevYVal, tileToEnter.getTileType().toString(),
                            newBeam.getCurrCoord().xVal(), newBeam.getCurrCoord().yVal(),
                            newBeam.getCurrDirection().toString());
                }

            }
            default -> throw new IllegalArgumentException("No matching result for enter tile from above");
        }

        if (!tileToEnter.getTileType().isSplitter()) {
            if (TextParserUtil.PRINT_IS_ON) {
                System.out.printf("Marking tile %s at %d, %d as visited from above.%n",
                        tileToEnter.getTileType().getCode(),
                        tileToEnter.getCoordinate().xVal(), tileToEnter.getCoordinate().yVal());
            }

            tileToEnter.visitFromAbove();
        }

        tileToEnter.visit();

        if (TextParserUtil.PRINT_IS_ON) {
            int newX = lightBeam.getCurrCoord().xVal();
            int newY = lightBeam.getCurrCoord().yVal();
            System.out.printf("Beam %d at tile %d, %d processed, new coord: %d, %d and going %s%n", lightBeam.getId(),
                    prevXVal, prevYVal, newX, newY, lightBeam.getCurrDirection().toString());
        }

    }

    public void enterTileFromBelow(LightBeam lightBeam, Tile tileToEnter, Grid grid) {
        if (!tileToEnter.getTileType().isSplitter() && tileToEnter.wasVisitedFromBelow()) {
            if (TextParserUtil.PRINT_IS_ON) {
                System.out.printf(
                        "Deactivating lightbeam %d -> it entered tile %s at %d, %d but it has already been visited from below%n",
                        lightBeam.getId(), tileToEnter.getTileType().getCode(), tileToEnter.getCoordinate().xVal(),
                        tileToEnter.getCoordinate().yVal());
            }

            lightBeam.setIsInactive();
            return;
        }

        int prevXVal = lightBeam.getCurrCoord().xVal();
        int prevYVal = lightBeam.getCurrCoord().yVal();

        lightBeam.addPreviousCoord(lightBeam.getCurrCoord());
        if (TextParserUtil.PRINT_IS_ON) {
            System.out.printf("Beam %d at tile %d, %d (%s) entered from below, about to process%n", lightBeam.getId(),
                    prevXVal, prevYVal, tileToEnter.getTileType().toString());
        }

        switch (tileToEnter.getTileType()) {
            case EMPTY -> lightBeam.setCurrCoord(new Coordinate(prevXVal - 1, prevYVal));
            case MIRROR_DOWN -> {
                lightBeam.setCurrCoord(new Coordinate(prevXVal, prevYVal - 1));
                lightBeam.setCurrDirection(Direction.LEFT);
            }
            case MIRROR_UP -> {
                lightBeam.setCurrCoord(new Coordinate(prevXVal, prevYVal + 1));
                lightBeam.setCurrDirection(Direction.RIGHT);
            }
            case SPLITTER_VERT -> lightBeam.setCurrCoord(new Coordinate(prevXVal - 1, prevYVal));
            case SPLITTER_HOR -> {
                lightBeam.setCurrCoord(new Coordinate(prevXVal, prevYVal - 1));
                lightBeam.setCurrDirection(Direction.LEFT);
                LightBeam newBeam = new LightBeam(TextParserUtil.provideId() + 1,
                        new Coordinate(prevXVal, prevYVal + 1), true,
                        Direction.RIGHT);
                grid.addNewLightBeam(newBeam);
                if (TextParserUtil.PRINT_IS_ON) {
                    System.out.printf(
                            "New light beam %d added. Currently at coord %d, %d, (%s) next coord %d, %d and going %s%n",
                            newBeam.getId(), prevXVal, prevYVal, tileToEnter.getTileType().toString(),
                            newBeam.getCurrCoord().xVal(), newBeam.getCurrCoord().yVal(),
                            newBeam.getCurrDirection().toString());
                }

            }
            default -> throw new IllegalArgumentException("No matching result for enter tile from below");
        }

        if (!tileToEnter.getTileType().isSplitter()) {
            if (TextParserUtil.PRINT_IS_ON) {
                System.out.printf("Marking tile %s at %d, %d as visited from below.%n",
                        tileToEnter.getTileType().getCode(),
                        tileToEnter.getCoordinate().xVal(), tileToEnter.getCoordinate().yVal());
            }

            tileToEnter.visitFromBelow();
        }

        tileToEnter.visit();

        if (TextParserUtil.PRINT_IS_ON) {
            int newX = lightBeam.getCurrCoord().xVal();
            int newY = lightBeam.getCurrCoord().yVal();
            System.out.printf("Beam %d at tile %d, %d processed, new coord: %d, %d and going %s%n", lightBeam.getId(),
                    prevXVal, prevYVal, newX, newY, lightBeam.getCurrDirection().toString());
        }

    }

}
