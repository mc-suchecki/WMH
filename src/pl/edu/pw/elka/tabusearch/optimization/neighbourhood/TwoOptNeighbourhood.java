package pl.edu.pw.elka.tabusearch.optimization.neighbourhood;

import pl.edu.pw.elka.tabusearch.domain.Node;
import pl.edu.pw.elka.tabusearch.domain.Solution;

import java.util.Collections;
import java.util.List;

public class TwoOptNeighbourhood implements Neighbourhood {
    private final List<Node> nodesList;
    private final int neighboursCount;

    public TwoOptNeighbourhood(final Solution solution) {
        this.nodesList = Collections.unmodifiableList(solution.getNodesList());
        this.neighboursCount = calculateNeighboursCount(nodesList.size());
    }

    private static int calculateNeighboursCount(final int n) {
        return (int)(0.5 * n * (n-1)); // n - size of nodes' list
    }

    @Override
    public int size() {
        return neighboursCount;
    }

    @Override
    public NeighboursIterator iterator() {
        return new NeighboursIterator(nodesList);
    }


}
