package adventofcode2023.day16;

public enum Direction {
    LEFT, RIGHT, UP, DOWN;

    @Override
    public String toString() {
        return switch (this) {
            case LEFT -> "left";
            case RIGHT -> "right";
            case UP -> "up";
            case DOWN -> "down";
            default -> "not valid";
        };
    }
}
