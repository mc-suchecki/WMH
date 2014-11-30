package pl.edu.pw.elka.tabusearch.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing particular solution - in this case a list of Nodes that are visited on the way.
 * @author mc
 */
public class Solution {
    private final List<Node> nodes;
    private Integer distance;

    public Solution(final List<Node> nodes) {
        this.nodes = new ArrayList<>(nodes);
    }

    public List<Node> getNodesList() {
        return nodes;
    }

    public Integer getDistance() {
        if(distance == null)
        {
            distance = 0;
            Node first, second;
            for (Integer i = 0; i < nodes.size();) {
                first = nodes.get(i);
                ++i;
                if (nodes.size() > i) {
                    second = nodes.get(i);
                } else {
                    second = nodes.get(0);
                }
                distance += first.getDistanceToNode(second.getLabel());
            }
        }
        return distance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final Node node : nodes) {
            sb.append(node.getLabel());
            sb.append(" ");
        }
        return sb.toString();
    }

    public boolean isBetterThan(final Solution another) {
        return getDistance() < another.getDistance();
    }
}
