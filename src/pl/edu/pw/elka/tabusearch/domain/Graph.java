package pl.edu.pw.elka.tabusearch.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Graph {
    private final Set<Node> nodes;

    public Graph(final Set<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getNodesList() {
        return new ArrayList<>(nodes);
    }
}
