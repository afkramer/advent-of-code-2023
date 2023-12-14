package adventofcode2023.day14;

public class CycleData {
    private int firstMatchingCycle;
    private int secondMatchingCycle;

    public CycleData(int firstMatchingCycle, int secondMatchingCycle) {
        this.firstMatchingCycle = firstMatchingCycle;
        this.secondMatchingCycle = secondMatchingCycle;
    }

    public int getFirstMatchingCycle() {
        return firstMatchingCycle;
    }

    public int getSecondMatchingCycle() {
        return secondMatchingCycle;
    }

}
