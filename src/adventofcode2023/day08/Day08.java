package adventofcode2023.day08;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import adventofcode2023.Day;
import adventofcode2023.TextParserUtil;

public class Day08 implements Day {
    String instructions = "";
    List<NodeInput> nodeInputs;

    @Override
    public void partOne() {
        boolean instructionsFinished = false;
        int stepsNeeded = 0;
        NodeInput currentNode = nodeInputs.stream().filter(n -> n.getNodeValue().equals("AAA")).findFirst().get();
        while (!instructionsFinished) {
            for (int i = 0; i < instructions.length(); i++) {
                stepsNeeded++;
                if (instructions.charAt(i) == 'L') {
                    final NodeInput toFind = currentNode;
                    currentNode = nodeInputs.stream().filter(n -> n.getNodeValue().equals(toFind.getNodeLeftValue()))
                            .findFirst().get();
                } else {
                    final NodeInput toFind = currentNode;
                    currentNode = nodeInputs.stream().filter(n -> n.getNodeValue().equals(toFind.getNodeRightValue()))
                            .findFirst().get();
                }

                if (currentNode.getNodeValue().equals("ZZZ")) {
                    instructionsFinished = true;
                }
            }

        }

        System.out.println(stepsNeeded);
    }

    public void partTwoOlder() {
        boolean instructionsFinished = false;
        long stepsNeeded = 0;

        List<NodeInput> currentNodes = nodeInputs.stream().filter(n -> n.getNodeValue().charAt(2) == 'A').toList();
        while (!instructionsFinished) {
            for (int i = 0; i < instructions.length(); i++) {
                stepsNeeded++;
                final List<NodeInput> toFind = currentNodes;
                if (instructions.charAt(i) == 'L') {
                    currentNodes = nodeInputs.stream()
                            .filter(n -> toFind.stream().map(NodeInput::getNodeLeftValue).toList()
                                    .contains(n.getNodeValue()))
                            .toList();
                } else {
                    currentNodes = nodeInputs.stream()
                            .filter(n -> toFind.stream().map(NodeInput::getNodeRightValue).toList()
                                    .contains(n.getNodeValue()))
                            .toList();
                }

                if (currentNodes.stream().allMatch(n -> n.getNodeValue().charAt(2) == 'Z')) {
                    instructionsFinished = true;
                    break;
                }
            }

        }

        System.out.println(stepsNeeded);
    }

    // @Override
    public void partTwoOld() {
        Network network = new Network(nodeInputs);

        boolean instructionsFinished = false;
        long stepsNeeded = 0L;
        if (stepsNeeded % 10000L == 0L) {
            System.out.println(String.format("Step: %d", stepsNeeded));
        }
        List<NetworkNode> currentNodes = network.getRoots();
        while (!instructionsFinished) {
            for (int i = 0; i < instructions.length(); i++) {
                stepsNeeded++;
                if (instructions.charAt(i) == 'L') {
                    currentNodes = currentNodes.stream().map(NetworkNode::getLeft).toList();
                } else {
                    currentNodes = currentNodes.stream().map(NetworkNode::getRight).toList();
                }

                if (currentNodes.stream().allMatch(node -> node.getValue().charAt(2) == 'Z')) {
                    instructionsFinished = true;
                    break;
                }
            }

        }

        System.out.println(stepsNeeded);
    }

    public void partTwoInefficient() {
        Network network = new Network(nodeInputs);

        boolean instructionsFinished = false;
        long stepsNeeded = 0L;

        List<String> currentNodeValues = this.nodeInputs.stream().map(n -> n.getNodeValue())
                .filter(s -> s.charAt(2) == 'A').toList();
        while (!instructionsFinished) {
            if (stepsNeeded % 1000000L == 0L) {
                System.out.println("Current timestamp: " + LocalTime.now());
                System.out.println(String.format("Step: %d", stepsNeeded));
            }
            for (int i = 0; i < instructions.length(); i++) {
                stepsNeeded++;
                List<String> nextNodes = new ArrayList<>();
                for (String value : currentNodeValues) {
                    if (instructions.charAt(i) == 'L') {
                        nextNodes.add(network.getAdjVertices().get(value).get(0));
                    } else {
                        nextNodes.add(network.getAdjVertices().get(value).get(1));
                    }

                }

                if (nextNodes.stream().allMatch(node -> node.charAt(2) == 'Z')) {
                    instructionsFinished = true;
                    break;
                } else {
                    currentNodeValues = nextNodes;
                }

            }

        }

        System.out.println(stepsNeeded);
    }

    @Override
    public void partTwo() {
        List<String> startNodes = nodeInputs.stream().map(NodeInput::getNodeValue).filter(val -> val.charAt(2) == 'A')
                .toList();
        List<Long> iterationsPerNode = startNodes.stream().map(s -> getStepsNeeded(s)).toList();
        Long lowestCommonDenom = iterationsPerNode.stream().reduce(iterationsPerNode.get(0), this::lcm);
        System.out.println(iterationsPerNode.toString());
        System.out.println(lowestCommonDenom);

    }

    public long getStepsNeeded(String nodeValue) {
        boolean instructionsFinished = false;
        long stepsNeeded = 0;
        NodeInput currentNode = nodeInputs.stream().filter(n -> n.getNodeValue().equals(nodeValue)).findFirst().get();
        while (!instructionsFinished) {
            for (int i = 0; i < instructions.length(); i++) {
                stepsNeeded++;
                if (instructions.charAt(i) == 'L') {
                    final NodeInput toFind = currentNode;
                    currentNode = nodeInputs.stream().filter(n -> n.getNodeValue().equals(toFind.getNodeLeftValue()))
                            .findFirst().get();
                } else {
                    final NodeInput toFind = currentNode;
                    currentNode = nodeInputs.stream().filter(n -> n.getNodeValue().equals(toFind.getNodeRightValue()))
                            .findFirst().get();
                }

                if (currentNode.getNodeValue().charAt(2) == 'Z') {
                    instructionsFinished = true;
                }
            }

        }
        return stepsNeeded;
    }

    // function to calculate gcd
    // or hcf of two numbers.
    private long gcd(long a, long b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // function to calculate
    // lcm of two numbers.
    private long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    @Override
    public void init() {
        List<String> rawData = TextParserUtil.readData("day08.txt");
        boolean instructionsRead = false;
        this.nodeInputs = new ArrayList<>();
        for (String string : rawData) {
            if (string.isBlank()) {
                instructionsRead = true;
            } else if (instructionsRead) {
                String nodeValue = string.substring(0, 3);
                String left = string.substring(7, 10);
                String right = string.substring(12, 15);
                nodeInputs.add(new NodeInput(nodeValue, left, right));
            } else {
                instructions += string;
            }

        }
    }
}
