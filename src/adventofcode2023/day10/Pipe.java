package adventofcode2023.day10;

public class Pipe {
    Coordinate coord;
    String code;
    PipeType type;
    boolean visited;

    public Pipe(int xVal, int yVal, String code) {
        this.coord = new Coordinate(xVal, yVal);
        this.code = code;
        this.type = PipeType.parsePipeFromCode(code);
    }

    public Coordinate getCoord() {
        return this.coord;
    }

    public String getCode() {
        return this.code;
    }

    public PipeType getType() {
        return this.type;
    }

    public boolean wasVisited() {
        return this.visited;
    }

    public void visit() {
        this.visited = true;
    }

    public Pipe findPipeToNorth() {
        int newXVal = this.coord.getxVal() - 1;
        int newYVal = this.coord.getyVal();
        return PipeMatrix.getPipeAtCoord(new Coordinate(newXVal, newYVal));

    }

    public Pipe findPipeToEast() {
        int newXVal = this.coord.getxVal();
        int newYVal = this.coord.getyVal() + 1;
        return PipeMatrix.getPipeAtCoord(new Coordinate(newXVal, newYVal));
    }

    public Pipe findPipeToSouth() {
        int newXVal = this.coord.getxVal() + 1;
        int newYVal = this.coord.getyVal();
        return PipeMatrix.getPipeAtCoord(new Coordinate(newXVal, newYVal));
    }

    public Pipe findPipeToWest() {
        int newXVal = this.coord.getxVal();
        int newYVal = this.coord.getyVal() - 1;
        return PipeMatrix.getPipeAtCoord(new Coordinate(newXVal, newYVal));
    }

}
