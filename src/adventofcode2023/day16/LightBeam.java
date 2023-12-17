package adventofcode2023.day16;

import java.util.ArrayList;
import java.util.List;

public class LightBeam {
    private int id;
    private Coordinate currCoord;
    private List<Coordinate> previousCoords;
    private boolean isActive;
    private Direction currDirection;

    public LightBeam(int id, Coordinate currCoord, boolean isActive, Direction currDirection) {
        this.id = id;
        this.currCoord = currCoord;
        this.isActive = isActive;
        this.currDirection = currDirection;
        this.previousCoords = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coordinate getCurrCoord() {
        return currCoord;
    }

    public void setCurrCoord(Coordinate coord) {
        this.currCoord = coord;
    }

    public List<Coordinate> getPreviousCoords() {
        return previousCoords;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive() {
        this.isActive = true;
    }

    public void setIsInactive() {
        this.isActive = false;
    }

    public Direction getCurrDirection() {
        return this.currDirection;
    }

    public void setCurrDirection(Direction direction) {
        this.currDirection = direction;
    }

    public void addPreviousCoord(Coordinate prevCoord) {
        this.previousCoords.add(prevCoord);
    }

    public boolean hasNotVisited(Tile tile) {
        return !this.previousCoords.contains(tile.getCoordinate());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((currCoord == null) ? 0 : currCoord.hashCode());
        result = prime * result + ((previousCoords == null) ? 0 : previousCoords.hashCode());
        result = prime * result + (isActive ? 1231 : 1237);
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
        LightBeam other = (LightBeam) obj;
        if (currCoord == null) {
            if (other.currCoord != null)
                return false;
        } else if (!currCoord.equals(other.currCoord))
            return false;
        if (previousCoords == null) {
            if (other.previousCoords != null)
                return false;
        } else if (!previousCoords.equals(other.previousCoords))
            return false;
        if (isActive != other.isActive)
            return false;
        return true;
    }

}
