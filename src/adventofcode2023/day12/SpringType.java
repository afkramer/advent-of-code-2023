package adventofcode2023.day12;

public enum SpringType {
    DAMAGED, OPERATIONAL, UNKNOWN;

    public static SpringType parseSpringTypeFromText(String string) {
        return switch (string) {
            case "." -> OPERATIONAL;
            case "#" -> DAMAGED;
            default -> UNKNOWN;
        };
    }
}
