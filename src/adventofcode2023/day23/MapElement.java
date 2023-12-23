package adventofcode2023.day23;

public enum MapElement {
    PATH("."),
    FOREST("#"),
    SLOPE_UP("^"),
    SLOPE_RIGHT(">"),
    SLOPE_DOWN("v"),
    SLOPE_LEFT("<"),
    UNDEFINED("???");

    private String text;

    private MapElement(String text) {
        this.text = text;
    }

    public static MapElement parseElementFromText(String text) {
        return switch (text) {
            case "." -> PATH;
            case "#" -> FOREST;
            case "^" -> SLOPE_UP;
            case ">" -> SLOPE_RIGHT;
            case "v" -> SLOPE_DOWN;
            case "<" -> SLOPE_LEFT;
            default -> UNDEFINED;
        };
    }
}
