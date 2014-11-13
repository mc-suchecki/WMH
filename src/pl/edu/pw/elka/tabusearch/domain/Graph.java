package pl.edu.pw.elka.tabusearch.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
    private final Map<String, Node> labelsToNodesMap;
    private final Set<Edge> edges;

    public Graph(final Map<String, Node> labelsToNodesMap, final Set<Edge> edges) {
        this.labelsToNodesMap = labelsToNodesMap;
        this.edges = edges;
    }

    public List<Node> getNodesList() {
        final List<Node> nodesList = new ArrayList<>();
        for (final Node node : labelsToNodesMap.values()) {
            nodesList.add(node);
        }
        return nodesList;
    }
}
