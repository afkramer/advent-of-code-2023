package adventofcode2023.day05;

import adventofcode2023.TextParserUtil;

public class Almanac {
    private static ElementMapping seedToSoil = new ElementMapping(ElementType.SEED, ElementType.SOIL);
    private static ElementMapping soilToFertilizer = new ElementMapping(ElementType.SOIL, ElementType.FERTILIZER);
    private static ElementMapping fertilizerToWater = new ElementMapping(ElementType.FERTILIZER, ElementType.WATER);
    private static ElementMapping waterToLight = new ElementMapping(ElementType.WATER, ElementType.LIGHT);
    private static ElementMapping lightToTemperature = new ElementMapping(ElementType.LIGHT, ElementType.TEMPERATURE);
    private static ElementMapping temperatureToHumidity = new ElementMapping(ElementType.TEMPERATURE,
            ElementType.HUMIDITY);
    private static ElementMapping humidityToLocation = new ElementMapping(ElementType.HUMIDITY, ElementType.LOCATION);

    static {
        initializeMapping(seedToSoil, MappingValues.getSeedToSoilRawMappings());
        initializeMapping(soilToFertilizer, MappingValues.getSoilToFertilizerRawMappings());
        initializeMapping(fertilizerToWater, MappingValues.getFertilizerToWaterRawMappings());
        initializeMapping(waterToLight, MappingValues.getWaterToLightRawMappings());
        initializeMapping(lightToTemperature, MappingValues.getLightToTemperatureRawMappings());
        initializeMapping(temperatureToHumidity, MappingValues.getTemperatureToHumidityRawMappings());
        initializeMapping(humidityToLocation, MappingValues.getHumidityToLocationRawMappings());
    }

    public static void initializeMapping(ElementMapping mapping, String[] mappingParameters) {
        for (String mappingParameter : mappingParameters) {
            String[] mappingElements = mappingParameter.split("\\s");
            Long destinationValue = TextParserUtil.parseLong(mappingElements[0]);
            Long sourceValue = TextParserUtil.parseLong(mappingElements[1]);
            Long range = TextParserUtil.parseLong(mappingElements[2]);

            mapping.addMappingValue(sourceValue, destinationValue, range);
        }

    }

    public static ElementMapping getSeedToSoil() {
        return seedToSoil;
    }

    public static ElementMapping getSoilToFertilizer() {
        return soilToFertilizer;
    }

    public static ElementMapping getFertilizerToWater() {
        return fertilizerToWater;
    }

    public static ElementMapping getWaterToLight() {
        return waterToLight;
    }

    public static ElementMapping getLightToTemperature() {
        return lightToTemperature;
    }

    public static ElementMapping getTemperatureToHumidity() {
        return temperatureToHumidity;
    }

    public static ElementMapping getHumidityToLocation() {
        return humidityToLocation;
    }

}
