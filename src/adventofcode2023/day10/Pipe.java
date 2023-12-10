package adventofcode2023.day10;

public enum Pipe {
    VERT("|"),
    HOR("-"),
    BEND_NE("L"),
    BEND_NW("J"),
    BEND_SW("7"),
    BEND_SE("F");

    private String code;

    private Pipe(String code) {
        this.code = code;
    }

    public static Pipe parsePipeFromCode(String code) {
        return switch (code) {
            case "|" -> VERT;
            case "-" -> HOR;
            case "L" -> BEND_NE;
            case "J" -> BEND_NW;
            case "7" -> BEND_SW;
            case "F" -> BEND_SE;
            default -> null;
        };
    }

    public Coordinate resultEntryFromNorth() {
        return switch (this) {
            case VERT -> new Coordinate(-1, 0);
            case HOR -> null;
            case BEND_NE -> new Coordinate(1, 1);
            case BEND_NW -> new Coordinate(-1, -1);
            case BEND_SW -> null;
            case BEND_SE -> null;
            default -> null;
        };
    }

    public Coordinate resultEntryFromEast() {
        return switch (this) {
            case VERT -> null;
            case HOR -> new Coordinate(0, -1);
            case BEND_NE -> new Coordinate(-1, -1);
            case BEND_NW -> null;
            case BEND_SW -> null;
            case BEND_SE -> new Coordinate(1, -1);
            default -> null;
        };
    }

    public Coordinate resultEntryFromSouth() {
        return switch (this) {
            case VERT -> new Coordinate(-1, 0);
            case HOR -> null;
            case BEND_NE -> null;
            case BEND_NW -> null;
            case BEND_SW -> new Coordinate(-1, -1);
            case BEND_SE -> new Coordinate(-1, 1);
            default -> null;
        };
    }

    public Coordinate resultEntryFromWest() {
        return switch (this) {
            case VERT -> null;
            case HOR -> new Coordinate(0, 1);
            case BEND_NE -> null;
            case BEND_NW -> new Coordinate(-1, 1);
            case BEND_SW -> new Coordinate(1, 1);
            case BEND_SE -> null;
            default -> null;
        };
    }
}
