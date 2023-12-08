package adventofcode2023.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Network {
    private NetworkNode root;
    private List<NetworkNode> roots;
    List<NodeInput> nodeInputs;
    Map<String, List<String>> adjVertices = new HashMap<>();

    public Network(List<NodeInput> nodeInputs) {
        this.nodeInputs = nodeInputs;
        // initializeNodes();
        initializeVertices();
    }

    private void initializeNodes() {
        // root = createTree("AAA");

        roots = this.nodeInputs.stream().filter(n -> n.getNodeValue().charAt(2) == 'A')
                .map(n -> createTree(n.getNodeValue())).toList();
    }

    // public void initializeVertices() {
    // for (NodeInput input : nodeInputs) {
    // this.adjVertices.put(new Vertex(input.getNodeValue()), new ArrayList<>());
    // }

    // for (NodeInput input : nodeInputs) {
    // Vertex parent = new Vertex(input.getNodeValue());
    // Vertex left = new Vertex(input.getNodeLeftValue());
    // Vertex right = new Vertex(input.getNodeRightValue());
    // this.adjVertices.get(parent).add(left);
    // this.adjVertices.get(parent).add(right);
    // }
    // }

    public void initializeVertices() {
        for (NodeInput input : nodeInputs) {
            this.adjVertices.put(input.getNodeValue(), new ArrayList<>());
        }

        for (NodeInput input : nodeInputs) {
            this.adjVertices.get(input.getNodeValue()).add(input.getNodeLeftValue());
            this.adjVertices.get(input.getNodeValue()).add(input.getNodeRightValue());
        }
    }

    private NetworkNode createTree(String nodeValue) {
        if (nodeInputs.stream().allMatch(n -> n.isInTree())) {
            return null;
        } else {
            NodeInput nodeInput = this.nodeInputs.stream().filter(n -> n.getNodeValue().equals(nodeValue)).findFirst()
                    .get();
            nodeInput.setInTree(true);
            NetworkNode rootNode = new NetworkNode(nodeValue);
            rootNode.setLeft(createTree(nodeInput.getNodeLeftValue()));
            rootNode.setRight(createTree(nodeInput.getNodeRightValue()));

            return rootNode;
        }
    }

    public NetworkNode getRoot() {
        return root;
    }

    public List<NetworkNode> getRoots() {
        return roots;
    }

    public Map<String, List<String>> getAdjVertices() {
        return adjVertices;
    }

}
