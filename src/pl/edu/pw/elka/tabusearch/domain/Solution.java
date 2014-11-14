package pl.edu.pw.elka.tabusearch.domain;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private final List<Node> nodes;

    public Solution(final List<Node> nodes) {
        this.nodes = new ArrayList(nodes);
    }

    public List<Node> getNodesList() {
        return nodes;
    }

    public Integer getDistance() {
        Integer distance = 0;
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
        return distance;
    }

    public String toString() {
        String text = new String();
        for (Node node : nodes) text += node.getLabel() + " ";
        return text;
    }
}
