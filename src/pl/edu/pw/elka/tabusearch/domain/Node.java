package pl.edu.pw.elka.tabusearch.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public Integer getAverageDistance() {
        Double distanceSum = 0.0;
        for (Integer distance : distancesMap.values()) {
            distanceSum += distance;
        }
        Double result = distanceSum / distancesMap.size();
        return result.intValue();   // round down (floor)
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
