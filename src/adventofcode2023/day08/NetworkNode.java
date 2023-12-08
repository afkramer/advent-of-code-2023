package adventofcode2023.day08;

public class NetworkNode {
    private String value;
    private NetworkNode left;
    private NetworkNode right;

    public NetworkNode(String value, NetworkNode left, NetworkNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public NetworkNode(String value) {
        this(value, null, null);
    }

    public String getValue() {
        return value;
    }

    public NetworkNode getLeft() {
        return left;
    }

    public NetworkNode getRight() {
        return right;
    }

    public void setLeft(NetworkNode left) {
        this.left = left;
    }

    public void setRight(NetworkNode right) {
        this.right = right;
    }
}
