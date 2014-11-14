package pl.edu.pw.elka.tabusearch.domain;

import java.util.*;

public class Node {
    private final String label;
    private Map<String, Integer> distancesMap;

    public Node(final String label) {
        this.label = label;
        this.distancesMap = new HashMap<>();
    }

    public String getLabel() {
        return label;
    }

    public Integer getDistanceToNode(final String nodeLabel) {
        return distancesMap.get(nodeLabel);
    }

    public void addConnection(final String sourceLabel, final Integer distance) {
        this.distancesMap.put(sourceLabel, distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj || getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        return Objects.equals(this.label, other.label);
    }
}
