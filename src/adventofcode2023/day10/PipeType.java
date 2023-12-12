package adventofcode2023.day10;

public enum PipeType {
    VERT("|"),
    HOR("-"),
    BEND_NE("L"),
    BEND_NW("J"),
    BEND_SW("7"),
    BEND_SE("F"),
    START_END("S"),
    GROUND("."),
    DUMMY_CONNECTION("&"),
    DUMMY_FILLER("D");

    private String code;

    private PipeType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static PipeType parsePipeFromCode(String code) {
        return switch (code) {
            case "|" -> VERT;
            case "-" -> HOR;
            case "L" -> BEND_NE;
            case "J" -> BEND_NW;
            case "7" -> BEND_SW;
            case "F" -> BEND_SE;
            case "S" -> START_END;
            case "." -> GROUND;
            case "&" -> DUMMY_CONNECTION;
            case "D" -> DUMMY_FILLER;
            default -> null;
        };
    }

    // TODO: refactor these! Should just be a boolean if it's possible to enter from
    // the North etc.
    // Especially ugly with the START_END pipe type
    // public Coordinate resultEntryFromNorth() {
    // return switch (this) {
    // case VERT -> new Coordinate(-1, 0);
    // case HOR -> null;
    // case BEND_NE -> new Coordinate(1, 1);
    // case BEND_NW -> new Coordinate(-1, -1);
    // case BEND_SW -> null;
    // case BEND_SE -> null;
    // case START_END -> new Coordinate(0, 0);
    // default -> null;
    // };
    // }

    // public Coordinate resultEntryFromEast() {
    // return switch (this) {
    // case VERT -> null;
    // case HOR -> new Coordinate(0, -1);
    // case BEND_NE -> new Coordinate(-1, -1);
    // case BEND_NW -> null;
    // case BEND_SW -> null;
    // case BEND_SE -> new Coordinate(1, -1);
    // case START_END -> new Coordinate(0, 0);
    // default -> null;
    // };
    // }

    // public Coordinate resultEntryFromSouth() {
    // return switch (this) {
    // case VERT -> new Coordinate(-1, 0);
    // case HOR -> null;
    // case BEND_NE -> null;
    // case BEND_NW -> null;
    // case BEND_SW -> new Coordinate(-1, -1);
    // case BEND_SE -> new Coordinate(-1, 1);
    // case START_END -> new Coordinate(0, 0);
    // default -> null;
    // };
    // }

    // public Coordinate resultEntryFromWest() {
    // return switch (this) {
    // case VERT -> null;
    // case HOR -> new Coordinate(0, 1);
    // case BEND_NE -> null;
    // case BEND_NW -> new Coordinate(-1, 1);
    // case BEND_SW -> new Coordinate(1, 1);
    // case BEND_SE -> null;
    // case START_END -> new Coordinate(0, 0);
    // default -> null;
    // };
    // }

    public boolean canEnterFromNorth(Pipe pipe) {
        return switch (this) {
            case VERT -> true;
            case HOR -> false;
            case BEND_NE -> true;
            case BEND_NW -> true;
            case BEND_SW -> false;
            case BEND_SE -> false;
            case START_END -> pipe.getType() == PipeType.VERT || pipe.getType() == PipeType.BEND_SW
                    || pipe.getType() == PipeType.BEND_SE;
            default -> false;
        };
    }

    public boolean canEnterFromEast(Pipe pipe) {
        return switch (this) {
            case VERT -> false;
            case HOR -> true;
            case BEND_NE -> true;
            case BEND_NW -> false;
            case BEND_SW -> false;
            case BEND_SE -> true;
            case START_END -> pipe.getType() == PipeType.HOR || pipe.getType() == PipeType.BEND_SW
                    || pipe.getType() == PipeType.BEND_NW;
            default -> false;
        };
    }

    public boolean canEnterFromSouth(Pipe pipe) {
        return switch (this) {
            case VERT -> true;
            case HOR -> false;
            case BEND_NE -> false;
            case BEND_NW -> false;
            case BEND_SW -> true;
            case BEND_SE -> true;
            case START_END -> pipe.getType() == PipeType.VERT || pipe.getType() == PipeType.BEND_NE
                    || pipe.getType() == PipeType.BEND_NW;
            default -> false;
        };
    }

    public boolean canEnterFromWest(Pipe pipe) {
        return switch (this) {
            case VERT -> false;
            case HOR -> true;
            case BEND_NE -> false;
            case BEND_NW -> true;
            case BEND_SW -> true;
            case BEND_SE -> false;
            case START_END -> pipe.getType() == PipeType.HOR || pipe.getType() == PipeType.BEND_NE
                    || pipe.getType() == PipeType.BEND_SE;
            default -> false;
        };
    }

    public boolean canLeaveToNorth() {
        return switch (this) {
            case START_END -> true;
            case VERT -> true;
            case HOR -> false;
            case BEND_NE -> true;
            case BEND_NW -> true;
            case BEND_SW -> false;
            case BEND_SE -> false;
            default -> false;
        };
    }

    public boolean canLeaveToEast() {
        return switch (this) {
            case START_END -> true;
            case VERT -> false;
            case HOR -> true;
            case BEND_NE -> true;
            case BEND_NW -> false;
            case BEND_SW -> false;
            case BEND_SE -> true;
            default -> false;
        };
    }

    public boolean canLeaveToSouth() {
        return switch (this) {
            case START_END -> true;
            case VERT -> true;
            case HOR -> false;
            case BEND_NE -> false;
            case BEND_NW -> false;
            case BEND_SW -> true;
            case BEND_SE -> true;
            default -> false;
        };
    }

    public boolean canLeaveToWest() {
        return switch (this) {
            case START_END -> true;
            case VERT -> false;
            case HOR -> true;
            case BEND_NE -> false;
            case BEND_NW -> true;
            case BEND_SW -> true;
            case BEND_SE -> false;
            default -> false;
        };
    }
}
