package adventofcode2023.day17;

public class Coordinate {
    private int xVal;
    private int yVal;

    public Coordinate(int xVal, int yVal) {
        this.xVal = xVal;
        this.yVal = yVal;
    }

    public int xVal() {
        return this.xVal;
    }

    public int yVal() {
        return this.yVal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + xVal;
        result = prime * result + yVal;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinate other = (Coordinate) obj;
        if (xVal != other.xVal)
            return false;
        if (yVal != other.yVal)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Coordinate [xVal=" + xVal + ", yVal=" + yVal + "]";
    }

}
