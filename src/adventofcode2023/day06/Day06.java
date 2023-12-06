package adventofcode2023.day06;

import java.util.ArrayList;
import java.util.List;

import adventofcode2023.Day;
import adventofcode2023.TextParserUtil;

public class Day06 implements Day {
    private List<Integer> raceTimes = new ArrayList<>();
    private List<Integer> distancesToBeat = new ArrayList<>();

    @Override
    public void init() {
        List<String> inputStrings = TextParserUtil.readData("day06.txt");
        String[] stringRaceTimes = inputStrings.get(0).split("\\:")[1].split("\\s");
        for (String raceTime : stringRaceTimes) {
            Integer raceTimeParsed = TextParserUtil.parseInteger(raceTime);
            if (raceTimeParsed != null) {
                raceTimes.add(TextParserUtil.parseInteger(raceTime));
            }
        }

        String[] stringDistancesToBeat = inputStrings.get(1).split("\\:")[1].split("\\s");
        for (String distanceToBeat : stringDistancesToBeat) {
            Integer distanceToBeatParsed = TextParserUtil.parseInteger(distanceToBeat);
            if (distanceToBeatParsed != null) {
                distancesToBeat.add(distanceToBeatParsed);
            }

        }
    }

    @Override
    public void partOne() {
        long sumOfWins = 0;
        for (int i = 0; i < raceTimes.size(); i++) {
            ToyBoat boat = new ToyBoat(raceTimes.get(i), distancesToBeat.get(i));
            long wins = boat.determineWaysToWin();
            if (sumOfWins == 0) {
                sumOfWins = wins;
            } else {
                sumOfWins *= wins;
            }
        }
        System.out.println(sumOfWins);
    }

    @Override
    public void partTwo() {
        // First combine the race times and distances to one number
        Long raceTime = TextParserUtil
                .parseLong(raceTimes.stream().map(i -> Integer.toString(i)).reduce("", (a, b) -> a + b));
        Long distance = TextParserUtil
                .parseLong(distancesToBeat.stream().map(i -> Integer.toString(i)).reduce("", (a, b) -> a + b));
        ToyBoat boat = new ToyBoat(raceTime, distance);
        System.out.println(boat.determineWaysToWin());
    }

}
