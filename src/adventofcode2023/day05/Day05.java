package adventofcode2023.day05;

import java.util.ArrayList;
import java.util.List;

import adventofcode2023.Day;
import adventofcode2023.TextParserUtil;

public class Day05 implements Day {
    private List<Seed> seeds = new ArrayList<>();
    private Almanac almanac = new Almanac();
    private String rawSeedString = "28965817 302170009 1752849261 48290258 804904201 243492043 2150339939 385349830 1267802202 350474859 2566296746 17565716 3543571814 291402104 447111316 279196488 3227221259 47952959 1828835733 9607836";

    @Override
    public void init() {

    }

    @Override
    public void partOne() {
        for (String rawSeedValue : rawSeedString.split("\\s")) {
            Seed seed = new Seed(TextParserUtil.parseLong(rawSeedValue));
            seeds.add(seed);
        }

        Long lowestValue = seeds.stream().map(s -> s.getLocationValue()).min(Long::compare).get();
        System.out.println(lowestValue);
    }

    @Override
    public void partTwo() {
        Long minLocationValue = -1L;
        String[] seedValues = rawSeedString.split("\\s");
        for (int i = 0; i < seedValues.length; i += 2) {
            Long initialSeedValue = TextParserUtil.parseLong(seedValues[i]);
            Long rangeOfSeeds = TextParserUtil.parseLong(seedValues[i + 1]);
            for (long seedNumber = initialSeedValue; seedNumber < initialSeedValue + rangeOfSeeds; seedNumber++) {
                Seed seed = new Seed(seedNumber);
                if (minLocationValue == -1L) {
                    minLocationValue = seed.getLocationValue();
                } else if (seed.getLocationValue() < minLocationValue) {
                    minLocationValue = seed.getLocationValue();
                }
            }
        }

        System.out.println(minLocationValue);
    }

}
