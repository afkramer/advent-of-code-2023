package adventofcode2023.day05v2;

public class MapElement {
    private long sourceStartValue;
    private long destinationStartValue;
    private long sourceEndValue;
    private long destinationEndValue;
    private long range;

    public MapElement(long sourceStartValue, long destinationStartValue, long sourceEndValue,
            long destinationEndValue) {
        this.sourceStartValue = sourceStartValue;
        this.destinationStartValue = destinationStartValue;
        this.sourceEndValue = sourceEndValue;
        this.destinationEndValue = destinationEndValue;
        this.range = sourceEndValue - sourceStartValue + 1;
    }

    public MapElement(long sourceStartValue, long destinationStartValue, long range) {
        this(sourceStartValue, destinationStartValue, sourceStartValue + range - 1, destinationStartValue + range - 1);
        this.range = range;
    }

    public long getSourceStartValue() {
        return sourceStartValue;
    }

    public long getDestinationStartValue() {
        return destinationStartValue;
    }

    public long getSourceEndValue() {
        return sourceEndValue;
    }

    public long getDestinationEndValue() {
        return destinationEndValue;
    }

    public long getRange() {
        return range;
    }

}
