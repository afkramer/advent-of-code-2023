package adventofcode2023.day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import adventofcode2023.TextParserUtil;

public class RockShifter {
    private List<String> rockMap = TextParserUtil.readData("day14.txt");

    public long sumRocksAfterCycles(int numCylcesToPerform) {
        CycleData data = findPeriodInCycles(numCylcesToPerform);
        int remainingCyclesAfterFirstMatch = numCylcesToPerform - (data.getFirstMatchingCycle() + 1);
        int reducedCycles = remainingCyclesAfterFirstMatch
                % (data.getSecondMatchingCycle() - data.getFirstMatchingCycle());
        System.out.printf("Need to run %d cycles after reduction %n", reducedCycles);
        List<String> rockResult = cycleRocks(data.getFirstMatchingCycle() + 1 + reducedCycles);
        return sumUpRocks(rockResult);
    }

    public CycleData findPeriodInCycles(int numCylcesToPerform) {
        return findPeriodInCycles(this.rockMap, numCylcesToPerform);
    }

    public CycleData findPeriodInCycles(List<String> rocksToShift, int numCylcesToPerform) {
        List<List<String>> previousCycles = new ArrayList<>();
        for (int i = 0; i < numCylcesToPerform; i++) {
            if (i % 100000 == 0 && i != 0) {
                System.out.printf("At iteration %d%n", i);
            }
            // System.out.println("Shifting north");
            rocksToShift = shiftRocksNorth(rocksToShift);
            // TextParserUtil.printLists(rockMap);
            // System.out.println("Shifting west");
            rocksToShift = shiftRocksWest(rocksToShift);
            // TextParserUtil.printLists(rockMap);
            // System.out.println("Shifting south");
            rocksToShift = shiftRocksSouth(rocksToShift);
            // TextParserUtil.printLists(rockMap);
            // System.out.println("Shifting east");
            rocksToShift = shiftRocksEast(rocksToShift);
            // TextParserUtil.printLists(rockMap);
            int foundIndex = foundIndexOfRockMap(previousCycles, rocksToShift);
            if (!previousCycles.isEmpty() && foundIndex != -1) {
                System.out.printf("Found a match at line %d during cycle %d%n", foundIndex, i);
                return new CycleData(foundIndex, i);
                // return rocksToShift;
            }
            // lastCycleMap = TextParserUtil.copyList(rocksToShift);
            previousCycles.add(rocksToShift);
        }
        return new CycleData(0, 0);
    }

    public List<String> cycleRocks(int numCylcesToPerform) {
        return cycleRocks(this.rockMap, numCylcesToPerform);
    }

    public List<String> cycleRocks(List<String> rocksToShift, int numCylcesToPerform) {
        for (int i = 0; i < numCylcesToPerform; i++) {
            if (i % 100000 == 0 && i != 0) {
                System.out.printf("At iteration %d%n", i);
            }
            // System.out.println("Shifting north");
            rocksToShift = shiftRocksNorth(rocksToShift);
            // TextParserUtil.printLists(rockMap);
            // System.out.println("Shifting west");
            rocksToShift = shiftRocksWest(rocksToShift);
            // TextParserUtil.printLists(rockMap);
            // System.out.println("Shifting south");
            rocksToShift = shiftRocksSouth(rocksToShift);
            // TextParserUtil.printLists(rockMap);
            // System.out.println("Shifting east");
            rocksToShift = shiftRocksEast(rocksToShift);
            // TextParserUtil.printLists(rockMap);
        }
        return rocksToShift;
    }

    public List<String> cycleRocksSmart(int numCylcesToPerform) {
        return cycleRocksSmart(this.rockMap, numCylcesToPerform);
    }

    public List<String> cycleRocksSmart(List<String> rocksToShift, int numCylcesToPerform) {
        int periodsThatAreTheSame = 1;
        List<String> configurationAfterOneCycle = cycleRocks(rocksToShift, 1);
        for (int i = 0; i < numCylcesToPerform - 1; i++) {
            rocksToShift = cycleRocks(rocksToShift, 1);
            if (rockMapsAreTheSame(rocksToShift, configurationAfterOneCycle) && i > 0) {
                periodsThatAreTheSame = i;
                break;
            }
        }

        System.out.printf("There is a regularity: %d%n", periodsThatAreTheSame);
        return rocksToShift;

    }

