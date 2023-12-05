package adventofcode2023.day05;

import java.util.ArrayList;
import java.util.List;

public class ElementMapping {
    private ElementType source;
    private ElementType destination;
    private List<MappingValue> mappingValues;

    public ElementMapping(ElementType source, ElementType destination) {
        this.source = source;
        this.destination = destination;
    }

    public void addMappingValue(Long source, Long destination, Long range) {
        if (mappingValues == null) {
            mappingValues = new ArrayList<>();
        }

        MappingValue mappingValue = new MappingValue(source, destination, range);
        mappingValues.add(mappingValue);
    }

    public ElementType getSource() {
        return source;
    }

    public ElementType getDestination() {
        return destination;
    }

    public Long getDestinationForSource(Long sourceValue) {
        for (MappingValue mappingValue : mappingValues) {
            if (sourceValue >= mappingValue.getSourceValue()
                    && sourceValue < (mappingValue.getSourceValue() + mappingValue.getRange())) {
                Long difference = sourceValue - mappingValue.getSourceValue();
                return mappingValue.getDestinationValue() + difference;
            }
        }

        return sourceValue;
    }

}
