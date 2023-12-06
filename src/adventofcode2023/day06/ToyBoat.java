package adventofcode2023.day06;

import java.util.stream.LongStream;

public class ToyBoat {
    private long raceTime;
    private long distanceToBeat;

    public ToyBoat(long raceTime, long distanceToBeat) {
        this.raceTime = raceTime;
        this.distanceToBeat = distanceToBeat;
    }

    public long determineWaysToWin() {
        return LongStream.rangeClosed(0L, this.raceTime).boxed()
                .filter(timePressed -> ((this.raceTime - timePressed) * timePressed) > this.distanceToBeat).count();
    }

}
