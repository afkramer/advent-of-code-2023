package adventofcode2023.day06;

import java.util.HashMap;
import java.util.Map;

public class ToyBoat {
    private long raceTime;
    private long distanceToBeat;

    public ToyBoat(long raceTime, long distanceToBeat) {
        this.raceTime = raceTime;
        this.distanceToBeat = distanceToBeat;
    }

    public Map<Long, Long> determineWaysToWin() {
        Map<Long, Long> timePressedAndDistances = new HashMap<>();
        for (long timePressed = 0L; timePressed <= raceTime; timePressed++) {
            long speedInMillimetersPerMillisecond = timePressed;
            long distanceTraveled = (raceTime - timePressed) * speedInMillimetersPerMillisecond;
            if (distanceTraveled > this.distanceToBeat) {
                timePressedAndDistances.put(timePressed, distanceTraveled);
            }
        }
        return timePressedAndDistances;
    }

    public int determineWaysToWinLongRace() {
        int numberOfWins = 0;
        for (long timePressed = 0L; timePressed <= raceTime; timePressed++) {
            long speedInMillimetersPerMillisecond = timePressed;
            long distanceTraveled = (raceTime - timePressed) * speedInMillimetersPerMillisecond;
            if (distanceTraveled > this.distanceToBeat) {
                numberOfWins++;
            }
        }
        return numberOfWins;
    }

}
