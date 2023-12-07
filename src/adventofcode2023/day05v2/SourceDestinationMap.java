package adventofcode2023.day05v2;

import java.util.ArrayList;
import java.util.List;

public class SourceDestinationMap {
    private ElementType sourceElement;
    private ElementType destinationElement;
    List<MapElement> mappedRanges;

    public SourceDestinationMap(ElementType sourceElement, ElementType destinationElement) {
        this.sourceElement = sourceElement;
        this.destinationElement = destinationElement;
        this.mappedRanges = new ArrayList<>();
    }

    public void addMapElement(long sourceStartValue, long destinationStartValue, long range) {
        mappedRanges.add(new MapElement(sourceStartValue, destinationStartValue, range));
    }

    public List<MapElement> convertRange(long sourceStartValue, long sourceEndValue) {
        for (MapElement mappedRange : mappedRanges) {
            if (sourceStartValue >= mappedRange.getSourceStartValue()) {

            }
        }

        return null;
    }
}
