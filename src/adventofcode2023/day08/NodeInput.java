package adventofcode2023.day08;

public class NodeInput {
    private String nodeValue;
    private String nodeLeftValue;
    private String nodeRightValue;
    private boolean inTree;

    public NodeInput(String nodeValue, String nodeLeftValue, String nodeRightValue, boolean inTree) {
        this.nodeValue = nodeValue;
        this.nodeLeftValue = nodeLeftValue;
        this.nodeRightValue = nodeRightValue;
        this.inTree = inTree;
    }

    public NodeInput(String nodeValue, String nodeLeftValue, String nodeRightValue) {
        this(nodeValue, nodeLeftValue, nodeRightValue, false);
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public String getNodeLeftValue() {
        return nodeLeftValue;
    }

    public String getNodeRightValue() {
        return nodeRightValue;
    }

    public boolean isInTree() {
        return inTree;
    }

    public void setNodeLeftValue(String nodeLeftValue) {
        this.nodeLeftValue = nodeLeftValue;
    }

    public void setNodeRightValue(String nodeRightValue) {
        this.nodeRightValue = nodeRightValue;
    }

    public void setInTree(boolean inTree) {
        this.inTree = inTree;
    }

}
