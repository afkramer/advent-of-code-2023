package adventofcode2023.day11;

public class Galaxy {
    private long xCoord;
    private long yCoord;
    private long xValuesToAdd;
    private long yValuesToAdd;

    public Galaxy(long xCoord, long yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public long determineDistanceToOtherGalaxy(Galaxy galaxy) {
        return Math.abs(this.xCoord - galaxy.getxCoord()) + Math.abs(this.yCoord - galaxy.getyCoord());
    }

    public long getxCoord() {
        return xCoord;
    }

    public long getyCoord() {
        return yCoord;
    }

    public void increaseXValuesToAdd(long amount) {
        this.xValuesToAdd += amount - 1;
    }

    public void increaseYValuesToAdd(long amount) {
        this.yValuesToAdd += amount - 1;
    }

    public void updateCoordinateAfterExpansion() {
        this.xCoord += this.xValuesToAdd;
        this.yCoord += this.yValuesToAdd;
    }

}
