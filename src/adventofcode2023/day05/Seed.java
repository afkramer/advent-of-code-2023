package adventofcode2023.day05;

public class Seed {
    private long value;
    private long soilValue;
    private long fertilizerValue;
    private long waterValue;
    private long lightValue;
    private long temperatureValue;
    private long humidityValue;
    private long locationValue;

    public Seed(long value) {
        this.value = value;
        initializeSeed();
    }

    private void initializeSeed() {
        this.soilValue = Almanac.getSeedToSoil().getDestinationForSource(value);
        this.fertilizerValue = Almanac.getSoilToFertilizer().getDestinationForSource(soilValue);
        this.waterValue = Almanac.getFertilizerToWater().getDestinationForSource(fertilizerValue);
        this.lightValue = Almanac.getWaterToLight().getDestinationForSource(waterValue);
        this.temperatureValue = Almanac.getLightToTemperature().getDestinationForSource(lightValue);
        this.humidityValue = Almanac.getTemperatureToHumidity().getDestinationForSource(temperatureValue);
        this.locationValue = Almanac.getHumidityToLocation().getDestinationForSource(humidityValue);
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getSoilValue() {
        return soilValue;
    }

    public void setSoilValue(long soilValue) {
        this.soilValue = soilValue;
    }

    public long getFertilizerValue() {
        return fertilizerValue;
    }

    public void setFertilizerValue(long fertilizerValue) {
        this.fertilizerValue = fertilizerValue;
    }

    public long getWaterValue() {
        return waterValue;
    }

    public void setWaterValue(long waterValue) {
        this.waterValue = waterValue;
    }

    public long getLightValue() {
        return lightValue;
    }

    public void setLightValue(long lightValue) {
        this.lightValue = lightValue;
    }

    public long getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(long temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public long getHumidityValue() {
        return humidityValue;
    }

    public void setHumidityValue(long humidityValue) {
        this.humidityValue = humidityValue;
    }

    public long getLocationValue() {
        return locationValue;
    }

    public void setLocationValue(long locationValue) {
        this.locationValue = locationValue;
    }

}
