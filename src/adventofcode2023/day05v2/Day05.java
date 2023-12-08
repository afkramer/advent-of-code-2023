package adventofcode2023.day05v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventofcode2023.Day;
import adventofcode2023.TextParserUtil;
import adventofcode2023.day05.MappingValue;

// TODO -> Could use ElementRange class instead of Map<Long, Long>
public class Day05 implements Day {
    // private String rawSeedString = "79 14 55 13";
    private String rawSeedString = """
            28965817 302170009 1752849261 48290258 804904201 243492043 2150339939
            385349830 1267802202 350474859 2566296746 17565716 3543571814 291402104
            447111316 279196488 3227221259 47952959 1828835733 9607836
            """;
    List<String> inputLines = TextParserUtil.readData("day05.txt");

    @Override
    public void partOne() {
        System.out.println("");
    }

    @Override
    public void partTwo() {
        String[] seedValues = rawSeedString.split("\\s");
        Map<Long, Long> rangesToConvert = generateSeedRangesToConvert(seedValues);

        List<List<MappingValue>> conversionTables = generateConversionTables();
        for (List<MappingValue> conversionTable : conversionTables) {
            rangesToConvert = translateRanges(rangesToConvert, conversionTable);
        }

        long smallestLocation = rangesToConvert.keySet().stream().min(Long::compare).get();
        System.out.println(smallestLocation);
    }

    public Map<Long, Long> generateSeedRangesToConvert(String[] rawSeedValues) {
        Map<Long, Long> seedRangesToConvert = new HashMap<>();
        for (int i = 0; i < rawSeedValues.length; i += 2) {
            long startValue = TextParserUtil.parseLong(rawSeedValues[i]);
            long range = TextParserUtil.parseLong(rawSeedValues[i + 1]);
            long endValue = startValue + range - 1;
            seedRangesToConvert.put(startValue, endValue);
        }
        return seedRangesToConvert;
    }

    public List<List<MappingValue>> generateConversionTables() {
        List<List<MappingValue>> conversionTables = new ArrayList<>();
        List<MappingValue> conversionTable = new ArrayList<>();
        // Start at 4th line of input text
        for (int line = 3; line < inputLines.size(); line++) {
            if (inputLines.get(line).length() == 0 || !Character.isDigit(inputLines.get(line).charAt(0))) {
                if (!conversionTable.isEmpty()) {
                    conversionTables.add(conversionTable);
                }
                conversionTable = new ArrayList<>();

            } else {
                String[] conversionValues = inputLines.get(line).split("\\s");
                long sourceStartValue = TextParserUtil.parseLong(conversionValues[1]);
                long destinationStartValue = TextParserUtil.parseLong(conversionValues[0]);
                long range = TextParserUtil.parseLong(conversionValues[2]);
                conversionTable.add(new MappingValue(sourceStartValue, destinationStartValue, range));
            }

        }

        conversionTables.add(conversionTable);

        return conversionTables;
    }

    public Map<Long, Long> translateRanges(Map<Long, Long> rangesToConvert,
            List<MappingValue> conversionTable) {

        Map<Long, Long> newRanges = new HashMap<>();
        for (Map.Entry<Long, Long> range : rangesToConvert.entrySet()) {
            long sourceStartFinal = range.getKey();
            long sourceEndFinal = range.getValue();
            System.out.println(
                    String.format("Converting source start: %d, source end: %d", sourceStartFinal, sourceEndFinal));

            List<MappingValue> relevantConversionRanges = new ArrayList<>();
            for (MappingValue map : conversionTable) {
                if (map.isRelevantForSourceValues(sourceStartFinal, sourceEndFinal)) {
                    relevantConversionRanges.add(map);
                }
            }

            // hacky redefinition of variables that needed to be final for the stream
            Long sourceStart = sourceStartFinal;
            Long sourceEnd = sourceEndFinal;

            if (relevantConversionRanges.isEmpty()) {
                // There is no conversion, so the source start and end stay as is
                newRanges.put(sourceStart, sourceEnd);
            } else {
                for (MappingValue map : relevantConversionRanges) {
                    long offsetToDestination = map.getDestinationValue() - map.getSourceValue();
                    long conversionRange = map.getRange();
                    long conversionSourceStart = map.getSourceValue();
                    long conversionSourceEnd = conversionSourceStart + conversionRange - 1;
                    if (map.areStartAndEndWithinRange(sourceStart, sourceEnd)) {
                        long destinationStart = sourceStart + offsetToDestination;
                        long destinationEnd = sourceEnd + offsetToDestination;
                        newRanges.put(destinationStart, destinationEnd);
                    } else if (map.isStartOutsideAndEndWithinRange(sourceStart, sourceEnd)) {
                        long destinationStart = sourceStart;
                        long destinationEnd = conversionSourceStart - 1;
                        newRanges.put(destinationStart, destinationEnd);
                        destinationStart = conversionSourceStart + offsetToDestination;
                        destinationEnd = sourceEnd + offsetToDestination;
                        newRanges.put(destinationStart, destinationEnd);
                    } else if (map.isStartWithinAndEndOutsideRange(sourceStart, sourceEnd)) {
                        long destinationStart = sourceStart + offsetToDestination;
                        long destinationEnd = conversionSourceEnd + offsetToDestination;
                        newRanges.put(destinationStart, destinationEnd);
                        sourceStart = conversionSourceEnd + 1;
                    } else {
                        long destinationStart = sourceStart;
                        long destinationEnd = conversionSourceStart - 1;
                        newRanges.put(destinationStart, destinationEnd);
                        destinationStart = conversionSourceStart + offsetToDestination;
                        destinationEnd = conversionSourceEnd + offsetToDestination;
                        newRanges.put(destinationStart, destinationEnd);
                        sourceStart = conversionSourceEnd + 1;
                    }
                }

            }

        }

        return newRanges;
    }

}
