package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Graph;
import pl.edu.pw.elka.tabusearch.domain.Solution;

/**
 * Class representing generator of initial solution - in this case generates Solution containing a random set of nodes.
 * @author mc
 */
public class InitialSolutionGenerator {

    public Solution generateInitialSolution(final Graph graph) {
        // just return path containing all cities in default order
        return new Solution(graph.getNodesList());
    }
}
