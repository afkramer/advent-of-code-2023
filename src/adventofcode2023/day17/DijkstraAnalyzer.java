package adventofcode2023.day17;

import java.util.ArrayList;
import java.util.List;

import adventofcode2023.TextParserUtil;

public class DijkstraAnalyzer {
    private List<String> rawInput = TextParserUtil.readData("day17.txt");
    private List<Node> graph;
    private int minXValue = 0;
    private int xSize = rawInput.size();
    private int minYValue = 0;
    private int ySize = rawInput.get(0).length();
    private static final int MAX_NODES_ONE_DIRECTION = 3;
    private static final int MIN_CLUMSY_MOVES = 4;
    private static final int MAX_CLUMSY_MOVES = 10;
    // private List<Match> previousMatches = new ArrayList<>();

    public void initializeForMax3() {
        initializeGraphMaxMoves3();
        setUpNeighborsMax3();
    }

    private void initializeGraphMaxMoves3() {
        this.graph = new ArrayList<>();
        // Set up beginning node outside the loop, as it has no previous steps or
        // direction
        graph.add(new Node(0, new Coordinate(0, 0), Direction.UNDEFINED, 0));
        for (int x = minXValue; x < xSize; x++) {
            for (int y = minYValue; y < ySize; y++) {
                generateNodesForPossibleMovesMax3(x, y);
            }
        }
    }

    private void generateNodesForPossibleMovesMax3(int xVal, int yVal) {
        for (Direction direction : Direction.getDirections()) {
            for (int moves = 1; moves <= MAX_NODES_ONE_DIRECTION; moves++) {
                if (isValidNode(xVal, yVal, direction, moves)) {
                    Node node = new Node(getHeatlossAtCoord(xVal, yVal), new Coordinate(xVal, yVal), direction, moves);
                    graph.add(node);
                }
            }
        }
    }

    private boolean isValidNode(int xVal, int yVal, Direction direction, int numMoves) {
        return switch (direction) {
            case NORTH -> xVal + numMoves <= xSize - 1;
            case EAST -> yVal - numMoves >= minYValue;
            case SOUTH -> xVal - numMoves >= minXValue;
            case WEST -> yVal + numMoves <= ySize - 1;
            default -> false;
        };

    }

    private void setUpNeighborsMax3() {
        for (Node node : graph) {
            findPossibleNeighborsForNodeMax3(node);
        }
    }

    private void findPossibleNeighborsForNodeMax3(Node node) {
        Direction currDirection = node.getDirection();
        int currNumMoves = node.getNumMoves();
        for (Direction direction : Direction.getDirections()) {
            Coordinate neighborCoord = node.getCoordinateOfNodeInDirection(direction, 1);
            Node neighborNode = null;
            if (direction == currDirection) {
                neighborNode = getNode(neighborCoord, direction, currNumMoves + 1);
            } else if (direction != Direction.getOppositeDirection(currDirection)) {
                neighborNode = getNode(neighborCoord, direction, 1);

            }
            if (neighborNode != null) {
                node.addNeighborNode(neighborNode);
            }
        }
    }

    public void initializeForGraphClumsy() {
        initializeGraphClumsy();
        setUpNeighborsClumsy();
    }

    private void initializeGraphClumsy() {
        this.graph = new ArrayList<>();
        // Set up beginning node outside the loop, as it has no previous steps or
        // direction
        graph.add(new Node(0, new Coordinate(0, 0), Direction.UNDEFINED, 0));
        for (int x = minXValue; x < xSize; x++) {
            for (int y = minYValue; y < ySize; y++) {
                generateNodesForPossibleMovesClumsy(x, y);
            }
        }
    }

    private void generateNodesForPossibleMovesClumsy(int xVal, int yVal) {
        for (Direction direction : Direction.getDirections()) {
            for (int moves = 1; moves <= MAX_CLUMSY_MOVES; moves++) {
                if (isValidClumsyNode(xVal, yVal, direction, moves)) {
                    Node node = new Node(getHeatlossAtCoord(xVal, yVal), new Coordinate(xVal, yVal), direction, moves);
                    graph.add(node);
                }
            }
        }
    }

    private boolean isValidClumsyNode(int xVal, int yVal, Direction direction, int numMoves) {
        int movesToMin = 4 - numMoves;
        return switch (direction) {
            // (Coordinate is on the grid) && (Coordinate has enough room to reach min
            // moves)
            case NORTH -> (xVal + numMoves <= xSize - 1) && (xVal - movesToMin >= minXValue);
            case EAST -> (yVal - numMoves >= minYValue) && (yVal + movesToMin <= ySize - 1);
            case SOUTH -> (xVal - numMoves >= minXValue) && (xVal + movesToMin <= xSize - 1);
            case WEST -> (yVal + numMoves <= ySize - 1) && (yVal - movesToMin >= minYValue);
            default -> false;
        };

    }

    private void setUpNeighborsClumsy() {
        for (Node node : graph) {
            findPossibleNeighborsForClumsyNode(node);
        }
    }

    private void findPossibleNeighborsForClumsyNode(Node node) {
        Direction currDirection = node.getDirection();
        int currNumMoves = node.getNumMoves();
        for (Direction direction : Direction.getDirections()) {
            Coordinate neighborCoord = node.getCoordinateOfNodeInDirection(direction, 1);
            Node neighborNode = null;
            if ((direction == currDirection || currDirection == Direction.UNDEFINED) && currNumMoves < 10) {
                neighborNode = getNode(neighborCoord, direction, currNumMoves + 1);
            } else if (direction != Direction.getOppositeDirection(currDirection) && direction != currDirection
                    && currNumMoves >= 4) {
                neighborNode = getNode(neighborCoord, direction, 1);

            }
            if (neighborNode != null) {
                node.addNeighborNode(neighborNode);
            }
        }
    }

