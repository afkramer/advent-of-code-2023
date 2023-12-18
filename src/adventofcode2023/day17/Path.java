package adventofcode2023.day17;

public class Path {
    private static final int MAX_BLOCKS_ONE_DIRECTION = 2;
    private int xVal;
    private int yVal;
    private Direction direction;
    private int rightCount;
    private int leftCount;
    private int heatLoss;
    private boolean isActive;

    public Path(int xVal, int yVal, Direction direction, int rightCount, int leftCount, int heatLoss) {
        this.xVal = xVal;
        this.yVal = yVal;
        this.direction = direction;
        this.rightCount = rightCount;
        this.leftCount = leftCount;
        this.heatLoss = heatLoss;
        this.isActive = true;
    }

    public Path(int xVal, int yVal, Direction direction) {
        this(xVal, yVal, direction, 0, 0, 0);
    }

    public Path(Path pathToCopy) {
        this(pathToCopy.getXVal(), pathToCopy.getYVal(), pathToCopy.getDirection(), pathToCopy.getRightCount(),
                pathToCopy.getLeftCount(),
                pathToCopy.getHeatLoss());
    }

    public int getXVal() {
        return this.xVal;
    }

    public int getYVal() {
        return this.yVal;
    }

    public Direction getDirection() {
        return this.direction;
    }

    // TODO: expand with "canContinue" -> then allow right or left turns

    public boolean canGoRight() {
        boolean canTurn = switch (this.direction) {
            case NORTH -> this.yVal + 1 < PathAnalyzer.Y_SIZE;
            case EAST -> this.xVal + 1 < PathAnalyzer.X_SIZE;
            case SOUTH -> this.yVal - 1 >= PathAnalyzer.MIN_Y;
            case WEST -> this.xVal - 1 < PathAnalyzer.MIN_X;
            default -> true;
        };

        return canTurn && rightCount < MAX_BLOCKS_ONE_DIRECTION;
    }

    public boolean canGoLeft() {
        boolean canTurn = switch (this.direction) {
            case NORTH -> this.yVal - 1 >= PathAnalyzer.MIN_Y;
            case EAST -> this.xVal - 1 >= PathAnalyzer.MIN_X;
            case SOUTH -> this.yVal + 1 < PathAnalyzer.Y_SIZE;
            case WEST -> this.xVal + 1 < PathAnalyzer.X_SIZE;
            default -> true;
        };

        return canTurn && leftCount < MAX_BLOCKS_ONE_DIRECTION;
    }

    public void goRight() {
        switch (this.direction) {
            case NORTH -> {
                this.yVal++;
                this.direction = Direction.EAST;
            }
            case EAST -> {
                this.xVal++;
                this.direction = Direction.SOUTH;
            }
            case SOUTH -> {
                this.yVal--;
                this.direction = Direction.WEST;
            }
            case WEST -> {
                this.xVal--;
                this.direction = Direction.NORTH;
            }
            default -> {
                this.yVal++;
                this.direction = Direction.EAST;
            }
        }

        this.rightCount++;
        this.leftCount = 0;
    }

    public void goLeft() {
        switch (this.direction) {
            case NORTH -> {
                this.yVal--;
                this.direction = Direction.WEST;
            }
            case EAST -> {
                this.xVal--;
                this.direction = Direction.NORTH;
            }
            case SOUTH -> {
                this.yVal++;
                this.direction = Direction.EAST;
            }
            case WEST -> {
                this.xVal++;
                this.direction = Direction.SOUTH;
            }
            default -> {
                this.yVal++;
                this.direction = Direction.SOUTH;
            }
        }

        this.leftCount++;
        this.rightCount = 0;
    }

    public int getRightCount() {
        return rightCount;
    }

    public int getLeftCount() {
        return leftCount;
    }

    public int getHeatLoss() {
        return heatLoss;
    }

    public void setHeatLoss(int heatLoss) {
        this.heatLoss = heatLoss;
    }

    public void increaseHeatLossBy(int heatLoss) {
        this.heatLoss += heatLoss;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setInactive() {
        this.isActive = false;
    }

    public boolean isAtTargetBlock() {
        return this.xVal == PathAnalyzer.X_SIZE - 1 && this.yVal == PathAnalyzer.Y_SIZE - 1;
    }

}
