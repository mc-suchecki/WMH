package pl.edu.pw.elka.tabusearch.domain;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private final List<Node> nodes;

    public Solution(final List<Node> nodes) {
        this.nodes = new ArrayList(nodes);
    }

    public List<Node> getNodeList() {
        return nodes;
    }

    public Integer getDistance() {
        Integer distance = 0;
        for (Node node : nodes) {
            //TODO
        }
        return distance;
    }
}
