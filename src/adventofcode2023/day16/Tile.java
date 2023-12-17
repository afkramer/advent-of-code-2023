package adventofcode2023.day16;

public class Tile {
    private TileType tileType;
    private Coordinate coordinate;
    private boolean wasVisitedFromLeft;
    private boolean wasVisitedFromRight;
    private boolean wasVisitedFromAbove;
    private boolean wasVisitedFromBelow;
    private boolean wasVisited;

    public Tile(TileType tileType, Coordinate coordinate) {
        this.tileType = tileType;
        this.coordinate = coordinate;
    }

    // public Tile(Tile tile) {
    // this.tileType = tile.getTileType();
    // this.coordinate = tile.getCoordinate();
    // }

    public TileType getTileType() {
        return tileType;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean wasVisitedFromLeft() {
        return wasVisitedFromLeft;
    }

    public void visitFromLeft() {
        this.wasVisitedFromLeft = true;
    }

    public boolean wasVisitedFromRight() {
        return wasVisitedFromRight;
    }

    public void visitFromRight() {
        this.wasVisitedFromRight = true;
    }

    public boolean wasVisitedFromAbove() {
        return wasVisitedFromAbove;
    }

    public void visitFromAbove() {
        this.wasVisitedFromAbove = true;
    }

    public boolean wasVisitedFromBelow() {
        return wasVisitedFromBelow;
    }

    public void visitFromBelow() {
        this.wasVisitedFromBelow = true;
    }

    public boolean wasVisited() {
        return this.wasVisited;
    }

    public void visit() {
        this.wasVisited = true;
    }

}
