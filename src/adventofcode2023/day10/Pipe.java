package adventofcode2023.day10;

public class Pipe {
    private Coordinate coord;
    private String code;
    private PipeType type;
    private boolean visited;
    private boolean counted;
    private FillType fill;
    // Add variables to track the pipes that connected to it during the path
    // Necessary for calculating which blocks are actually inside the path
    private boolean connectedToPipeNorth;
    private boolean connectedToPipeEast;
    private boolean connectedToPipeSouth;
    private boolean connectedToPipeWest;

    public Pipe(int xVal, int yVal, String code) {
        this.coord = new Coordinate(xVal, yVal);
        this.code = code;
        this.type = PipeType.parsePipeFromCode(code);
        this.fill = FillType.NONE;
    }

    public Pipe(int xVal, int yVal) {
        this(xVal, yVal, "none");
    }

    // Constructor to make a copy of a pipe for initializing the large array
    public Pipe(Pipe pipe) {
        this(pipe.getCoord().getxVal(), pipe.getCoord().getyVal(), pipe.getCode());
        this.visited = pipe.wasVisited();
        this.counted = pipe.wasCounted();
        this.connectedToPipeNorth = pipe.isConnectedToPipeNorth();
        this.connectedToPipeEast = pipe.isConnectedToPipeEast();
        this.connectedToPipeSouth = pipe.isConnectedToPipeSouth();
        this.connectedToPipeWest = pipe.isConnectedToPipeWest();
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

    public boolean wasCounted() {
        return this.counted;
    }

    public void setPipeTypeBasedOnSurroundings() {
        Pipe pipeToNorth = findPipeToNorthBig();
        Pipe pipeToWest = findPipeToWestBig();
        boolean connectionAbove = pipeToNorth != null && pipeToNorth.isConnectedToPipeSouth();
        boolean connectionNextTo = pipeToWest != null && pipeToWest.isConnectedToPipeEast();
        if (connectionAbove) {
            // this.connectedToPipeNorth = true;
            // this.connectedToPipeSouth = true;
            this.type = PipeType.DUMMY_CONNECTION;
            this.code = PipeType.DUMMY_CONNECTION.getCode();
            this.visit();
        } else if (connectionNextTo) {
            // this.connectedToPipeEast = true;
            // this.connectedToPipeWest = true;
            this.type = PipeType.DUMMY_CONNECTION;
            this.code = PipeType.DUMMY_CONNECTION.getCode();
            this.visit();
        } else {
            this.type = PipeType.DUMMY_FILLER;
            this.code = PipeType.DUMMY_FILLER.getCode();
        }

    }

    public void count() {
        this.counted = true;
    }

    public Pipe findPipeToNorthBig() {
        int newXVal = this.coord.getxVal() - 1;
        int newYVal = this.coord.getyVal();
        return PipeMatrix.getPipeAtCoordBig(new Coordinate(newXVal, newYVal));
    }

    public Pipe findPipeToEastBig() {
        int newXVal = this.coord.getxVal();
        int newYVal = this.coord.getyVal() + 1;
        return PipeMatrix.getPipeAtCoordBig(new Coordinate(newXVal, newYVal));
    }

    public Pipe findPipeToSouthBig() {
        int newXVal = this.coord.getxVal() + 1;
        int newYVal = this.coord.getyVal();
        return PipeMatrix.getPipeAtCoordBig(new Coordinate(newXVal, newYVal));
    }

    public Pipe findPipeToWestBig() {
        int newXVal = this.coord.getxVal();
        int newYVal = this.coord.getyVal() - 1;
        return PipeMatrix.getPipeAtCoordBig(new Coordinate(newXVal, newYVal));
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

    public void setConnectedToPipeNorth(boolean connectedToPipeNorth) {
        this.connectedToPipeNorth = connectedToPipeNorth;
    }

    public void setConnectedToPipeEast(boolean connectedToPipeEast) {
        this.connectedToPipeEast = connectedToPipeEast;
    }

    public void setConnectedToPipeSouth(boolean connectedToPipeSouth) {
        this.connectedToPipeSouth = connectedToPipeSouth;
    }

    public void setConnectedToPipeWest(boolean connectedToPipeWest) {
        this.connectedToPipeWest = connectedToPipeWest;
    }

    public boolean isConnectedToPipeNorth() {
        return connectedToPipeNorth;
    }

    public boolean isConnectedToPipeEast() {
        return connectedToPipeEast;
    }

    public boolean isConnectedToPipeSouth() {
        return connectedToPipeSouth;
    }

    public boolean isConnectedToPipeWest() {
        return connectedToPipeWest;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public FillType getFill() {
        return this.fill;
    }

    public void setFill(FillType fill) {
        this.fill = fill;
    }

    @Override
    public String toString() {
        return "Pipe [coord=" + coord + ", code=" + code + ", type=" + type + "]";
    }

}
