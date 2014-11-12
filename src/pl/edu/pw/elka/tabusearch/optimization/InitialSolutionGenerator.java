package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Graph;
import pl.edu.pw.elka.tabusearch.domain.Solution;

/**
 * Created by mc on 11/11/14.
 */
public class InitialSolutionGenerator {

    public Solution generateInitialSolution(final Graph graph) {
        // just return path containing all cities in default order
        return new Solution(graph.getNodesList());
    }
}