    public boolean rockMapsAreTheSame(List<String> mapOne, List<String> mapTwo) {
        for (int x = 0; x < mapOne.size(); x++) {
            for (int y = 0; y < mapOne.get(0).length(); y++) {
                if (mapOne.get(x).charAt(y) != mapTwo.get(x).charAt(y)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int foundIndexOfRockMap(List<List<String>> previousCycles, List<String> mapToCheck) {
        return IntStream.range(0, previousCycles.size()).boxed()
                .filter(i -> rockMapsAreTheSame(previousCycles.get(i), mapToCheck)).findFirst().orElse(-1);
        // return previousCycles.stream().anyMatch(cycle -> rockMapsAreTheSame(cycle,
        // mapToCheck));
    }

    public List<String> shiftRocksNorth() {
        return shiftRocksNorth(this.rockMap);
    }

    public List<String> shiftRocksNorth(List<String> rocksToShift) {
        List<String> pivotedMap = TextParserUtil.pivotStringList(rocksToShift);
        pivotedMap = bubbleSortRocks(pivotedMap, "west");
        return TextParserUtil.pivotBackStringList(pivotedMap);
    }

    private List<String> shiftRocksWest(List<String> rocksToShift) {
        return bubbleSortRocks(rocksToShift, "west");
    }

    private List<String> shiftRocksSouth(List<String> rocksToShift) {
        List<String> pivotedMap = TextParserUtil.pivotStringList(rocksToShift);
        pivotedMap = bubbleSortRocks(pivotedMap, "east");
        return TextParserUtil.pivotBackStringList(pivotedMap);
    }

    private List<String> shiftRocksEast(List<String> rocksToShift) {
        return bubbleSortRocks(rocksToShift, "east");
    }

    private List<String> bubbleSortRocks(List<String> pivotedMap, String direction) {
        List<String> shiftedPivotedRockMap = new ArrayList<>();
        for (String rockLine : pivotedMap) {
            if (direction.equals("west")) {
                shiftedPivotedRockMap.add(bubbleSortRockLineToWest(rockLine));
            } else {
                shiftedPivotedRockMap.add(bubbleSortRockLineToEast(rockLine));
            }

        }
        return shiftedPivotedRockMap;
    }

    private String bubbleSortRockLineToWest(String rockLine) {
        String[] rockLineArray = rockLine.split("");
        boolean wasSwapped = true;
        while (wasSwapped) {
            wasSwapped = false;
            for (int i = 0; i < rockLine.length() - 1; i++) {
                if (rockLineArray[i].equals(".") && rockLineArray[i + 1].equals("O")) {
                    String temp = rockLineArray[i];
                    rockLineArray[i] = rockLineArray[i + 1];
                    rockLineArray[i + 1] = temp;
                    wasSwapped = true;
                }
            }
        }

        return Arrays.stream(rockLineArray).collect(Collectors.joining());
    }

    private String bubbleSortRockLineToEast(String rockLine) {
        String[] rockLineArray = TextParserUtil.reverseString(rockLine).split("");
        boolean wasSwapped = true;
        while (wasSwapped) {
            wasSwapped = false;
            for (int i = 0; i < rockLine.length() - 1; i++) {
                if (rockLineArray[i].equals(".") && rockLineArray[i + 1].equals("O")) {
                    String temp = rockLineArray[i];
                    rockLineArray[i] = rockLineArray[i + 1];
                    rockLineArray[i + 1] = temp;
                    wasSwapped = true;
                }
            }
        }

        return TextParserUtil.reverseString(Arrays.stream(rockLineArray).collect(Collectors.joining()));
    }

    public long sumUpRocks(List<String> rocksToSum) {
        long sum = 0l;
        int rowsInRockMap = rocksToSum.size();
        for (int i = 0; i < rowsInRockMap; i++) {
            int rockValue = rowsInRockMap - i;
            Map<String, Long> groupings = Arrays.stream(rocksToSum.get(i).split(""))
                    .collect(Collectors.groupingBy(str -> str, Collectors.counting()));
            Long val = groupings.get("O");
            sum += groupings.get("O") != null ? val * rockValue : 0;
        }
        return sum;
    }

}
