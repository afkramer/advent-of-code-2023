package adventofcode2023.day05v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventofcode2023.Day;
import adventofcode2023.TextParserUtil;
import adventofcode2023.day05.MappingValue;

public class Day05 implements Day {
    private String rawSeedStringTest = "79 14 55 13";
    private String rawSeedString = "28965817 302170009 1752849261 48290258 804904201 243492043 2150339939 385349830 1267802202 350474859 2566296746 17565716 3543571814 291402104 447111316 279196488 3227221259 47952959 1828835733 9607836";
    List<String> inputLines = TextParserUtil.readData("testday05.txt");

    @Override
    public void partOne() {
        System.out.println("");
    }

    @Override
    public void partTwo() {
        String[] seedValues = rawSeedStringTest.split("\\s");
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

        // TODO -> the loops are all off here -> may need some kind of new data
        // structure?
        Map<Long, Long> newRanges = new HashMap<>();
        for (Map.Entry<Long, Long> range : rangesToConvert.entrySet()) {
            Long sourceStartFinal = range.getKey();
            Long sourceEndFinal = range.getValue();

            // List<MappingValue> relevantConversionRanges =
            // conversionTable.stream().filter(
            // i -> i.isRelevantForSourceValues(sourceStartFinal, sourceEndFinal))
            // .sorted().toList();
            List<MappingValue> relevantConversionRanges = new ArrayList<>();
            for (MappingValue map : conversionTable) {
                if (map.isRelevantForSourceValues(sourceStartFinal, sourceEndFinal)) {
                    relevantConversionRanges.add(map);
                }
            }

            // TODO -> create comparator to be able to sort the list (Collections.sort()
            // doesn't want to)
            // Only relevant if things don't work with the stream

            // Collections.sort(relevantConversionRanges);

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
                    long conversionSourceEnd = conversionSourceStart + conversionRange;
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
                        long destinationStart = sourceStart;
                        long destinationEnd = conversionSourceEnd;
                        newRanges.put(destinationStart, destinationEnd);
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