    public long sumShortestRoute() {

        List<Node> unsettledNodes = new ArrayList<>();
        unsettledNodes.add(graph.get(0));

        List<Node> settledNodes = new ArrayList<>();

        while (!unsettledNodes.isEmpty()) {
            Node currNode = unsettledNodes.get(0);
            // System.out.printf(
            // "Evaluating node at %d, %d with total heat loss value of %d, local heat loss
            // of %d and %d moves in direction %s%n%n",
            // currNode.getXVal(), currNode.getYVal(), currNode.getHeatLossTotal(),
            // currNode.getHeatLossAtNode(),
            // currNode.getNumMoves(),
            // currNode.getDirection());

            for (Node neighborNode : currNode.getAdjacentNodes()) {
                // if (!settledNodes.contains(neighborNode)) {
                // if (nodeStillNeedsToBeEvaluated(settledNodes, neighborNode)) {
                boolean wasUpdated = false;
                // if (!previousMatches.contains(new Match(currNode, neighborNode,
                // neighborNode.getHeatLossTotal()))) {
                wasUpdated = updateNodes(currNode, neighborNode);
                // }

                // if (!unsettledNodes.contains(neighborNode)) {
                if (wasUpdated) {
                    unsettledNodes.add(neighborNode);
                }

                // }
            }

            // System.out.printf(
            // "Settling/unsettling node at %d, %d with total heat loss value of %d, local
            // heat loss of %d and %d moves in direction %s%n%n",
            // currNode.getXVal(), currNode.getYVal(), currNode.getHeatLossTotal(),
            // currNode.getHeatLossAtNode(),
            // currNode.getNumMoves(),
            // currNode.getDirection());
            settledNodes.add(currNode);
            unsettledNodes.remove(currNode);

        }

        final Coordinate endCoord = new Coordinate(xSize - 1, ySize - 1);

        Node nodeWithBestPath = null;
        for (Node node : getNodesAtCoord(endCoord)) {
            if (nodeWithBestPath == null || node.getHeatLossTotal() < nodeWithBestPath.getHeatLossTotal()) {
                nodeWithBestPath = node;
            }
        }

        if (nodeWithBestPath != null) {
            printBestPath(nodeWithBestPath);
            return nodeWithBestPath.getHeatLossTotal();
        } else {
            return 0l;
        }
    }

    private boolean nodeStillNeedsToBeEvaluated(List<Node> settledNodes, Node node) {
        return !settledNodes.containsAll(node.getAdjacentNodes());
    }

    private boolean updateNodes(Node currNode, Node neighborNode) {
        Integer heatLossToNeighbor = neighborNode.getHeatLossAtNode();
        // System.out.printf(
        // "Updating neighbor node with %d steps to the %s at %d, %d, which will
        // increase heat loss by %d%n",
        // neighborNode.getNumMoves(), neighborNode.getDirection(),
        // neighborNode.getXVal(), neighborNode.getYVal(),
        // heatLossToNeighbor);
        String prevNode = neighborNode.getPreviousNode() == null ? null : neighborNode.getPreviousNode().toString();
        // System.out.printf("Current values of neighbor -> total heatloss: %d, previous
        // node: %s%n",
        // neighborNode.getHeatLossTotal(), prevNode);
        Integer newHeatLoss = currNode.getHeatLossTotal() + heatLossToNeighbor;
        boolean wasUpdated = false;
        if (neighborNode.getHeatLossTotal() == 0 || neighborNode.getHeatLossTotal() > newHeatLoss) {
            // previousMatches.add(new Match(currNode, neighborNode, newHeatLoss));
            wasUpdated = true;
            neighborNode.setPreviousNode(currNode);
            neighborNode.setHeatLossTotal(newHeatLoss);
            prevNode = neighborNode.getPreviousNode() == null ? null : neighborNode.getPreviousNode().toString();
            // System.out.printf("New values of neighbor -> total heatloss: %d, previous
            // node: %s, direction: %s%n%n",
            // neighborNode.getHeatLossTotal(), prevNode,
            // neighborNode.getDirection());
        }
        return wasUpdated;
    }

    private List<Node> getNodesAtCoord(Coordinate coord) {
        return this.graph.stream().filter(node -> node.getCoordinate().equals(coord)).toList();
    }

    private Node getNode(Coordinate coord, Direction direction, int numMoves) {
        return this.graph.stream().filter(node -> node.getCoordinate().equals(coord)
                && node.getDirection().equals(direction) && node.getNumMoves() == numMoves).findFirst().orElse(null);
    }

    private Integer getHeatlossAtCoord(int xVal, int yVal) {
        boolean isWithinXBounds = xVal >= minXValue && xVal < xSize;
        boolean isWithinYBounds = yVal >= minYValue && yVal < ySize;

        if (isWithinXBounds && isWithinYBounds) {
            return TextParserUtil.parseInteger(rawInput.get(xVal).charAt(yVal) + "");
        } else {
            return null;
        }
    }

    private void printBestPath(Node node) {
        System.out.println("Printing the backwards path taken.");
        Node previous = node.getPreviousNode();
        while (previous != null) {
            System.out.printf("Node at %d, %d with local heat loss %d and %d steps in direction %s%n",
                    previous.getXVal(), previous.getYVal(), previous.getHeatLossAtNode(), previous.getNumMoves(),
                    previous.getDirection());
            previous = previous.getPreviousNode();
        }
    }
}
