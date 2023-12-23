package adventofcode2023.day17;

// TODO: this was an attempt to increase performance by saving previous matches, but it can't work this way
public class Match {
    private Node startNode;
    private Node endNode;
    private int heatLossTotal;

    public Match(Node startNode, Node endNode, int heatLossTotal) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.heatLossTotal = heatLossTotal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((startNode == null) ? 0 : startNode.hashCode());
        result = prime * result + ((endNode == null) ? 0 : endNode.hashCode());
        result = prime * result + heatLossTotal;
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
        Match other = (Match) obj;
        if (startNode == null) {
            if (other.startNode != null)
                return false;
        } else if (!startNode.equals(other.startNode))
            return false;
        if (endNode == null) {
            if (other.endNode != null)
                return false;
        } else if (!endNode.equals(other.endNode))
            return false;
        if (heatLossTotal != other.heatLossTotal)
            return false;
        return true;
    }

}
