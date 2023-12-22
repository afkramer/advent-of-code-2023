package adventofcode2023.day17;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int heatLossTotal;
    private int heatLossAtNode;
    private Coordinate coord;
    private List<Node> adjacentNodes;
    private List<Node> visitedByNodes;
    private Direction direction;
    private int numMoves;
    private Node previousNode;

    public Node(int heatLossTotal, int heatLossAtNode, Coordinate coord, List<Node> adjacentNodes,
            List<Node> visitedByNodes, Direction direction,
            int numMoves,
            Node previousNode) {
        this.heatLossTotal = heatLossTotal;
        this.heatLossAtNode = heatLossAtNode;
        this.coord = coord;
        this.adjacentNodes = adjacentNodes;
        this.visitedByNodes = visitedByNodes;
        this.direction = direction;
        this.numMoves = numMoves;
        this.previousNode = previousNode;
    }

    public Node(int heatLossAtNode, Coordinate coord, Direction direction, int numMoves) {
        this(0, heatLossAtNode, coord, new ArrayList<>(), new ArrayList<>(), direction, numMoves, null);
    }

    public Direction directionToOtherNode(Node otherNode) {
        int difX = otherNode.getXVal() - this.coord.xVal();
        int difY = otherNode.getYVal() - this.coord.yVal();

        if (difX == -1 && difY == 0) {
            return Direction.NORTH;
        } else if (difX == 0 && difY == 1) {
            return Direction.EAST;
        } else if (difX == 1 && difY == 0) {
            return Direction.SOUTH;
        } else if (difX == 0 && difY == -1) {
            return Direction.WEST;
        } else {
            return Direction.UNDEFINED;
        }

    }

    public Coordinate getCoordinateOfNodeInDirection(Direction direction, int steps) {
        return switch (direction) {
            case NORTH -> new Coordinate(this.coord.xVal() - steps, this.coord.yVal());
            case EAST -> new Coordinate(this.coord.xVal(), this.coord.yVal() + steps);
            case SOUTH -> new Coordinate(this.coord.xVal() + steps, this.coord.yVal());
            case WEST -> new Coordinate(this.coord.xVal(), this.coord.yVal() - steps);
            default -> null;
        };
    }

    public int getHeatLossTotal() {
        return heatLossTotal;
    }

    public void setHeatLossTotal(int heatLoss) {
        this.heatLossTotal = heatLoss;
    }

    public int getHeatLossAtNode() {
        return this.heatLossAtNode;
    }

    public Coordinate getCoordinate() {
        return this.coord;
    }

    public int getXVal() {
        return this.coord.xVal();
    }

    public int getYVal() {
        return this.coord.yVal();
    }

    public List<Node> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void addNeighborNode(Node node) {
        adjacentNodes.add(node);
    }

    public List<Node> getVisitedByNodes() {
        return this.visitedByNodes;
    }

    public void addVisitedByNode(Node node) {
        this.visitedByNodes.add(node);
    }

    public Node getPreviousNode() {
        return this.previousNode;
    }

    public void setPreviousNode(Node node) {
        this.previousNode = node;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getNumMoves() {
        return this.numMoves;
    }

    @Override
    public String toString() {
        return "Node [coord=" + coord + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + heatLossTotal;
        result = prime * result + heatLossAtNode;
        result = prime * result + ((coord == null) ? 0 : coord.hashCode());
        result = prime * result + ((adjacentNodes == null) ? 0 : adjacentNodes.hashCode());
        result = prime * result + ((visitedByNodes == null) ? 0 : visitedByNodes.hashCode());
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
        result = prime * result + numMoves;
        result = prime * result + ((previousNode == null) ? 0 : previousNode.hashCode());
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
        Node other = (Node) obj;
        if (heatLossTotal != other.heatLossTotal)
            return false;
        if (heatLossAtNode != other.heatLossAtNode)
            return false;
        if (coord == null) {
            if (other.coord != null)
                return false;
        } else if (!coord.equals(other.coord))
            return false;
        if (adjacentNodes == null) {
            if (other.adjacentNodes != null)
                return false;
        } else if (!adjacentNodes.equals(other.adjacentNodes))
            return false;
        if (visitedByNodes == null) {
            if (other.visitedByNodes != null)
                return false;
        } else if (!visitedByNodes.equals(other.visitedByNodes))
            return false;
        if (direction != other.direction)
            return false;
        if (numMoves != other.numMoves)
            return false;
        if (previousNode == null) {
            if (other.previousNode != null)
                return false;
        } else if (!previousNode.equals(other.previousNode))
            return false;
        return true;
    }

}
