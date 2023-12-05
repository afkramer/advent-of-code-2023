package adventofcode2023.day05;

public class MappingValue {
    private long sourceValue;
    private long destinationValue;
    private long range;

    public MappingValue(long sourceValue, long destinationValue, long range) {
        this.sourceValue = sourceValue;
        this.destinationValue = destinationValue;
        this.range = range;
    }

    public long getSourceValue() {
        return sourceValue;
    }

    public long getDestinationValue() {
        return destinationValue;
    }

    public long getRange() {
        return range;
    }

}
