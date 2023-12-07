package adventofcode2023.day05;

public class MappingValue implements Comparable<MappingValue> {
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

    public boolean isRelevantForSourceValues(long sourceStart, long sourceEnd) {
        long conversionSourceEnd = this.sourceValue + this.range - 1;
        // TODO refactor -> is this really necessary? There are four cases where the
        // conversion is relevant for a range to convert
        // TODO -> you'll need to include conditions that the end outside the source is
        // also greater than the other end
        // SO not that both start and end values are outside the range
        boolean startAndEndWithinRange = sourceStart >= this.sourceValue && sourceEnd <= conversionSourceEnd;
        boolean startWithinEndOutsideRange = sourceStart >= this.sourceValue && sourceStart <= conversionSourceEnd
                && sourceEnd > conversionSourceEnd;
        boolean startAndEndOutsideRange = sourceStart < this.sourceValue && sourceEnd > conversionSourceEnd;
        boolean startOutsideEndWithinRange = sourceStart < this.sourceValue && sourceEnd <= conversionSourceEnd
                && sourceEnd >= this.sourceValue;

        return startAndEndWithinRange || startWithinEndOutsideRange || startAndEndOutsideRange
                || startOutsideEndWithinRange;
    }

    public boolean areStartAndEndWithinRange(long sourceStart, long sourceEnd) {
        long conversionSourceEnd = this.sourceValue + this.range - 1;
        return sourceStart >= this.sourceValue && sourceEnd <= conversionSourceEnd;
    }

    public boolean isStartWithinAndEndOutsideRange(long sourceStart, long sourceEnd) {
        long conversionSourceEnd = this.sourceValue + this.range - 1;
        return sourceStart >= this.sourceValue && sourceStart <= conversionSourceEnd && sourceEnd > conversionSourceEnd;
    }

    public boolean areStartAndEndOutsideRange(long sourceStart, long sourceEnd) {
        long conversionSourceEnd = this.sourceValue + this.range - 1;
        return sourceStart < this.sourceValue && sourceEnd > conversionSourceEnd;
    }

    public boolean isStartOutsideAndEndWithinRange(long sourceStart, long sourceEnd) {
        long conversionSourceEnd = this.sourceValue + this.range - 1;
        return sourceStart < this.sourceValue && sourceEnd <= conversionSourceEnd && sourceEnd >= this.sourceValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (sourceValue ^ (sourceValue >>> 32));
        result = prime * result + (int) (destinationValue ^ (destinationValue >>> 32));
        result = prime * result + (int) (range ^ (range >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MappingValue other = (MappingValue) obj;
        if (sourceValue != other.sourceValue)
            return false;
        if (destinationValue != other.destinationValue)
            return false;
        if (range != other.range)
            return false;
        return true;
    }

    @Override
    public int compareTo(MappingValue other) {
        if (this.sourceValue < other.getSourceValue()) {
            return -1;
        } else if (this.sourceValue > other.getSourceValue()) {
            return 1;
        } else {
            return 0;
        }
    }

}
