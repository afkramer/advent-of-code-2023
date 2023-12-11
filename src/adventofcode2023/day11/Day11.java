package adventofcode2023.day11;

import adventofcode2023.Day;

public class Day11 implements Day {

    @Override
    public void partOne() {
        GalaxyMap map = new GalaxyMap();
        map.initializeSmall();
        long sum = sumDistances(map);

        System.out.println(sum);
    }

    @Override
    public void partTwo() {
        GalaxyMap map = new GalaxyMap();
        map.initializeBig(1000000);
        long sum = sumDistances(map);

        System.out.println(sum);
    }

    private long sumDistances(GalaxyMap galaxyMap) {
        long sum = 0;

        for (int start = 0; start < galaxyMap.getGalaxies().size(); start++) {
            for (int end = start + 1; end < galaxyMap.getGalaxies().size(); end++) {
                Galaxy startGalaxy = galaxyMap.getGalaxies().get(start);
                Galaxy endGalaxy = galaxyMap.getGalaxies().get(end);
                sum += startGalaxy.determineDistanceToOtherGalaxy(endGalaxy);
            }
        }

        return sum;
    }

}
