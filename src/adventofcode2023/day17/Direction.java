package adventofcode2023.day17;

import java.util.List;

public enum Direction {
    NORTH, EAST, SOUTH, WEST, UNDEFINED;

    public static List<Direction> getDirections() {
        return List.of(NORTH, EAST, SOUTH, WEST);
    }

    public static Direction getOppositeDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> SOUTH;
            case EAST -> WEST;
            case SOUTH -> NORTH;
            case WEST -> EAST;
            default -> UNDEFINED;
        };
    }
}
