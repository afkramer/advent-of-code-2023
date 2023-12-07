package adventofcode2023.day05v2;

public enum ElementType {
    SEED, SOIL, FERTILIZER, WATER, LIGHT, TEMPERATURE, HUMIDITY, LOCATION, UNDEFINED;

    public ElementType parseElementTypeFromString(String string) {
        return switch (string) {
            case "seed" -> SEED;
            case "soil" -> SOIL;
            case "fertilizer" -> FERTILIZER;
            case "water" -> WATER;
            case "light" -> LIGHT;
            case "temperature" -> TEMPERATURE;
            case "humidity" -> HUMIDITY;
            case "location" -> LOCATION;
            default -> UNDEFINED;
        };
    }
}
